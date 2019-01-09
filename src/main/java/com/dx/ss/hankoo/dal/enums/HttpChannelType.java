package com.dx.ss.hankoo.dal.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 请求渠道类型枚举
 */
@Getter
public enum HttpChannelType {

    APP("app", "APP"), 
    WEB("web", "WEB"), 
    H5("h5", "H5");

    private String type;

    private String detail;

    HttpChannelType(String type, String detail) {
        this.type = type;
        this.detail = detail;
    }

    /**
     * 获得枚举
     */
    public static HttpChannelType getEnumByType(String type) {

        if (StringUtils.isNotBlank(type)) {
            for (HttpChannelType channelType : HttpChannelType.values()) {
                if (StringUtils.equals(type, channelType.getType())) {
                    return channelType;
                }
            }
        }
        return null;
    }

    public static String getDetailByType(String type) {

        if (StringUtils.isNotBlank(type)) {
            for (HttpChannelType channelType : HttpChannelType.values()) {
                if (StringUtils.equals(type, channelType.getType())) {
                    return channelType.getDetail();
                }
            }
        }
        return null;
    }

}
