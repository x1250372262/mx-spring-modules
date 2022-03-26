package com.mx.spring.aspect;//package com.mx.spring.dev.aspect;
//
//import cn.hutool.core.convert.Convert;
//import cn.hutool.core.util.StrUtil;
//import com.mx.spring.dev.annotation.UniqueSubmit;
//import com.mx.spring.dev.code.C;
//import com.mx.spring.dev.core.M;
//import com.mx.spring.dev.core.R;
//import com.mx.spring.dev.support.redis.api.IRedisService;
//import com.mx.spring.dev.support.security.SaUtils;
//import com.mx.spring.dev.util.WebUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Aspect
//@Component
//public class UniqueSubmitAspect {
//
//    @Autowired
//    private IRedisService iRedisService;
//    @Autowired
//    private SaUtils saUtils;
//
//    @Pointcut("@annotation(com.mx.spring.dev.annotation.UniqueSubmit)")
//    public void pointCut() {
//    }
//
//    @Around("pointCut()")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        HttpServletRequest request = WebUtils.request();
//        String loginId = saUtils.loginId();
//        if(StringUtils.isBlank(loginId)){
//            return pjp.proceed();
//        }
//        String key = loginId + "-" + request.getServletPath();
//        UniqueSubmit annotation = ((MethodSignature) pjp.getSignature()).getMethod().getAnnotation(UniqueSubmit.class);
//        Long time = System.currentTimeMillis() + annotation.value();
//        //加锁
//        boolean islock = iRedisService.lock(key, Convert.toStr(time));
//        if (islock) {
//            Object result;
//            try {
//                result = pjp.proceed();
//            } finally {
//                //解锁
//                iRedisService.unlock(key, Convert.toStr(time));
//            }
//            return result;
//        } else {
//            String msg = StrUtil.format(C.NOT_REPEAT_REQUEST.getMsg(), annotation.value());
//            int code = C.NOT_REPEAT_REQUEST.getCode();
//            if (UniqueSubmit.RetType.M.equals(annotation.retType())) {
//                return M.fail(code, msg);
//            } else {
//                return R.create(code).msg(msg);
//            }
//
//        }
//    }
//}