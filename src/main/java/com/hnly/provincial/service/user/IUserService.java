package com.hnly.provincial.service.user;

import com.hnly.provincial.entity.user.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author maqh
 * @since 2021-08-31
 */
public interface IUserService extends IService<User> {

    boolean login(String userName, String password);
}
