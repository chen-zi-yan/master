package com.hnly.provincial.entity.waterquota;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hnly.provincial.comm.utils.PageWhere;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用水定额
 * </p>
 *
 * @author czy
 * @since 2021-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_water_quota")
@Schema(name = "WaterQuotaVO", description = "用水定额")
public class WaterQuotaVO extends PageWhere<WaterQuota> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotNull(message = "行政区划不能为空")
    @NotEmpty(message = "行政区划不能为空")
    @Schema(description = "行政区划")
    private String code;

    @Digits(integer = 8, fraction = 2)
    @NotNull(message = "行政区划不能为空")
    @Schema(description = "用水定额")
    private BigDecimal water;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

    @NotNull(message = "行政区划不能为空")
    @Schema(description = "年")
    private Integer year;


}
