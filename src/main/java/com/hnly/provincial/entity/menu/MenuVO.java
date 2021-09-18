package com.hnly.provincial.entity.menu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hnly.provincial.comm.utils.PageWhere;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 菜单功能表
 * </p>
 *
 * @author maqh
 * @since 2021-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_menu")
@Schema(name = "MenuVO", description = "菜单功能表")
public class MenuVO extends PageWhere<Menu> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "1.菜单 2.功能点")
    private String type;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "路由name 英文关键字")
    private String key;

    @Schema(description = "路由path/type=3为api接口")
    private String url;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "父级key")
    private String parentKey;

    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

    @Schema(description = "图标")
    private String icon;

    private List<MenuVO> children;

}
