package com.hnly.provincial.entity.role;

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
 * 角色表
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_role")
@Schema(name = "RoleVO", description = "角色表")
public class RoleVO extends PageWhere<Role> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "角色名称不能null")
    @NotEmpty(message = "角色名称不能空")
    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色描述")
    private String remark;

    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(description = "创建时间")
    private Date createTime;


}
