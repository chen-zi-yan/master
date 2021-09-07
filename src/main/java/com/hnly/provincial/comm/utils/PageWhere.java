package com.hnly.provincial.comm.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class PageWhere<T> {

    @ApiModelProperty(value = "每页条数")
    private long size;

    @ApiModelProperty(value = "当前页")
    private long current;

    @ApiModelProperty(value = "", hidden = true)
    public Page<T> page() {
        return new Page<T>(current, size);
    }
}
