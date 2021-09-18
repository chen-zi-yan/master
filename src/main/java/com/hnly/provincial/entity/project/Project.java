package com.hnly.provincial.entity.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@Schema(name = "Project", description = "项目管理")
public class Project implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotEmpty
    @NotNull
    @Schema(description = "行政区划")
    private String code;

    @NotEmpty
    @NotNull
    @Schema(description = "项目名")
    private String name;

    @Schema(description = "负责单位 1 农业 2 水利  3 发改")
    private String unit;

    @Schema(description = "项目类型 0 井灌 1 渠灌")
    private String type;

    @NotEmpty
    @NotNull
    @Schema(description = "厂家")
    private String manufacturers;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;


}
