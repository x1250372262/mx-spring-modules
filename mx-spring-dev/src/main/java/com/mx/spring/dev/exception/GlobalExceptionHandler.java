package com.mx.spring.dev.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.hutool.core.util.StrUtil;
import com.mx.spring.dev.code.Code;
import com.mx.spring.dev.result.Result;
import com.mx.spring.dev.support.log.MxLog;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description: 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result handleException(HttpRequestMethodNotSupportedException e) {
        MxLog.error(e.getMessage(), e);
        return Result.create(Code.NOT_SUPPORT_REQUEST.getCode()).msg(StrUtil.format(Code.NOT_SUPPORT_REQUEST.getMsg(), e.getMethod()));
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result notFount(RuntimeException e) {
        MxLog.error("运行时异常:", e);
        return Result.create(Code.SYSTEM_ERROR.getCode()).msg("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        MxLog.error(e.getMessage(), e);
        return Result.create(Code.SYSTEM_ERROR.getCode()).msg(Code.SYSTEM_ERROR.getMsg());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(MxException.class)
    public Result businessException(MxException e) {
        MxLog.error(e.getMessage(), e);
        return Result.create(Code.SYSTEM_ERROR.getCode()).msg(e.getMessage());
    }

    /**
     * 登录异常
     */
    @ExceptionHandler(NotLoginException.class)
    public Result notLoginException(HttpServletRequest request, NotLoginException e) {
        MxLog.error(e.getMessage(), e);
        return Result.create(Code.NOT_LOGIN.getCode()).msg(Code.NOT_LOGIN.getMsg());
    }

    /**
     * 权限
     */
    @ExceptionHandler({NotPermissionException.class, NotRoleException.class})
    public Result notPermissionException(HttpServletRequest request, Exception e) {
        MxLog.error(e.getMessage(), e);
        return Result.create(Code.NOT_PERMISSION.getCode()).msg(Code.NOT_PERMISSION.getMsg());
    }


    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return Result.create(Code.INVALID_PARAMETER.getCode()).msg(message);
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result handleValidException(ConstraintViolationException e) {
        MxLog.warn("ConstraintViolationException:", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return Result.create(Code.INVALID_PARAMETER.getCode()).msg(message);
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public Result handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return Result.create(Code.INVALID_PARAMETER.getCode()).msg(message);
    }


}
