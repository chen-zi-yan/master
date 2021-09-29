package com.hnly.provincial.entity.wateruserecords;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author : chenziyan
 * @version : 1.0
 * @Date : 2021-09-23 15:45
 * @description :每月的用水量
 ***/
@Data
@Schema(name = "MonthSumWaterVO", description = "每月的用水量")
public class MonthSumWaterVO {

    @Schema(description = "今年每月用水量")
    private List<Object> year;

    @Schema(description = "去年每月用水量")
    private List<Object> lastYear;

}
