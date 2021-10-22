package com.mx.spring.dev.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.mx.spring.dev.code.C;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public R handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return R.create(C.SYSTEM_ERROR.getCode()).msg("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public R notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return R.create(C.SYSTEM_ERROR.getCode()).msg("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.create(C.SYSTEM_ERROR.getCode()).msg(C.SYSTEM_ERROR.getMsg());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(MxException.class)
    public R businessException(MxException e) {
        log.error(e.getMessage(), e);
        return R.create(C.SYSTEM_ERROR.getCode()).msg(e.getMessage());
    }

    /**
     * 登录异常
     */
    @ExceptionHandler(NotLoginException.class)
    public R notLoginException(HttpServletRequest request, NotLoginException e) {
        log.error(e.getMessage(), e);
        return R.create(C.NOT_LOGIN.getCode()).msg(C.NOT_LOGIN.getMsg());
    }

    /**
     * 权限
     */
    @ExceptionHandler({NotPermissionException.class, NotRoleException.class})
    public R notPermissionException(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        return R.create(C.NOT_PERMISSION.getCode()).msg(C.NOT_PERMISSION.getMsg());
    }



    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return R.create(C.INVALID_PARAMETER.getCode()).msg(message);
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public R handleValidException(ConstraintViolationException e) {
        log.warn("ConstraintViolationException:", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return R.create(C.INVALID_PARAMETER.getCode()).msg(message);
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public R handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return R.create(C.INVALID_PARAMETER.getCode()).msg(message);
    }


}
