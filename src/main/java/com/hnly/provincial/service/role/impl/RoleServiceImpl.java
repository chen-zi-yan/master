package com.hnly.provincial.service.role.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.dao.role.RoleMapper;
import com.hnly.provincial.entity.role.Role;
import com.hnly.provincial.entity.role.RoleVO;
import com.hnly.provincial.entity.role.menu.RoleMenu;
import com.hnly.provincial.entity.user.User;
import com.hnly.provincial.service.role.IRoleService;
import com.hnly.provincial.service.role.menu.IRoleMenuService;
import com.hnly.provincial.service.user.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private IRoleMenuService roleMenuService;

    @Resource
    private IUserService userService;

    @Override
    public TableDataUtils<List<RoleVO>> findListByPage(RoleVO roleVO) {
        Page<Role> page = lambdaQuery()
                .likeRight(!StringUtils.isEmpty(roleVO.getRoleName()), Role::getRoleName, roleVO.getRoleName())
                .page(roleVO.page());
        List<RoleVO> roleVOs = Conversion.changeList(page.getRecords(), RoleVO.class);
        return TableDataUtils.success(page.getTotal(), roleVOs);
    }

    @Override
    public boolean add(RoleVO roleVO) {
        roleVO.setCreateTime(new Date());
        Role role = Conversion.changeOne(roleVO, Role.class);
        baseMapper.insert(role);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        if (userService.lambdaQuery().eq(User::getQuanxian, id).count() > 0) {
            throw new MyException(ResultEnum.HIVE_NOT_DELETE);
        }
        baseMapper.deleteById(id);
        roleMenuService.lambdaUpdate().eq(RoleMenu::getRoleId, id).remove();
        return true;
    }

    @Override
    public boolean updateData(RoleVO roleVO) {
        roleVO.setUpdateTime(new Date());
        Role role = Conversion.changeOne(roleVO, Role.class);
        baseMapper.updateById(role);
        return true;
    }

    @Override
    public RoleVO findById(Long id) {
        Role role = baseMapper.selectById(id);
        return Conversion.changeOne(role, RoleVO.class);
    }

    @Override
    public List<Role> findList() {
        return lambdaQuery().list();
    }
}
