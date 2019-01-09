package com.dx.ss.hankoo.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regular Expressions
 *
 * @author liu.weihao
 * @version V1.0
 * @ClassName: RegUtil
 * @date 2016年11月29日
 */
public class RegUtil {

    /**
     * <p class="detail">
     * 验证Email
     * </p>
     *
     * @param email email地址，格式：zhangsan@zuidaima.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
     * @return 验证成功返回true，验证失败返回false
     * @author liuwh
     * @date 2015-8-11
     */
    public static boolean checkEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex, email);
    }

    /**
     * <p class="detail">
     * 验证身份证号码
     * </p>
     *
     * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @return 验证成功返回true，验证失败返回false
     * @author liuwh
     * @date 2015-8-11
     */
    public static boolean checkIdCard(String idCard) {
        if (StringUtils.isBlank(idCard)) {
            return false;
        }
        String regex = "[1-9]\\d{13,16}[X|x]{1}";
        return Pattern.matches(regex, idCard);
    }

    /**
     * <p class="detail">
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     * </p>
     *
     * @param mobile 移动、联通、电信运营商的号码段
     *               <p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *               、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
     *               <p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
     *               <p>电信的号段：133、153、180（未启用）、189</p>
     * @return 验证成功返回true，验证失败返回false
     * @author liuwh
     * @date 2015-8-11
     */
    public static boolean checkMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return false;
        }
        String regex = "(\\+\\d+)?1[3456789]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }

    /**
     * <p class="detail">
     * 验证固定电话号码
     * </p>
     *
     * @param phone 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
     *              <p><b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
     *              数字之后是空格分隔的国家（地区）代码。</p>
     *              <p><b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
     *              对不使用地区或城市代码的国家（地区），则省略该组件。</p>
     *              <p><b>电话号码：</b>这包含从 0 到 9 的一个或多个数字 </p>
     * @return 验证成功返回true，验证失败返回false
     * @author liuwh
     * @date 2015-8-11
     */
    public static boolean checkPhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            return false;
        }
        String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
        return Pattern.matches(regex, phone);
    }

    /**
     * <p class="detail">
     * 验证整数（正整数和负整数）
     * </p>
     *
     * @param digit 一位或多位0-9之间的整数
     * @return 验证成功返回true，验证失败返回false
     * @author liuwh
     */
    public static boolean checkDigit(String digit) {
        if (StringUtils.isBlank(digit)) {
            return false;
        }
        String regex = "-?\\d+$";
        return Pattern.matches(regex, digit);
    }

    /**
     * <p class="detail">
     * 验证整数和浮点数（正负整数和正负浮点数）
     * </p>
     *
     * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDecimals(String decimals) {
        if (StringUtils.isBlank(decimals)) {
            return false;
        }
        String regex = "^\\d+(\\.\\d+)?$";
        return Pattern.matches(regex, decimals);
    }

    /**
     * <p class="detail">
     * 功能：检查是否是金额
     * </p>
     *
     * @param money
     * @return
     * @author liu.weihao
     * @date 2016-10-12
     */
    public static boolean checkMoney(String money) {
        if (StringUtils.isBlank(money)) {
            return false;
        }
        String regex = "^([0-9][\\d]{0,7}|0)(\\.[\\d]{1,2})?$";
        return Pattern.matches(regex, money);
    }

    /**
     * <p class="detail">
     * 验证空白字符
     * </p>
     *
     * @param blankSpace 空白字符，包括：空格、\t、\n、\r、\f、\x0B
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBlankSpace(String blankSpace) {
        if (StringUtils.isBlank(blankSpace)) {
            return false;
        }
        String regex = "\\s+";
        return Pattern.matches(regex, blankSpace);
    }

    /**
     * <p class="detail">
     * 验证中文
     * </p>
     *
     * @param chinese 中文字符
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkChinese(String chinese) {
        if (StringUtils.isBlank(chinese)) {
            return false;
        }
        String regex = "^[\u4E00-\u9FA5]+$";
        return Pattern.matches(regex, chinese);
    }

    /**
     * <p class="detail">
     * 验证日期（年月日）
     * </p>
     *
     * @param birthday 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBirthday(String birthday) {
        if (StringUtils.isBlank(birthday)) {
            return false;
        }
        String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
        return Pattern.matches(regex, birthday);
    }

    /**
     * <p class="detail">
     * 验证URL地址
     * </p>
     *
     * @param url
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkURL(String url) {
        if (StringUtils.isBlank(url)) {
            return false;
        }
        String regex = "\\b(([\\w-]+://?|www[.])[^\\s()<>]+(?:\\([\\w\\d]+\\)|([^[:punct:]\\s]|/)))";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return p.matcher(url).find();
    }

    /**
     * <p class="detail">
     * 获取网址 URL 的一级域名
     * http://www.zuidaima.com/share/1550463379442688.htm ->> zuidaima.com
     * </p>
     * <pre>
     * </pre>
     *
     * @param url
     * @return
     */
    public static String getDomain(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        String regex = "(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        // 获取完整的域名  
        // Pattern p=Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);  
        Matcher matcher = p.matcher(url);
        matcher.find();
        return matcher.group();
    }

    /**
     * <p class="detail">
     * 匹配中国邮政编码
     * </p>
     *
     * @param postcode 邮政编码
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkPostcode(String postcode) {
        if (StringUtils.isBlank(postcode)) {
            return false;
        }
        String regex = "[1-9]\\d{5}";
        return Pattern.matches(regex, postcode);
    }

    /**
     * <p class="detail">
     * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
     * </p>
     *
     * @param ipAddress IPv4标准地址
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIpAddress(String ipAddress) {
        if (StringUtils.isBlank(ipAddress)) {
            return false;
        }
        String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
        return Pattern.matches(regex, ipAddress);
    }

    /**
     * 正则表达式验证昵称
     * <strong>限16个字符，支持中英文、数字、减号或下划线</strong>
     *
     * @param nickName
     * @return
     */
    public static boolean checkNickName(String nickName) {
        if (StringUtils.isBlank(nickName)) {
            return false;
        }
        String regex = "^[\\u4e00-\\u9fa5_a-zA-Z0-9-]{1,16}$";
        return Pattern.matches(regex, nickName);
    }

    /**
     * 正则表达式验证密码
     * <strong>6-20 位，字母、数字、符号，包含任意两种</strong>
     *
     * @param password
     * @return
     */
    public static boolean checkPassword(String password) {
        if (StringUtils.isBlank(password)) {
            return false;
        }
        String regex = "((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{6,20}$";
        return Pattern.matches(regex, password);
    }

}
