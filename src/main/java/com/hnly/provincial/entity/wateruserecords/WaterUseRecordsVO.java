package com.hnly.provincial.entity.wateruserecords;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hnly.provincial.comm.utils.PageWhere;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用水记录表
 * </p>
 *
 * @author czy
 * @since 2021-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_water_use_records")
@Schema(name = "WaterUseRecordsVO", description = "用水记录表")
public class WaterUseRecordsVO extends PageWhere<WaterUseRecords> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "农户id")
    private Long farmerId;

    @Schema(description = "设备id")
    private Long deviceId;

    @Schema(description = "行政区划")
    private String code;

    @Schema(description = "年份")
    private String year;

    @Schema(description = "月份")
    private String month;

    @Schema(description = "0 标准计量 1 非标准计量")
    private String type;

    @Schema(description = "开始使用时间")
    private Date startTime;

    @Schema(description = "结束使用时间")
    private Date endTime;

    @Schema(description = "总用水量")
    private String useWater;

    @Schema(description = "总用电量")
    private String useElectricity;

    @Schema(description = "总计费用")
    private BigDecimal useMoney;

    @Schema(description = "一阶水量")
    private String useWaterFirst;

    @Schema(description = "二阶水量")
    private String useWaterSecond;

    @Schema(description = "三阶水量")
    private String useWaterThird;

    @Schema(description = "一阶费用")
    private BigDecimal useMoneyFirst;

    @Schema(description = "二阶费用")
    private BigDecimal useMoneySecond;

    @Schema(description = "三阶费用")
    private BigDecimal useMoneyThird;


}
