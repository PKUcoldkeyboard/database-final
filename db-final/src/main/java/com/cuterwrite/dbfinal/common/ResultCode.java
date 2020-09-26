package com.cuterwrite.dbfinal.common;

import lombok.Getter;

/**
 * 
 * @author Pang S.Z.
 * @create 2020-09-26 16:14:30
 */
@Getter
public enum ResultCode {
	SUCCESS(true,20000,"成功"),
	UNKNOWN_ERROR(false,20001,"未知错误"),
	PARAM_ERROR(false,20002,"参数错误"),
	NULL_POINT(false,20003,"空指针错误"),
	HTTP_CLIENT_ERROR(false,20004,"HTTP客户端异常");
	
	//响应是否成功
	private Boolean success;
	//响应状态码
	private Integer code;
	//响应信息
	private String message;
	
	ResultCode(boolean success,Integer code,String message){
		this.success=success;
		this.code=code;
		this.message=message;
	}
}
