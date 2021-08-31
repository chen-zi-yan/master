package com.hnly.provincial.controller.user;


import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.TokenUtil;
import com.hnly.provincial.entity.user.User;
import com.hnly.provincial.service.user.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author maqh
 * @since 2021-08-31
 */
@RestController
@RequestMapping("user")
@Api(tags = "系统用户管理接口")
public class UserController {

    @Resource
    private IUserService userService;

    @ApiOperation("登陆接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", required = true, value = "用户名"),
            @ApiImplicitParam(name = "password", required = true, value = "用户密码")
    })
    @PostMapping("login")
    public JsonBean<String> login(String userName, String password) {
        if (userService.login(userName, password)) {
            User user = new User();
            user.setUsername(userName);
            String sign = TokenUtil.sign(user);
            return JsonBean.success(sign);
        }
        return JsonBean.err(ResultEnum.USER_USERNAME_PASSWORD_EXISTS);
    }

    @ApiOperation("获取所有用户")
    @PostMapping("getList")
    public JsonBean<List<User>> getList() {
        List<User> list = userService.list();
        return JsonBean.success(list);
    }
}
