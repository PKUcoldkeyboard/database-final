package com.cuterwrite.dbfinal.exception;

import com.cuterwrite.dbfinal.common.ResultCode;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 
 * @author Pang S.Z.
 * @create 2020-09-26 16:53:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CMSException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private Integer code;
	
	public CMSException(Integer code,String message) {
		super(message);
		this.code=code;
	}
	
	public CMSException(ResultCode resultCode) {
		super(resultCode.getMessage());
		this.code=resultCode.getCode();
	}
}
