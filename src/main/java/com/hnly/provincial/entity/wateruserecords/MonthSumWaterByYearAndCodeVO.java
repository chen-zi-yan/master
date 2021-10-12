package com.hnly.provincial.entity.wateruserecords;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : chenziyan
 * @version : 1.0
 * @Date : 2021-10-12 10:07
 * @description : 获取该区域该年每月的累计用水量
 ***/
@Data
@Schema(name = "MonthSumWaterByYearAndCodeVO", description = "获取该区域该年每月的累计用水量")
public class MonthSumWaterByYearAndCodeVO {

    @Schema(description = "月份")
    private String month;

    @Schema(description = "用水量")
    private BigDecimal useWater;
}
