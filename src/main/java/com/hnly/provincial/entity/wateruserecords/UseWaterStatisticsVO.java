package com.hnly.provincial.entity.wateruserecords;

import com.hnly.provincial.comm.utils.PageWhere;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : chenziyan
 * @version : 1.0
 * @Date : 2021-09-26 8:04
 * @description :
 ***/
@Data
@Schema(name = "UseWaterStatistics", description = "灌区用水统计")
public class UseWaterStatisticsVO extends PageWhere<WaterUseRecords> {

    @Schema(description = "年")
    private String year;

    @Schema(description = "单位")
    private String name;

    @Schema(description = "累计已用水量")
    private BigDecimal useWater;

    @Schema(description = "剩余水量")
    private BigDecimal surplus;

    @Schema(description = "已用水量占比")
    private String useWaterRatio;

    @Schema(description = "区域规划")
    private String code;


}
