package com.hnly.provincial.comm.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "每页条数,分页时使用")
    private long size;
    @Schema(description = "当前页,分页时使用")
    private long current;

    @Schema(name = "", hidden = true)
    public Page<T> page() {
        return new Page<T>(current, size);
    }
}
