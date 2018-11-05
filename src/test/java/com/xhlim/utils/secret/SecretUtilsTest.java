package com.xhlim.utils.secret;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author xhlim@outlook.com
 * @date 2018-08-29
 */
public class SecretUtilsTest {

    @Test
    public void test() throws NoSuchAlgorithmException {
        Map<String, String> keyPair = SecretUtils.getKeyPairMap();
        System.out.println("---------- 私钥 -----------");
        System.out.println(keyPair.get(SecretUtils.PUBLIC_KEY));
        System.out.println("---------- 公钥 -----------");
        System.out.println(keyPair.get(SecretUtils.PRIVATE_KEY));
        Integer i = 1 << 3;
        System.out.println(i);
    }
}
