package com.hnly.provincial.controller.user;


import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.TokenUtil;
import com.hnly.provincial.comm.validation.Add;
import com.hnly.provincial.comm.validation.Update;
import com.hnly.provincial.entity.user.User;
import com.hnly.provincial.entity.user.UserVO;
import com.hnly.provincial.service.user.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;
import java.util.HashMap;
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
@Slf4j
@RestController
@RequestMapping("user")
@Tag(name = "系统用户管理接口")
public class UserController {

    @Resource
    private IUserService userService;

    private static final String TYPE_ONE = "1";
    private static final String TYPE_ZERO = "0";

    @Operation(summary = "登陆接口",
            parameters = {
                    @Parameter(name = "userName", required = true, description = "用户名"),
                    @Parameter(name = "password", required = true, description = "用户密码"),
            }
    )
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

    @Operation(summary = "获取所有用户")
    @PostMapping("getList")
    public JsonBean<List<User>> getList() {
        List<User> list = userService.list();
        return JsonBean.success(list);
    }

    @Operation(summary = "刷新token")
    @PostMapping("refreshToken")
    public JsonBean<Map<String, String>> refreshToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.replace("Bearer ", "");
        String newToken = TokenUtil.refreshToken(token);
        Map<String, String> tokenMap = new HashMap<>(2);
        tokenMap.put("oldToken", token);
        tokenMap.put("newToken", newToken);
        return JsonBean.success(tokenMap);
    }

    @Operation(summary = "添加用户")
    @PostMapping
    public JsonBean<String> addUser(@RequestBody @Validated({Add.class, Default.class}) UserVO user) {
        if (userService.add(user)) {
            return JsonBean.success();
        }
        return JsonBean.err(ResultEnum.SAVE_ERROR);
    }

    @Operation(summary = "修改用户", description = "只支持修改用户信息<br/> 不能修改账号和密码>")
    @PutMapping
    public JsonBean<String> updateUser(@RequestBody @Validated({Update.class}) UserVO userVO) {
        if (userService.updateUser(userVO)) {
            return JsonBean.success();
        }
        return JsonBean.err(ResultEnum.SAVE_ERROR);
    }

    @Operation(summary = "禁用用户")
    @PutMapping("{id}")
    public JsonBean<String> disableUser(@PathVariable Long id) {
        if (userService.disableUser(id, TYPE_ONE)) {
            return JsonBean.success();
        }
        return JsonBean.err();
    }

    @Operation(summary = "启用用户")
    @PutMapping("enableUser/{id}")
    public JsonBean<String> enableUser(@PathVariable Long id) {
        if (userService.disableUser(id, TYPE_ZERO)) {
            return JsonBean.success();
        }
        return JsonBean.err();
    }

}
