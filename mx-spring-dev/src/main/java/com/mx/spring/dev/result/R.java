package com.mx.spring.dev.result;

import cn.hutool.core.collection.CollectionUtil;
import com.mx.spring.dev.code.C;
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
 * @Description:
 */
@ApiModel(value = "R", description = "通用请求返回数据-操作消息提醒")
public class R implements Serializable {

    @ApiModelProperty(value = "错误码")
    private int code;

    @ApiModelProperty(value = "错误信息")
    private String msg;

    @ApiModelProperty(value = "其他参数")
    private Map<String, Object> attrs = new HashMap<>();

    private R() {
    }

    private R(int code) {
        this.code = code;
    }

    public static boolean check(R r) {
        return r.code() == C.SUCCESS.getCode();
    }

    public R none() {
        return R.create(-10);
    }

    /**
     * 根据参数返回成功还是失败
     */
    public static R result(Object object) {
        if (object != null) {
            return R.ok();
        }
        return R.fail();
    }

    /**
     * 根据参数返回成功还是失败
     */
    public static R result(int result) {
        if (result >= 0) {
            return R.ok();
        }
        return R.fail();
    }

    /**
     * 根据参数返回成功还是失败
     */
    public static R result(List<?> list) {
        if (CollectionUtil.isNotEmpty(list)) {
            return R.ok();
        }
        return R.fail();
    }

    /**
     * 根据参数返回成功还是失败
     */
    public static R result(int[] result) {
        if (result != null && result.length > 0) {
            return R.ok();
        }
        return R.fail();
    }

    /**
     * 成功result
     */
    public static R ok() {
        return R.create(C.SUCCESS.getCode())
                .msg(C.SUCCESS.getMsg());
    }

    /**
     * 返回成功 并且带map参数
     */
    public static R mapResult(Map<String, Object> attrs) {
        return R.ok().attrs(attrs);
    }

    /**
     * 失败result
     */
    public static R fail() {
        return R.create(C.ERROR.getCode())
                .msg(C.ERROR.getMsg());
    }

    public static R create() {
        return new R();
    }

    public static R create(int code) {
        return new R(code);
    }

    public static R sameName() {
        return R.create(C.FIELDS_EXISTS.getCode()).msg(String.format(C.FIELDS_EXISTS.getMsg(), "名称"));
    }

    public static R sameData(String msg) {
        return R.create(C.FIELDS_EXISTS.getCode()).msg(String.format(C.FIELDS_EXISTS.getMsg(), msg));
    }

    public static R noVersion() {
        return R.create(C.VERSION_NOT_SAME.getCode()).msg(C.VERSION_NOT_SAME.getMsg());
    }

    public static R noData() {
        return R.create(C.NO_DATA.getCode()).msg(C.NO_DATA.getMsg());
    }

    public static boolean checkVersion(Object var1, Object var2) {
        return Objects.equals(var1, var2);
    }

    public boolean check() {
        return this.code() == C.SUCCESS.getCode();
    }

    public int code() {
        return code;
    }

    public R code(int code) {
        this.code = code;
        return this;
    }

    public String msg() {
        return StringUtils.trimToEmpty(msg);
    }

    public R msg(String msg) {
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

    public R attr(String attrKey, Object attrValue) {
        this.attrs.put(attrKey, attrValue);
        return this;
    }

    public R attr(String attrKey, Object attrValue, Object defaultValue) {
        this.attrs.put(attrKey, ObjectUtils.defaultIfNull(attrValue, defaultValue));
        return this;
    }

    public R attrs(Map<String, Object> attrs) {
        this.attrs = attrs;
        return this;
    }

    public R data(Object value) {
        this.attrs.put("data", value);
        return this;
    }

    public R data(Object value, Object defaultValue) {
        this.attrs.put("data", ObjectUtils.defaultIfNull(value, defaultValue));
        return this;
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

    public Map<String, Object> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, Object> attrs) {
        this.attrs = attrs;
    }
}
