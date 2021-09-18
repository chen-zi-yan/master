package com.hnly.provincial.entity.role.menu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hnly.provincial.comm.utils.PageWhere;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* <p>
* 角色菜单表
* </p>
*
* @author maqh
* @since 2021-09-17
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(name="RoleMenuVO", description="角色菜单表VO")
public class RoleMenuVO extends PageWhere<RoleMenu> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "菜单id")
    private Long menuId;

    @Schema(description = "菜单id集合")
    private List<Long> menuIds;

}
