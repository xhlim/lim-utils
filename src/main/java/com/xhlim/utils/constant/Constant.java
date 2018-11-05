package com.xhlim.utils.constant;

import java.util.Locale;

/**
 * @author xhlim@outlook.com
 * @date 2018-08-08
 */
public class Constant {

    /**
     * lang 型
     */
    public enum Lon {
        // 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16
        ZERO(0L), ONE(ZERO.num + 1), TWO(ONE.num + 1), THREE(TWO.num + 1), FOUR(THREE.num + 1), FIVES(FOUR.num + 1), SIX(FIVES.num + 1), SEVEN(SIX.num + 1), EIGHT(SEVEN.num + 1), NINE(EIGHT.num + 1), TEN(NINE.num + 1), ELEVEN(TEN.num + 1), TWELVE(ELEVEN.num + 1), THIRTEEN(TWELVE.num + 1), FOURTEEN(THIRTEEN.num + 1), FIFTEEN(FOURTEEN.num + 1), SIXTEEN(FIFTEEN.num + 1);

        private Long num;

        Lon(Long num) {
            this.num = num;
        }

        public Long num() {
            return this.num;
        }

        /**
         * 初始化一个枚举类型
         *
         * @param op
         * @return
         */
        public static Lon init(String op) {
            return valueOf(op.toUpperCase(Locale.ROOT));
        }
    }

    /**
     * int 型
     */
    public enum Int {
        // 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16
        ZERO(0), ONE(ZERO.num + 1), TWO(ONE.num + 1), THREE(TWO.num + 1), FOUR(THREE.num + 1), FIVES(FOUR.num + 1), SIX(FIVES.num + 1), SEVEN(SIX.num + 1), EIGHT(SEVEN.num + 1), NINE(EIGHT.num + 1), TEN(NINE.num + 1), ELEVEN(TEN.num + 1), TWELVE(ELEVEN.num + 1), THIRTEEN(TWELVE.num + 1), FOURTEEN(THIRTEEN.num + 1), FIFTEEN(FOURTEEN.num + 1), SIXTEEN(FIFTEEN.num + 1);

        private Integer num;

        Int(Integer num) {
            this.num = num;
        }

        public Integer num() {
            return this.num;
        }

        /**
         * 初始化一个枚举类型
         *
         * @param op
         * @return
         */
        public static Int init(String op) {
            return valueOf(op.toUpperCase(Locale.ROOT));
        }
    }

    public static String STR_NULL = "null";

    public enum Time {
        // 毫秒,秒,分,小时,天
        MILLISECOND(1L), SECOND(MILLISECOND.time * 1000), MINUTE(SECOND.time * 60), HOUR(MINUTE.time * 60), DAY(HOUR.time * 24);

        private Long time;

        Time(Long time) {
            this.time = time;
        }

        public Long time() {
            return this.time;
        }

        /**
         * 初始化一个枚举类型
         *
         * @param op
         * @return
         */
        public static Time init(String op) {
            return valueOf(op.toUpperCase(Locale.ROOT));
        }
    }

    public enum SortOrder {


        // Ascending order, Descending order
        ASC("asc"), DESC("desc");

        private String sort;

        SortOrder(String sort) {
            this.sort = sort;
        }

        public String sort() {
            return sort;
        }

        /**
         * 初始化一个枚举类型
         *
         * @param op
         * @return
         */
        public static SortOrder init(String op) {
            return valueOf(op.toUpperCase(Locale.ROOT));
        }
    }


}
