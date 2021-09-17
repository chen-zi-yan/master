package com.hnly.provincial.service.menu;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.menu.Menu;
import com.hnly.provincial.entity.menu.MenuVO;

import java.util.List;

/**
* <p>
* 菜单功能表 服务类
* </p>
*
* @author maqh
* @since 2021-09-09
*/
public interface IMenuService extends IService<Menu> {

    /**
    * 查询菜单功能表分页数据
    *
    * @param menuVO     条件
    * @return 分页结果
    */
    TableDataUtils<List<MenuVO>> findListByPage(MenuVO menuVO);

    /**
    * 添加菜单功能表
    * @param menuVO
    * @return false 失败   true 成功
    */
    boolean add(MenuVO menuVO);

    /**
    * 删除菜单功能表
    *
    * @param id 主键
    * @return false 失败   true 成功
    */
    boolean delete(Long id);

    /**
    * 修改菜单功能表
    *
    * @param menuVO
    * @return false 失败   true 成功
    */
    boolean updateData(MenuVO menuVO);

    /**
    * id查询数据
    *
    * @param id id
    * @return MenuVO
    */
    MenuVO findById(Long id);

    /**
     * 获取所有菜单
     * @return 菜单集合
     */
    List<MenuVO> getMenuAll();

    /**
     * 获取登陆用户的菜单
     * @return 用户菜单
     */
    List<MenuVO> getUserMenu();
}
