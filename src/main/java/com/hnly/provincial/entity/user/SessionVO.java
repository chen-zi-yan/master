package com.hnly.provincial.entity.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author maqh
 * @version 1.0
 * @date 2021-09-17
 */
@Data
public class SessionVO {

    @Schema(description = "token")
    private String token;

    @Schema(description = "有效期毫秒")
    private Long validPeriod;

    @Schema(description = "登陆用户名称")
    private String name;


}
