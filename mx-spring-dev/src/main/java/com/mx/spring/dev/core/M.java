package com.mx.spring.dev.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.code.C;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
@ApiModel(value = "M", description = "通用请求返回数据-响应信息主体")
public class M<T> implements Serializable {

    @ApiModelProperty(value = "错误码")
    private int code;

    @ApiModelProperty(value = "错误信息")
    private String msg;

    @ApiModelProperty(value = "返回数据")
    private T data;


    private M() {
    }

    public static <T> M<Pages<T>> list(Page<T> page) {
        Pages<T> pages = new Pages<>(page.getCurrent(),
                page.getSize(),
                page.getPages(),
                page.getTotal(),
                page.getRecords());
        return M.ok(pages);
    }

    public static <T> M<Pages<T>> list(Pages<T> pages) {
        return M.ok(pages);
    }


    public static <T> M<T> ok(int code, String msg, T data) {
        M<T> result = new M<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> M<T> ok(int code, T data) {
        return ok(code, C.SUCCESS.getMsg(), data);
    }

    public static <T> M<T> ok(String msg, T data) {
        return ok(C.SUCCESS.getCode(), msg, data);
    }

    public static <T> M<T> ok(T data) {
        return ok(C.SUCCESS.getCode(), C.SUCCESS.getMsg(), data);
    }

    public static <T> M<T> ok(int code, String msg) {
        return ok(code, msg, null);
    }


    public static <T> M<T> fail(int code, String msg, T data) {
        M<T> result = new M<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> M<T> fail(int code, T data) {
        return ok(code, C.ERROR.getMsg(), data);
    }

    public static <T> M<T> fail(String msg, T data) {
        return ok(C.ERROR.getCode(), msg, data);
    }

    public static <T> M<T> fail(T data) {
        return ok(C.ERROR.getCode(), C.ERROR.getMsg(), data);
    }

    public static <T> M<T> fail(int code, String msg) {
        return fail(code, msg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
