package com.cuterwrite.dbfinal.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**  
 * @author Pang S.Z.
 * @create 2020-12-18 16:55:14 
 */
@Data
public class FindPwdParam {
	@NotBlank(message = "用户名不能为空")
	private String username;
	@NotBlank(message = "新密码不能为空")
	private String newPassword;
	@NotBlank(message = "验证码不能为空")
	private String code;
}
