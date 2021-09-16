package com.hnly.provincial.entity.ladderwaterprice;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hnly.provincial.comm.utils.PageWhere;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;
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
@Schema(name = "LadderWaterPriceVO", description = "阶梯水价表")
public class LadderWaterPriceVO extends PageWhere<LadderWaterPrice> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotNull(message = "地区不能为null")
    @NotEmpty(message = "地区不能为空")
    @Schema(description = "地区")
    private String area;

    @Digits(integer = 2, fraction = 2)
    @NotNull(message = "基准水价不能为null")
    @Schema(description = "基准水价")
    private BigDecimal waterPrice;

    @Digits(integer = 1, fraction = 2)
    @NotNull(message = "一阶倍数不能为null")
    @Schema(description = "一阶倍数")
    private BigDecimal firstMultiple;

    @Digits(integer = 1, fraction = 2)
    @NotNull(message = "二阶倍数不能为null")
    @Schema(description = "二阶倍数")
    private BigDecimal secondMultiple;

    @Digits(integer = 1, fraction = 2)
    @NotNull(message = "三阶倍数不能为null")
    @Schema(description = "三阶倍数")
    private BigDecimal thirdMultiple;

    @Digits(integer = 2, fraction = 2)
    @NotNull(message = "一阶水价不能为null")
    @Schema(description = "一阶水价")
    private BigDecimal firstOrderWarterPrice;

    @Digits(integer = 2, fraction = 2)
    @NotNull(message = "二阶水价不能为null")
    @Schema(description = "二阶水价")
    private BigDecimal secondOrderWarterPrice;

    @Digits(integer = 2, fraction = 2)
    @NotNull(message = "三阶水价不能为null")
    @Schema(description = "三阶水价")
    private BigDecimal thirdOrderWarterPrice;

    @Digits(integer = 4, fraction = 4)
    @NotNull(message = "电价不能为null")
    @Schema(description = "电价")
    private BigDecimal electrovalence;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;


}
