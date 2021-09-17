package com.hnly.provincial.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.Md5Utils;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.dao.user.UserMapper;
import com.hnly.provincial.entity.user.User;
import com.hnly.provincial.entity.user.UserVO;
import com.hnly.provincial.service.user.IUserService;
import org.springframework.stereotype.Service;

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
        //密码MD5加密
        user1.setPassword(Md5Utils.getMD5(user1.getPassword()));
        return super.save(user1);
    }

    @Override
    public boolean updateUser(UserVO userVO) {
        User user = Conversion.changeOne(userVO, User.class);
        return updateById(user);
    }

    @Override
    public boolean disableUser(Long id, String type) {
        User user = new User();
        user.setId(id);
        user.setStatus(type);
        return updateById(user);
    }
}
