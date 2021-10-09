package com.hnly.provincial.entity.wateruserecords;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : chenziyan
 * @version : 1.0
 * @Date : 2021-09-30 10:07
 * @description : 获取该区域的累计灌溉开井次数和累计用水量
 ***/
@Data
@Schema(name = "AccumulativeUseWaterAndNumberVO", description = "获取该区域的累计灌溉开井次数和累计用水量")
public class AccumulativeUseWaterAndNumberVO {

    @Schema(description = "累计灌溉开井次数")
    private BigDecimal wellOpening;
    ;

    @Schema(description = "累计用水量")
    private BigDecimal sumUseWater;

}
