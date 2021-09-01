package com.hnly.provincial.config.interceptor.exception;

import com.hnly.provincial.comm.ResultEnum;

/**
 * 自定义异常
 * @author maqh
 * @version 1.0
 * @date 2021-09-01
 */
public class MyException extends RuntimeException {
    private ResultEnum resultEnum;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.resultEnum = resultEnum;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }
}
