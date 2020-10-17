package com.cuterwrite.dbfinal.dto;


import javax.validation.constraints.NotBlank;

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
	@NotBlank(message = "用户名不能为空")
	private String username;
	@NotBlank(message = "密码不能为空")
	private String password;
}
