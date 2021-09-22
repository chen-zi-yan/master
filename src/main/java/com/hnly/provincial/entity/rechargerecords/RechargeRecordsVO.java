package com.hnly.provincial.entity.rechargerecords;

import com.hnly.provincial.comm.utils.PageWhere;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 充值记录
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(name = "RechargeRecordsVO", description = "充值记录")
public class RechargeRecordsVO extends PageWhere<RechargeRecords> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "市")
    private String cityName;

    @Schema(description = "县")
    private String countyName;

    @Schema(description = "乡")
    private String townshipName;

    @Schema(description = "村")
    private String villageName;

    @Schema(description = "区域码")
    private String code;

    @Schema(description = "名字")
    private String name;

    @Schema(description = "卡号")
    private String icCode;

    @Schema(description = "设备号")
    private String devSn;

    @Schema(description = "设备注册号")
    private String devRegistrationNo;

    @Schema(description = "充值金额")
    private String money;


}
