package com.dx.ss.hankoo.common.utils;

/**
 * 计算
 * @author 卡内齐 (kaneiqi@dajiaok.com)
 * @version $Id: BaseCal.java, v 0.1 2014-6-4 下午01:06:54 kaneiqi Exp $
 */
public class BaseCal {
    /**
     * 字节数组转换成十六进制字符串
     *
     * @param b 字节数组
     *
     * @return 十六进制字符串
     */
    public static String byte2hex(byte[] b) {
        String hs   = "";
        String stmp = "";

        for (int i = 0; i < b.length; i++) {
            stmp = Integer.toHexString(b[i] & 0xFF);

            if (stmp.length() == 1) {
                hs += ("0" + stmp);
            } else {
                hs += stmp;
            }
        }

        return hs.toUpperCase();
    }

    /**
     * 十六进制字符串转换成字节数组
     *
     * @param hex 十六进制字符串
     *
     * @return 字节数组
     *
     * @throws IllegalArgumentException
     */
    public static byte[] hex2byte(String hex) throws IllegalArgumentException {
        if ((hex.length() % 2) != 0) {
            throw new IllegalArgumentException();
        }

        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];

        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap    = "" + arr[i++] + arr[i];
            int    byteint = Integer.parseInt(swap, 16) & 0xFF;

            b[j] = new Integer(byteint).byteValue();
        }

        return b;
    }
}
