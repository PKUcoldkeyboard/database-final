package com.cuterwrite.dbfinal.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**  
 * 修改密码参数
 * @author Pang S.Z.
 * @create 2020-10-05 20:16:48 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ChangePwdParam {
	@NotEmpty
	private String username;
	@NotEmpty
	private String oldPassword;
	@NotEmpty
	private String newPassword;
}
