package com.cuterwrite.dbfinal.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @author
 */
@Data
public class UserRoleKey implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 权限id
     */
    private Integer roleId;
}