package com.hnly.provincial.entity.wateruserecords;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : chenziyan
 * @version : 1.0
 * @Date : 2021-09-30 10:07
 * @description : 获取该区域下今日的累计用水量和今日的用水次数
 ***/
@Data
@Schema(name = "TodayUseWaterAndNumberVO", description = "获取该区域下今日的累计用水量和今日的用水次数")
public class TodayUseWaterAndNumberVO {

    @Schema(description = "今日的累计用水量")
    private BigDecimal useWater;

    @Schema(description = "今日的用水次数")
    private BigDecimal useWaterNumber;

}
