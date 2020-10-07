package com.cuterwrite.dbfinal.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**  
 * 用户登录参数
 * @author Pang S.Z.
 * @create 2020-10-04 22:38:37 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginParam {	
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
}
