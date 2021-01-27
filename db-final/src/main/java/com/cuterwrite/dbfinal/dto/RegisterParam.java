package com.cuterwrite.dbfinal.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**  
 * @author Pang S.Z.
 * @create 2020-12-18 14:17:07 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterParam {
	@NotBlank(message = "用户名不能为空")
	private String username;
	@NotBlank(message = "密码不能为空")
	private String password;
	@NotBlank(message = "验证码不能为空")
	private String code;
	@NotBlank(message = "邮箱不能为空")
	private String email;
}
