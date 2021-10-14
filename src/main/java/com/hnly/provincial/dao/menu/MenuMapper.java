package com.hnly.provincial.dao.menu;

import com.hnly.provincial.entity.menu.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hnly.provincial.entity.menu.MenuVO;

import java.util.List;

/**
 * <p>
 * 菜单功能表 Mapper 接口
 * </p>
 *
 * @author maqh
 * @since 2021-09-09
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 获取权限组菜单顶级
     * @param quanxian  权限id
     * @return 权限id 最顶级菜单
     */
    List<MenuVO> getUserMenu(Long quanxian);

    /**
     * 获取权限组二级菜单
     * @param key 上级key
     * @param id 权限id
     * @return 权限id 二级菜单
     */
    List<MenuVO> getChildren(String key, Long id);

    /**
     * 获取权限组按钮权限
     * @param id 权限id
     * @return 拥有按钮权限
     */
    List<MenuVO> getButton(Long id);
}
