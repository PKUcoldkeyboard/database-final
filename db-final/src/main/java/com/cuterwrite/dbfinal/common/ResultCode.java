package com.cuterwrite.dbfinal.common;

import lombok.Getter;

/**
 * 异常信息枚举类
 *
 * @author Pang S.Z.
 * @create 2020-09-26 16:14:30
 */
@Getter
public enum ResultCode {
    SUCCESS(true, 20000, "操作成功"),
    UNKNOWN_ERROR(false, 20001, "操作失败"),
    PARAM_ERROR(false, 20002, "参数错误"),
    NULL_POINT(false, 20003, "空指针错误"),
    HTTP_CLIENT_ERROR(false, 20004, "HTTP客户端异常"),
    UNAUTHORIZED(false, 20005, "暂未登录或token已经过期"),
    FORBIDDEN(false, 20006, "没有相关权限"),
    VALIDATE_FAILED(false, 20007, "参数检验失败");

    //响应是否成功
    private Boolean success;
    //响应状态码
    private Integer code;
    //响应信息
    private String message;

    ResultCode(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
