package com.hnly.provincial.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.dao.user.UserMapper;
import com.hnly.provincial.entity.user.User;
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
    public boolean login(String userName, String password) {
        User one = lambdaQuery().eq(User::getUsername, userName).eq(User::getPassword, password).one();
        if (one != null) {
            return true;
        }
        return false;
    }
}
