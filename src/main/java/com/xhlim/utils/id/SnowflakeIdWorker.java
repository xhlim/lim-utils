package com.xhlim.utils.id;

/**
 * SnowFlake 算法生产的ID是一个64位大小的整数，结构如下：<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 00000 - 000000000000<br>
 * 【01 位】标识，由于long在Java中是有符号的，最高位是符号位，正数是0，负数是1，ID一般使用正数，所以最高位是0<br>
 * 【41 位】时间截(毫秒级)，表示<span style='color: red'>当前时间与指定的开始时间的差值</span>
 * （<span style='color: red'>设置开始时间后最好不要修改，否则可能会出现ID重复问题</span>），
 * 41位的时间截，可以使用约70年: (2^41)/(1000*60*60*24*365)=69.73年<br>
 * 【10 位】工作机器ID，可以部署在2^10=1024个节点，包括5位datacenterId和5位workerId<br>
 * 【12 位】序列号，记录同一毫秒内产生的ID，同一机器同一时间截（毫秒)内产生的4096个ID<br>
 * SnowFlake的优点：所有生成的id按时间趋势递增，整个分布式系统内不会产生重复id（因为有datacenterId和workerId来做区分）。
 *
 * @author xhlim@outlook.com
 * @create 2017-11-27
 */
public class SnowflakeIdWorker {
    /**
     * 开始时间截(2000-01-01 08:00:00)，单位毫秒
     */
    private final long startTimestamp = 946684800000L;
    /**
     * 机器ID所占位数
     */
    private final long workerIdBit = 5L;
    /**
     * 数据中心标识ID所占位数
     */
    private final long datacenterIdBit = 5L;
    /**
     * 支持的最大机器ID，结果是 31: 0B11111
     */
    private final long maxWorkerId = ~(-1L << workerIdBit);
    /**
     * 支持的最大数据中心ID，结果是 31: 0B11111
     */
    private final long maxDatacenterId = ~(-1L << datacenterIdBit);
    /**
     * 序列号在ID中占的位数
     */
    private final long sequenceBits = 12L;
    /**
     * 机器ID向左移位数
     */
    private final long workerIdShift = 12L;
    /**
     * 数据中心ID向左移位数(12+5)
     */
    private final long datacenterIdShift = workerIdShift + workerIdBit;
    /**
     * 时间截向左移位数(12+5+5)
     */
    private final long timestampLeftShift = datacenterIdShift + datacenterIdBit;
    /**
     * 生成序列号掩码，这里为4095(0B111111111111=0xFFF=4095)
     */
    private final long sequenceMask = ~(-1L << sequenceBits);
    /**
     * 工作机器ID(0~31)
     */
    private long workerId;
    /**
     * 数据中心ID(0~31)
     */
    private long datacenterId;
    /**
     * 毫秒内序列号(0~4095)
     */
    private long sequence = 0L;
    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    /**
     * ID 生成器: 使用工作机器的序号，范围是 [0, 1023]，优点是方便给机器编号
     *
     * @param workerId 工作机器 ID
     */
    public SnowflakeIdWorker(long workerId) {
        // 最大1023
        long maxMachineId = (maxDatacenterId + 1) * (maxWorkerId + 1) - 1;
        if (workerId < 0 || workerId > maxMachineId) {
            throw new IllegalArgumentException(String.format("工作机器ID不能大于 %d 或者小于 %d", maxMachineId, 0));
        }
        this.datacenterId = (workerId >> workerIdBit) & maxDatacenterId;
        this.workerId = workerId & maxWorkerId;
    }

    /**
     * ID 生成器: 使用工作机器 ID 和数据中心 ID，优点是方便分数据中心管理
     *
     * @param datacenterId 数据中心 ID (0~31)
     * @param workerId     工作机器 ID (0~31)
     */
    public SnowflakeIdWorker(long datacenterId, long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("工作机器ID不能大于 %d 或者小于 %d", maxWorkerId, 0));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("数据中心ID不能大于 %d 或者小于 %d", maxDatacenterId, 0));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 获得下一个 ID(该方法是线程安全的)
     *
     * @return long 类型的 ID
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        // 如果当前时间小于上一次 ID 生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("系统时间回退，无法生成ID，时间差:%d", lastTimestamp - timestamp));
        }
        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒，获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }
        // 上次生成 ID 的时间截
        lastTimestamp = timestamp;
        // 移位并通过或运算拼到一起组成 64 位的 ID
        return ((timestamp - startTimestamp) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成 ID 的时间截
     * @return 当前时间戳(毫秒)
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        // 如果当前时间小于最后时间，设置当前时间为最新时间
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回当前时间，以毫秒为单位
     *
     * @return 当前时间(毫秒)
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }
}