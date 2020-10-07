package com.cuterwrite.dbfinal.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class UserRoleKey implements Serializable {
    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 权限id
     */
    private Integer roleId;

    private static final long serialVersionUID = 1L;
}