package com.hnly.provincial.service.role.menu.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.dao.role.menu.RoleMenuMapper;
import com.hnly.provincial.entity.role.menu.RoleMenu;
import com.hnly.provincial.entity.role.menu.RoleMenuVO;
import com.hnly.provincial.service.role.menu.IRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author maqh
 * @since 2021-09-17
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Override
    public TableDataUtils<List<RoleMenuVO>> findListByPage(RoleMenuVO roleMenuVO) {
        Page<RoleMenu> page = lambdaQuery().page(roleMenuVO.page());
        List<RoleMenuVO> roleMenuVos = Conversion.changeList(page.getRecords(), RoleMenuVO.class);
        return TableDataUtils.success(page.getTotal(), roleMenuVos);
    }

    @Override
    public boolean add(RoleMenuVO roleMenuVO) {
        //清空角色下所有菜单
        lambdaUpdate().eq(RoleMenu::getRoleId, roleMenuVO.getRoleId()).remove();
        for (Long menuId : roleMenuVO.getMenuIds()) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleMenuVO.getRoleId());
            roleMenu.setMenuId(menuId);
            baseMapper.insert(roleMenu);
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(RoleMenuVO roleMenuVO) {
        RoleMenu roleMenu = Conversion.changeOne(roleMenuVO, RoleMenu.class);
        baseMapper.updateById(roleMenu);
        return true;
    }

    @Override
    public List<Long> findByRoleId(Long id) {
        List<RoleMenu> list = lambdaQuery().eq(RoleMenu::getRoleId, id).select(RoleMenu::getMenuId).list();
        List<Long> menuIds = list.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        return menuIds;
    }
}
