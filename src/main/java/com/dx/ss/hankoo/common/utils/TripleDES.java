/**
 *
 */
package com.dx.ss.hankoo.common.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 3DES加解密
 */
public class TripleDES {

    /**
     * 加密算法
     */
    private static final String ALGORITHM_3DES = "DESede";

    /**
     * 默认编码
     */
    private static final String ENCODING = "UTF-8";

    /**
     * CIPHER算法名称
     */
    private static final String CIPHER_NAME = "DESede/ECB/NoPadding";

    /**
     * keyBytes 加密密钥，长度为24字节
     */
    private final byte[] keyBytes;

    public TripleDES(String key) throws UnsupportedEncodingException {
        this.keyBytes = key.getBytes(ENCODING);
    }

    /**
     * 3DES加密
     *
     * @param src 被加密的数据缓冲区
     * @return 解密后的数据缓冲区
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    private byte[] encrypt(byte[] src) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        src = fill(src);

        SecretKey key = new SecretKeySpec(keyBytes, ALGORITHM_3DES);
        Cipher c1 = Cipher.getInstance(CIPHER_NAME);

        c1.init(Cipher.ENCRYPT_MODE, key);
        return c1.doFinal(src);

    }

    /**
     * 3DES解密
     *
     * @param src 解密后的数据缓冲区
     * @return 被加密的数据缓冲区
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    private byte[] decrypt(byte[] src) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {

        SecretKey key = new SecretKeySpec(keyBytes, ALGORITHM_3DES);
        Cipher c1 = Cipher.getInstance(CIPHER_NAME);

        c1.init(Cipher.DECRYPT_MODE, key);

        return trim(c1.doFinal(src));

    }

    public String encryptBytes(byte[] bytes) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException {
        byte[] bytes1 = encrypt(bytes);

        return BaseCal.byte2hex(bytes1);
    }

    public String decryptBytes(byte[] bytes) {
        try {
            byte[] bytes1 = decrypt(bytes);

            return new String(bytes1, ENCODING);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 3DES加密
     *
     * @param src 明文
     * @return 密文
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public String encrypt(String src) throws UnsupportedEncodingException, InvalidKeyException,
            NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {
        byte[] bytes = src.getBytes(ENCODING);
        byte[] bytes1 = encrypt(bytes);

        return BaseCal.byte2hex(bytes1);
    }

    /**
     * 3DES解密
     *
     * @param src 密文
     * @return 明文
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public String decrypt(String src) throws UnsupportedEncodingException, InvalidKeyException,
            NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {

        byte[] bytes = BaseCal.hex2byte(src);
        byte[] bytes1 = decrypt(bytes);

        return new String(bytes1, ENCODING);
    }

    /**
     * 数组补充0，使之长度为8的倍数
     *
     * @param bytes
     * @return 长度为8的倍数的新数组
     */
    private byte[] fill(byte[] bytes) {
        if (bytes == null) {
            return null;
        } else {
            int bytesLength = bytes.length;

            if ((bytesLength % 8) == 0) {
                return bytes;
            } else {
                int newBytesLength = ((int) (bytesLength / 8) + 1) * 8;
                byte[] newBytes = new byte[newBytesLength];

                for (int i = 0; i < bytesLength; i++) {
                    newBytes[i] = bytes[i];
                }

                for (int k = bytesLength; k < newBytesLength; k++) {
                    newBytes[k] = 0;
                }

                return newBytes;
            }
        }
    }

    /**
     * 去掉数组中值为0的元素
     *
     * @param bytes
     * @return
     */
    private byte[] trim(byte[] bytes) {
        if (bytes == null) {
            return null;
        } else {
            int bytesLength = bytes.length;

            int i = bytesLength - 1;

            for (; i >= 0; i--) {
                if (bytes[i] != 0) {
                    break;
                }
            }

            byte[] newBytes = new byte[i + 1];

            for (int k = 0; k <= i; k++) {
                newBytes[k] = bytes[k];
            }

            return newBytes;
        }
    }
}
