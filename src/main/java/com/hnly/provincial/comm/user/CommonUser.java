package com.hnly.provincial.comm.user;

import com.hnly.provincial.comm.utils.TokenUtil;
import com.hnly.provincial.entity.user.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author maqh
 * @version 1.0
 * @date 2021-09-17
 */
@Component
public class CommonUser {

    /**
     * 获取用户
     *
     * @return 用户信息
     */
    public User getUser() {
        User tokenUserId = getTokenUser();
        return initCode(tokenUserId);
    }

    /**
     * 整理农户管辖区域
     *
     * @param user 用户信息
     * @return 整理后code
     */
    public User initCode(User user) {
        user.setCode(code(user.getCode()));
        return user;
    }

    /**
     * 整理code
     *
     * @param code 整理前code
     * @return 整理后code
     */
    public String code(String code) {
        String str = "";
        if (code == null) {
            str = "41";
        } else if (Integer.parseInt(code.substring(9, 12)) > 0) {
            str = code.substring(0, 12);
        } else if (Integer.parseInt(code.substring(6, 9)) > 0) {
            str = code.substring(0, 9);
        } else if (Integer.parseInt(code.substring(4, 6)) > 0) {
            str = code.substring(0, 6);
        } else if (Integer.parseInt(code.substring(2, 4)) > 0) {
            str = code.substring(0, 4);
        } else {
            str = code.substring(0, 2);
        }
        return str;
    }

    /**
     * 获取token用户的行政区划
     *
     * @return 行政区划
     */
    public String getUserCode() {
        User tokenUser = getTokenUser();
        return code(tokenUser.getCode());
    }

    /**
     * 获取tokenUser
     *
     * @return user
     */
    private User getTokenUser() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String authorization = request.getHeader("Authorization");
        return TokenUtil.getTokenUserId(authorization);
    }

}
