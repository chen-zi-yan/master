package com.hnly.provincial.comm;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 统一结果封装类
 * </p>
 *
 * @author maqh
 * @version 1.0
 * @since 2021-05-11
 */
@Data
public class JsonBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code = 200;
    private String message = "success";
    private T data;

    public JsonBean() {
    }

    public JsonBean(T data) {
        this.data = data;
    }

    public JsonBean(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonBean(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> JsonBean<T> success() {
        return new JsonBean<>();
    }

    public static <T> JsonBean<T> success(T o) {
        return new JsonBean<>(o);
    }

    public static <T> JsonBean<T> success(ResultEnum result) {
        return new JsonBean<>(result.getCode(), result.getMsg());
    }

    public static <T> JsonBean<T> err() {
        return new JsonBean<>(ResultEnum.SAVE_ERROR.getCode(), ResultEnum.SAVE_ERROR.getMsg());
    }

    public static <T> JsonBean<T> err(ResultEnum result, T o) {
        return new JsonBean<>(result.getCode(), result.getMsg(), o);
    }

    public static <T> JsonBean<T> err(ResultEnum result) {
        return new JsonBean<>(result.getCode(), result.getMsg());
    }
}
