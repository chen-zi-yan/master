package com.hnly.provincial.service.role.menu;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.role.menu.RoleMenu;
import com.hnly.provincial.entity.role.menu.RoleMenuVO;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author maqh
 * @since 2021-09-17
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    /**
     * 查询角色菜单表分页数据
     *
     * @param roleMenuVO 条件
     * @return 分页结果
     */
    TableDataUtils<List<RoleMenuVO>> findListByPage(RoleMenuVO roleMenuVO);

    /**
     * 添加角色菜单表
     *
     * @param roleMenuVO
     * @return false 失败   true 成功
     */
    boolean add(RoleMenuVO roleMenuVO);

    /**
     * 删除角色菜单表
     *
     * @param id 主键
     * @return false 失败   true 成功
     */
    boolean delete(Long id);

    /**
     * 修改角色菜单表
     *
     * @param roleMenuVO
     * @return false 失败   true 成功
     */
    boolean updateData(RoleMenuVO roleMenuVO);

    /**
     * 角色表id查询菜单
     *
     * @param id id
     * @return 角色下的菜单id
     */
    List<Long> findByRoleId(Long id);
}
