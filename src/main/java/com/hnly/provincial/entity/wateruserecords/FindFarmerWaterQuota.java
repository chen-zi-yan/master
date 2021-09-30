package com.hnly.provincial.entity.wateruserecords;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : chenziyan
 * @version : 1.0
 * @Date : 2021-09-30 10:07
 * @description : 农户用水一阶二阶额度
 ***/
@Data
@Schema(name = "FindFarmerWaterQuota", description = "农户用水额度")
public class FindFarmerWaterQuota {

    @Schema(description = "一阶段用水额度")
    private BigDecimal firstOrderTotal;

    @Schema(description = "二阶段用水额度")
    private BigDecimal secondOrderTotal;

}
