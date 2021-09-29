package com.hnly.provincial.entity.wateruserecords;

import com.hnly.provincial.comm.utils.PageWhere;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : chenziyan
 * @version : 1.0
 * @Date : 2021-09-26 13:46
 * @description :用水记录(入参)
 ***/
@Data
@Schema(name = "FindNameVO", description = "用水记录(入参)")
public class FindNameVO extends PageWhere<WaterUseRecords> {

    @Schema(description = "计量类型")
    private String type;

    @Schema(description = "农户名称")
    private String farmerName;

    @Schema(description = "设备名称")
    private String deviceName;

    @Schema(description = "行政区划")
    private String code;
}
