package com.hnly.provincial.config.interceptor.exception;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author maqh
 * @version 1.0
 * @date 2021-09-01
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandle {


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JsonBean<Object> exception(Exception e) {
        log.error(e.getMessage(), e);
        return JsonBean.err(ResultEnum.UNKNOWN_ERR);
    }

    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public JsonBean<Object> handle(Exception e) {
        if (e instanceof MyException) {
            MyException myException = (MyException) e;
            return JsonBean.err(myException.getResultEnum());
        } else {
            return JsonBean.err(ResultEnum.UNKNOWN_ERR);
        }
    }

    /**
     * form data 方式调用接口校验
     */
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public JsonBean<Object> bindExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(o -> o.getField() + ":" + o.getDefaultMessage())
                .collect(Collectors.toList());
        return JsonBean.err(ResultEnum.VALIDATION_ERR, collect);
    }

    /**
     * 处理json 请求体调用接口校验失败抛出的异常
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonBean<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(o -> o.getField() + ":" + o.getDefaultMessage())
                .collect(Collectors.toList());
        return JsonBean.err(ResultEnum.VALIDATION_ERR, collect);
    }

    /**
     * 处理单个参数校验失败抛出的异常
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonBean<Object> constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> collect = constraintViolations.stream()
                .map(o -> o.getMessage())
                .collect(Collectors.toList());
        return JsonBean.err(ResultEnum.VALIDATION_ERR, collect);
    }

}
