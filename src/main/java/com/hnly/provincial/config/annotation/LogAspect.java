package com.hnly.provincial.config.annotation;

import com.hnly.provincial.comm.user.CommonUser;
import com.hnly.provincial.entity.log.UserOperateLog;
import com.hnly.provincial.entity.user.User;
import com.hnly.provincial.service.log.IUserOperateLogService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author maqh
 * @version 1.0
 * @date 2021-09-22
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Resource
    private CommonUser commonUser;

    @Value("${saveLog}")
    private boolean saveLog;

    @Resource
    private IUserOperateLogService userOperateLogService;

    @Around("@annotation(io.swagger.v3.oas.annotations.Operation)")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        long beginTime = System.currentTimeMillis();

        Object proceed = point.proceed();

        //拦截异常，防止一些特殊情况导致程序不能正常进行
        try {
            //去配置文件内容设置是否保存操作日志
            if (saveLog) {
                saveLog(point, System.currentTimeMillis() - beginTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proceed;
    }


    private void saveLog(ProceedingJoinPoint point, long time) {

        UserOperateLog userLog = new UserOperateLog();

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Operation annotation = method.getAnnotation(Operation.class);
        if (annotation != null) {
            userLog.setContent(annotation.summary());
        }
        Object[] args = point.getArgs();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = u.getParameterNames(method);
        if (args != null && parameterNames != null) {
            StringBuilder params = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                params.append(parameterNames[i]).append(":").append(args[i]).append(" ");
            }
            userLog.setParam(params.toString());
        }

        String name = point.getTarget().getClass().getName();
        userLog.setMethod(name + "." + signature.getName() + "()");
        userLog.setClientIp(commonUser.getIp());
        userLog.setUrl(commonUser.getServletPath());
        userLog.setUseTime(time + "");
        userLog.setRequestMethod(commonUser.getRequestMethod());
        User user = commonUser.getUser();
        if (user.getId() != null) {
            userLog.setUserName(user.getUsername());
            userLog.setUserId(user.getId());
        }
        userOperateLogService.save(userLog);
    }
}
