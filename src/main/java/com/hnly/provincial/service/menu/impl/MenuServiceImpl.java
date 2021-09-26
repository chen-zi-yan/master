package com.hnly.provincial.service.menu.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.user.CommonUser;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.dao.menu.MenuMapper;
import com.hnly.provincial.entity.menu.Menu;
import com.hnly.provincial.entity.menu.MenuVO;
import com.hnly.provincial.entity.user.User;
import com.hnly.provincial.service.menu.IMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单功能表 服务实现类
 * </p>
 *
 * @author maqh
 * @since 2021-09-09
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Resource
    private CommonUser commonUser;

    @Override
    public TableDataUtils<List<MenuVO>> findListByPage(MenuVO menuVO) {
        Page<Menu> page = lambdaQuery().page(menuVO.page());
        List<MenuVO> menuVOs = Conversion.changeList(page.getRecords(), MenuVO.class);
        return TableDataUtils.success(page.getTotal(), menuVOs);
    }

    @Override
    public boolean add(MenuVO menuVO) {
        Menu menu = Conversion.changeOne(menuVO, Menu.class);
        baseMapper.insert(menu);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(MenuVO menuVO) {
        Menu menu = Conversion.changeOne(menuVO, Menu.class);

        baseMapper.updateById(menu);
        return true;
    }

    @Override
    public MenuVO findById(Long id) {
        Menu menu = baseMapper.selectById(id);
        return Conversion.changeOne(menu, MenuVO.class);
    }

    @Override
    public List<MenuVO> getMenuAll() {
        List<Menu> list = lambdaQuery().eq(Menu::getType, 1).isNull(Menu::getParentKey).list();
        List<MenuVO> menuVOS = Conversion.changeList(list, MenuVO.class);
        for (MenuVO menuVO : menuVOS) {
            List<Menu> children = lambdaQuery().eq(Menu::getParentKey, menuVO.getKey()).list();
            List<MenuVO> childrenVOS = Conversion.changeList(children, MenuVO.class);
            menuVO.setChildren(childrenVOS);
        }
        return menuVOS;
    }

    @Override
    public List<MenuVO> getUserMenu() {
        User user = commonUser.getUser();
        if (user.getUsername().equals("admin")) {
            return getMenuAll();
        }
        List<MenuVO> menus = baseMapper.getUserMenu(user.getQuanxian());
        for (MenuVO menuVO : menus) {
            menuVO.setChildren(baseMapper.getChildren(menuVO.getKey(), user.getQuanxian()));
        }
        return menus;
    }

    @Override
    public List<MenuVO> getButtonRole() {
        User user = commonUser.getUser();
        return baseMapper.getButton(user.getQuanxian());
    }
}
