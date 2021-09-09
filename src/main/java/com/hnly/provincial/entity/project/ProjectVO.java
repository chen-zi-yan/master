package com.hnly.provincial.entity.project;

import com.hnly.provincial.comm.utils.PageWhere;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@Schema(name="ProjectVO", description="项目管理")
public class ProjectVO extends PageWhere<Project> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotEmpty
    @NotNull
    @Schema(description = "行政区划")
    private String code;
    private String codeName;

    @NotEmpty
    @NotNull
    @Schema(description = "项目名")
    private String name;

    @Schema(description = "负责单位 1 农业 2 水利  3 发改")
    private String unit;
    private String unitName;

    @Schema(description = "项目类型 0 井灌 1 渠灌")
    private String type;
    private String typeName;

    @NotEmpty
    @NotNull
    @Schema(description = "厂家")
    private String manufacturers;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;


}
