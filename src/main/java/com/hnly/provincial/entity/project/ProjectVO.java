package com.hnly.provincial.entity.project;

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
import java.util.Date;

/**
 * <p>
 * 项目管理
 * </p>
 *
 * @author ymd
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_project")
@Schema(name = "ProjectVO", description = "项目管理")
public class ProjectVO extends PageWhere<Project> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "自增主键,添加时不填")
    private Long id;

    @NotEmpty
    @NotNull
    @Schema(description = "县级行政区划")
    private String code;

    @Schema(description = "县级行政区名称,添加/修改时不填")
    private String codeName;

    @Schema(description = "市级行政区名称,添加/修改时不填")
    private String cityName;

    @NotEmpty
    @NotNull
    @Schema(description = "项目名")
    private String name;

    @NotEmpty
    @NotNull
    @Schema(description = "负责单位 1 农业 2 水利  3 发改")
    private String unit;

    @Schema(description = "负责单位名称,添加/修改时不填")
    private String unitName;

    @NotEmpty
    @NotNull
    @Schema(description = "项目类型 0 井灌 1 渠灌")
    private String type;

    @Schema(description = "项目类型,添加/修改时不填")
    private String typeName;

    @NotEmpty
    @NotNull
    @Schema(description = "厂家")
    private String manufacturers;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

    public String getUnitName() {
        if ("1".equals(unit)) {
            return "农业局";
        } else if ("2".equals(unit)) {
            return "水利局";
        } else {
            return "发改委";
        }
    }

    public String getTypeName() {
        if ("0".equals(unit)) {
            return "井灌";
        } else {
            return "渠灌";
        }
    }


}
