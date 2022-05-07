package com.mx.spring.dev.result;

import com.mx.spring.dev.code.Code;
import com.mx.spring.dev.support.page.Pages;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description: 通用请求返回数据-响应信息主体
 */
@ApiModel(value = "MxView", description = "通用请求返回数据-响应信息主体")
public class MxView<T> implements Serializable {

    @ApiModelProperty(value = "错误码")
    private String code;

    @ApiModelProperty(value = "错误信息")
    private String msg;

    @ApiModelProperty(value = "返回数据")
    private T data;


    private MxView() {
    }

    public static <T> MxView<Pages<T>> list(Pages<T> pages) {
        return MxView.ok(pages);
    }


    public static <T> MxView<T> ok(String code, String msg, T data) {
        MxView<T> result = new MxView<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> MxView<T> ok(T data) {
        return ok(Code.SUCCESS.getCode(), Code.SUCCESS.getMsg(), data);
    }

    public static <T> MxView<T> ok(String code, String msg) {
        return ok(code, msg, null);
    }


    public static <T> MxView<T> fail(String code, String msg, T data) {
        MxView<T> result = new MxView<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> MxView<T> fail(T data) {
        return ok(Code.ERROR.getCode(), Code.ERROR.getMsg(), data);
    }

    public static <T> MxView<T> fail(String code, String msg) {
        return fail(code, msg, null);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
