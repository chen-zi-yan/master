package com.hnly.provincial.entity.wateruserecords;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : chenziyan
 * @version : 1.0
 * @Date : 2021-09-23 15:45
 ***/
@Data
@Schema(name = "YearSunWaterVO", description = "年累计用水量")
public class YearSunWaterVO {

    @Schema(description = "今年累计用水量")
    private double yearSum;

    @Schema(description = "与上年相比用水量")
    private double discrepancy;

}
