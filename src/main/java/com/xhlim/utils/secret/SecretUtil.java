package com.xhlim.utils.secret;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 密钥生成
 *
 * @author xhlim@outlook.com
 * @date 2018-08-29
 */
public class SecretUtil {

    /**
     * 加密算法
     */
    enum Algorithm {
        // RSA
        RSA("RSA");

        private String algorithm;

        Algorithm(String algorithm) {
            this.algorithm = algorithm;
        }

        public String algorithm() {
            return this.algorithm;
        }
    }

    /**
     * 公钥
     */
    public final static String PUBLIC_KEY = "PublicKey";
    /**
     * 私钥
     */
    public final static String PRIVATE_KEY = "PrivateKey";
    /**
     * 密钥默认长度
     */
    private final static Integer KEY_LENGTH = 1024;
    /**
     * 初始容量 16
     */
    private final static Integer INITIAL_CAPACITY = 1 << 4;


    /**
     * 生成密钥对
     *
     * @param length
     * @return KeyPair
     * @throws NoSuchAlgorithmException
     */
    private static KeyPair generateKeyPair(Integer length) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(Algorithm.RSA.algorithm());
        // 初始化指定大小
        keyPairGenerator.initialize(length == null ? KEY_LENGTH : length);
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }


    /**
     * 生成密钥对
     *
     * @param length
     * @return 包含公钥和私钥的map {@link #PUBLIC_KEY},{@link #PRIVATE_KEY}
     * @throws NoSuchAlgorithmException
     */
    public static Map<String, String> getKeyPairMap(Integer length) throws NoSuchAlgorithmException {
        KeyPair keyPair = generateKeyPair(length);
        return new HashMap<String, String>(INITIAL_CAPACITY) {{
            put(PUBLIC_KEY, Base64.getMimeEncoder().encodeToString(keyPair.getPublic().getEncoded()));
            put(PRIVATE_KEY, Base64.getMimeEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
        }};
    }

    /**
     * 生成密钥对
     *
     * @return 包含公钥和私钥的map {@link #PUBLIC_KEY},{@link #PRIVATE_KEY}
     * @throws NoSuchAlgorithmException
     */
    public static Map<String, String> getKeyPairMap() throws NoSuchAlgorithmException {
        KeyPair keyPair = generateKeyPair(null);
        return new HashMap<String, String>(INITIAL_CAPACITY) {{
            put(PUBLIC_KEY, Base64.getMimeEncoder().encodeToString(keyPair.getPublic().getEncoded()));
            put(PRIVATE_KEY, Base64.getMimeEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
        }};
    }

}
