package com.hnly.provincial.entity.ladderwaterprice;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 阶梯水价表
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_ladder_water_price")
@Schema(name = "LadderWaterPrice", description = "阶梯水价表")
public class LadderWaterPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "地区")
    private String area;

    @Schema(description = "基准水价")
    private BigDecimal waterPrice;

    @Schema(description = "一阶倍数")
    private BigDecimal firstMultiple;

    @Schema(description = "二阶倍数")
    private BigDecimal secondMultiple;

    @Schema(description = "三阶倍数")
    private BigDecimal thirdMultiple;

    @Schema(description = "一阶水价")
    private BigDecimal firstOrderWarterPrice;

    @Schema(description = "二阶水价")
    private BigDecimal secondOrderWarterPrice;

    @Schema(description = "三阶水价")
    private BigDecimal thirdOrderWarterPrice;

    @Schema(description = "电价")
    private BigDecimal electrovalence;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;


}
