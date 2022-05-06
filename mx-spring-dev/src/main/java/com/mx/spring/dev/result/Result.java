package com.mx.spring.dev.result;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.mx.spring.dev.code.Code;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description: 通用请求返回数据-操作消息提醒
 */
@ApiModel(value = "Result", description = "通用请求返回数据-操作消息提醒")
public class Result implements Serializable {

    @ApiModelProperty(value = "错误码")
    private String code;

    @ApiModelProperty(value = "错误信息")
    private String msg;

    @ApiModelProperty(value = "其他参数")
    private Map<String, Object> attrs = new HashMap<>();

    private Result() {
    }

    private Result(String code) {
        this.code = code;
    }

    public static boolean check(Result result) {
        return Code.SUCCESS.getCode().equals(result.code);
    }

    public boolean check() {
        return Code.SUCCESS.getCode().equals(this.code);
    }

    /**
     * 根据参数返回成功还是失败
     */
    public static Result result(Object object) {
        if (object != null) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 根据参数返回成功还是失败
     */
    public static Result result(int result) {
        if (result >= 1) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 根据参数返回成功还是失败
     */
    public static Result result(List<?> list) {
        if (CollectionUtil.isNotEmpty(list)) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 根据参数返回成功还是失败
     */
    public static Result result(int[] result) {
        if (result != null && result.length > 0) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 成功result
     */
    public static Result ok() {
        return Result.create(Code.SUCCESS.getCode())
                .msg(Code.SUCCESS.getMsg());
    }

    /**
     * 返回成功 并且带map参数
     */
    public static Result mapResult(Map<String, Object> attrs) {
        return Result.ok().attrs(attrs);
    }

    /**
     * 失败result
     */
    public static Result fail() {
        return Result.create(Code.ERROR.getCode())
                .msg(Code.ERROR.getMsg());
    }

    public static Result create() {
        return new Result();
    }

    public static Result create(String code) {
        return new Result(code);
    }


    public static Result sameName() {
        return Result.create(Code.FIELDS_EXISTS.getCode()).msg(StrUtil.format(Code.FIELDS_EXISTS.getMsg(), "名称"));
    }

    public static Result sameData(String msg) {
        return Result.create(Code.FIELDS_EXISTS.getCode()).msg(String.format(Code.FIELDS_EXISTS.getMsg(), msg));
    }

    public static Result noVersion() {
        return Result.create(Code.VERSION_NOT_SAME.getCode()).msg(Code.VERSION_NOT_SAME.getMsg());
    }

    public static Result noData() {
        return Result.create(Code.NO_DATA.getCode()).msg(Code.NO_DATA.getMsg());
    }

    public static boolean checkVersion(Object var1, Object var2) {
        return Objects.equals(var1, var2);
    }


    public String code() {
        return code;
    }

    public Result code(String code) {
        this.code = code;
        return this;
    }

    public String msg() {
        return StringUtils.trimToEmpty(msg);
    }

    public Result msg(String msg) {
        this.msg = msg;
        return this;
    }

    public Map<String, Object> attrs() {
        return attrs;
    }

    @SuppressWarnings("unchecked")
    public <T> T attr(String attrKey) {
        return (T) this.attrs.get(attrKey);
    }

    public Result attr(String attrKey, Object attrValue) {
        this.attrs.put(attrKey, attrValue);
        return this;
    }

    public Result attr(String attrKey, Object attrValue, Object defaultValue) {
        this.attrs.put(attrKey, ObjectUtils.defaultIfNull(attrValue, defaultValue));
        return this;
    }

    public Result attrs(Map<String, Object> attrs) {
        this.attrs = attrs;
        return this;
    }

    public Result data(Object value) {
        this.attrs.put("data", value);
        return this;
    }

    public Result data(Object value, Object defaultValue) {
        this.attrs.put("data", ObjectUtils.defaultIfNull(value, defaultValue));
        return this;
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

    public Map<String, Object> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, Object> attrs) {
        this.attrs = attrs;
    }
}
