package com.hnly.provincial.entity.wateruserecords;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : chenziyan
 * @version : 1.0
 * @Date : 2021-10-13 11:37
 * @description : 获取该区域合计用水额度合计已用水量合计剩余水量合计已用占比
 ***/
@Data
@Schema(name = "SummationVO", description = "获取该区域合计用水额度合计已用水量合计剩余水量合计已用占比")
public class SummationVO {

    @Schema(description = "合计用水额度")
    private BigDecimal summationUseWaterLimit;

    @Schema(description = "合计已用水量")
    private BigDecimal summationUseWater;

    @Schema(description = "合计剩余水量")
    private BigDecimal summationSurplus;

    @Schema(description = "合计已用占比")
    private BigDecimal summationUseWaterRatio;

}
