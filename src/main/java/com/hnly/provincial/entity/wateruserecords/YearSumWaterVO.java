package com.hnly.provincial.entity.wateruserecords;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : chenziyan
 * @version : 1.0
 * @Date : 2021-09-23 15:45
 ***/
@Data
@Schema(name = "YearSumWaterVO", description = "年累计用水量")
public class YearSumWaterVO {

    @Schema(description = "今年累计用水量")
    private BigDecimal yearSum;

    @Schema(description = "与上年相比用水量")
    private BigDecimal  discrepancy;

}
