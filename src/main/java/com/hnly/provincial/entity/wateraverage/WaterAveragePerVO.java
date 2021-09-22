package com.hnly.provincial.entity.wateraverage;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hnly.provincial.comm.utils.PageWhere;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
@Schema(name = "WaterAveragePerVO", description = "亩均定额")
public class WaterAveragePerVO extends PageWhere<WaterAveragePer> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "市名称")
    private String cityName;
    @Schema(description = "县名称")
    private String countyName;

    @NotNull(message = "年度不能为null")
    @NotEmpty(message = "年度不能为空")
    @Schema(description = "年度")
    private String year;

    @NotNull(message = "超量系数不能为null")
    @NotEmpty(message = "超量系数不能为空")
    @Schema(description = "超量系数（%）")
    private String aboveAverage;

    @NotNull(message = "亩均水量不能为null")
    @NotEmpty(message = "亩均水量不能为空")
    @Schema(description = "亩均水量")
    private String averagePerWaterFirst;

    @NotNull(message = "所属县不能为null")
    @NotEmpty(message = "所属县不能为空")
    @Schema(description = "所属县")
    private String areaCode;

}
