package com.dx.ss.hankoo.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

@Slf4j
@Component
public class SignUtils {

    private static TripleDES tripleDES;

    private static String key = "yoogurt_#163g%(^$";

    public SignUtils() {
    }

    @PostConstruct
    public void init() {
        try {
            tripleDES = new TripleDES(buildKey(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 3DES 加密的key必须是24byte，不多也不少
     *
     * @param salt
     * @return
     * @throws UnsupportedEncodingException
     * @author liu.weihao
     * @date 2016年12月13日
     */
    private String buildKey(String salt) throws UnsupportedEncodingException {
        byte[] key = new byte[24]; // 声明一个24位的字节数组，默认里面都是0
        byte[] temp = salt.getBytes("UTF-8"); // 将字符串转成字节数组

        if (key.length > temp.length) {
            // 如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, temp.length);
        } else {
            // 如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return new String(key, "utf-8");
    }

    /**
     * <li>init-method 与afterPropertiesSet 都是在初始化bean的时候执行</li> <li>
     * 执行顺序是afterPropertiesSet 先执行，init-method 后执行</li> <li>必须实现
     * InitializingBean接口</li>
     *
     * @throws Exception
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */

    /**
     * 加签
     *
     * @param src
     * @return
     */
    public static String encrypt(String src) {
        try {
            return tripleDES.encrypt(StringUtils.trim(src));
        } catch (Exception ex) {
            log.error(src + "签名异常", ex);
        }
        return "";
    }

    /**
     * 解签
     *
     * @param src
     * @return
     */
    public static String decrypt(String src) {
        try {
            return tripleDES.decrypt(StringUtils.trim(src));
        } catch (Exception ex) {
            log.error(src + "解签异常", ex);
        }
        return "";
    }

}
