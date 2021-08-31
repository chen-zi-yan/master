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
public class PageWhere {

    @ApiModelProperty(value = "每页条数", required = true)
    private long size;
    @ApiModelProperty(value = "当前页", required = true)
    private long current;

    @ApiModelProperty(value = "", hidden = true)
    public Page page() {
        return new Page<>(current, size);
    }
}
