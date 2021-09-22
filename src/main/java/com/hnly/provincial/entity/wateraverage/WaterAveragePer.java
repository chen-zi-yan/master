package com.hnly.provincial.entity.wateraverage;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 亩均定额
 * </p>
 *
 * @author czy
 * @since 2021-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_water_average_per")
@Schema(name = "WaterAveragePer", description = "亩均定额")
public class WaterAveragePer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "年度")
    private String year;

    @Schema(description = "超量系数（%）")
    private String aboveAverage;

    @Schema(description = "亩均水量")
    private String averagePerWaterFirst;

    @Schema(description = "所属县")
    private String areaCode;


}
