package com.hnly.provincial.comm.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author maqh
 * @version 1.0
 * @since 2021-05-26
 */
@Data
public class TableDataUtils<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long total;
    private T data;

    public TableDataUtils(Long total, T data) {
        this.total = total;
        this.data = data;
    }

    public static <T> TableDataUtils<T> success(Long total, T data) {
        return new TableDataUtils<>(total, data);
    }
}
