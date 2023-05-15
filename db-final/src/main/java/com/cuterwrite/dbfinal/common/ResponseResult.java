package com.cuterwrite.dbfinal.common;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
/**
 * 	统一结果封装类
 * @author Pang S.Z.
 * @create 2020-09-26 16:19:24
 */
public class ResponseResult {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    private ResponseResult() {
    }

    //通用返回成功
    public static ResponseResult ok() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(ResultCode.SUCCESS.getSuccess());
        responseResult.setCode(ResultCode.SUCCESS.getCode());
        responseResult.setMessage(ResultCode.SUCCESS.getMessage());
        return responseResult;
    }

    // 通用返回失败，未知错误
    public static ResponseResult error() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(ResultCode.UNKNOWN_ERROR.getSuccess());
        responseResult.setCode(ResultCode.UNKNOWN_ERROR.getCode());
        responseResult.setMessage(ResultCode.UNKNOWN_ERROR.getMessage());
        return responseResult;
    }

    // 设置结果，形参为结果枚举
    public static ResponseResult setResult(ResultCode result) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(result.getSuccess());
        responseResult.setCode(result.getCode());
        responseResult.setMessage(result.getMessage());
        return responseResult;
    }

    /**
     * 自定义模块
     **/

    //自定义返回数据
    public ResponseResult data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    //通用设置data
    public ResponseResult data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    //自定义状态信息
    public ResponseResult message(String message) {
        this.setMessage(message);
        return this;
    }

    //自定义状态码
    public ResponseResult code(Integer code) {
        this.setCode(code);
        return this;
    }

    //自定义返回结果
    public ResponseResult success(Boolean success) {
        this.setSuccess(success);
        return this;
    }
}
