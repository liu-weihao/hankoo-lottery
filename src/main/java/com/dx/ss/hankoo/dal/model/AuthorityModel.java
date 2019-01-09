package com.dx.ss.hankoo.dal.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class AuthorityModel implements Serializable {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 权限id
     */
    private Long authorityId;

    /**
     * 权限名
     */
    private String authorityName;

    /**
     * 权限组名
     */
    private String authorityGroup;

    /**
     * 接口uri
     */
    private String uri;

    /**
     * 关联控件
     */
    private String associatedControl;

    @Override
    public String toString() {
        return "AuthorityModel{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", authorityId=" + authorityId +
                ", authorityName='" + authorityName + '\'' +
                ", authorityGroup='" + authorityGroup + '\'' +
                ", uri='" + uri + '\'' +
                ", associatedControl='" + associatedControl + '\'' +
                '}';
    }
}
