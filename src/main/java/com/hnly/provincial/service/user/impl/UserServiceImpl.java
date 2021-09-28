package com.hnly.provincial.service.user.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.Md5Utils;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.dao.user.UserMapper;
import com.hnly.provincial.entity.user.User;
import com.hnly.provincial.entity.user.UserVO;
import com.hnly.provincial.service.area.IAreaService;
import com.hnly.provincial.service.role.IRoleService;
import com.hnly.provincial.service.user.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author maqh
 * @since 2021-08-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private IRoleService roleService;

    @Resource
    private IAreaService areaService;

    @Override
    public User login(String userName, String password) {
        return lambdaQuery().eq(User::getUsername, userName)
                .eq(User::getPassword, Md5Utils.getMD5(password))
                .one();
    }

    @Override
    public boolean add(UserVO user) {
        Integer count = lambdaQuery().eq(User::getUsername, user.getUsername()).count();
        if (count < 0) {
            //验证用户名是否已存在, 存在抛出自定义异常
            throw new MyException(ResultEnum.USER_ALREADY_EXISTS);
        }

        User user1 = Conversion.changeOne(user, User.class);
        user1.setCreatetime(new Date());
        //密码MD5加密
        user1.setPassword(Md5Utils.getMD5(user1.getPassword()));
        if (user1.getCode() != null) {
            user1.setAnge(areaService.getNameByCode(user1.getCode()));
        }
        return super.save(user1);
    }

    @Override
    public boolean updateUser(UserVO userVO) {
        User user = Conversion.changeOne(userVO, User.class);
        user.setUpdatetime(new Date());
        return updateById(user);
    }

    @Override
    public boolean disableUser(Long id, String type) {
        User user = new User();
        user.setId(id);
        user.setStatus(type);
        user.setUpdatetime(new Date());
        return updateById(user);
    }

    @Override
    public TableDataUtils<List<UserVO>> getPage(UserVO vo) {
        Page<User> page = lambdaQuery().likeRight(!StringUtils.isEmpty(vo.getCode()) && !vo.getCode().equals("41"), User::getCode, vo.getCode()).page(vo.page());
        List<UserVO> users = Conversion.changeList(page.getRecords(), UserVO.class);
        for (UserVO user : users) {
            user.setQuanxianName(roleService.getName(user.getQuanxian()));
        }
        return TableDataUtils.success(page.getTotal(), users);
    }
}
