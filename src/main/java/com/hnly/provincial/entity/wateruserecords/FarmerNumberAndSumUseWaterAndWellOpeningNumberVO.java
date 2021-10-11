package com.hnly.provincial.entity.wateruserecords;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : chenziyan
 * @version : 1.0
 * @Date : 2021-10-10 10:07
 * @description : 获取该区域的累计用水农户数和累计灌溉开井次数和累计用水量
 ***/
@Data
@Schema(name = "FarmerNumberAndSumUseWaterAndWellOpeningNumberVO", description = "获取该区域的累计用水农户数和累计灌溉开井次数和累计用水量")
public class FarmerNumberAndSumUseWaterAndWellOpeningNumberVO {

    @Schema(description = "累计用水农户数")
    private BigDecimal sumUseWaterFarmer;

    @Schema(description = "累计用水量")
    private BigDecimal sumUseWater;

    @Schema(description = "累计灌溉开井次数")
    private BigDecimal wellOpening;

}
