package com.nyuen.dxtest.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;

/**
 * @description 统一 API响应结果封装
 */
public class Result {
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应提示信息
     */
    private String message;
    /**
     * 响应结果对象
     */
    private Object data;

    Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toJson() {
        String dataFormat = "{\"code\":${0},\"message\":${1},\"data\":${2}}";
        dataFormat = dataFormat.replace("${0}", null == this.code ? "" :this.code.toString());
        dataFormat = dataFormat.replace("${1}", null == this.message ?"": JSON.toJSONString(this.message));
        dataFormat = dataFormat.replace("${2}", null == this.data ?"": JSON.toJSONString(this.data));
        return dataFormat;
    }

    public static void main(String[] args){
        Result r = new Result(1,"1","1");
        System.out.println(JSON.parse(r.toJson()));
    }
}

