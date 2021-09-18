package com.hnly.provincial.entity.area;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hnly.provincial.comm.utils.PageWhere;
import com.hnly.provincial.comm.validation.Add;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 地区表
 * </p>
 *
 * @author czy
 * @since 2021-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "AreaVO", description = "地区表")
public class AreaVO extends PageWhere<Area> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotNull(message = "名称不能null", groups = {Add.class})
    @NotEmpty(message = "名称为空", groups = {Add.class})
    @Schema(description = "名称")
    private String name;

    @NotNull(message = "行政区划不能null", groups = {Add.class})
    @NotEmpty(message = "行政区划不能为空", groups = {Add.class})
    @Schema(description = "区域号")
    private String code;

    @Schema(description = "上级id")
    private String fatherCode;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(description = "0 市 1 县区 2 乡镇 3 村庄")
    private String status;

    public String getLabel() {
        return name;
    }

    public String getValue() {
        return code;
    }

}
