package com.hnly.provincial.comm.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @NotEmpty
    @ApiModelProperty(value = "每页条数", required = true)
    private long size;
    @NotNull
    @NotEmpty
    @ApiModelProperty(value = "当前页", required = true)
    private long current;

    @ApiModelProperty(value = "", hidden = true)
    public Page page() {
        return new Page<>(current, size);
    }
}
