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

    public User getUser() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String authorization = request.getHeader("Authorization");
        User tokenUserId = TokenUtil.getTokenUserId(authorization);
        return initCode(tokenUserId);
    }

    /**
     * 整理农户管辖区域
     * @param user 用户信息
     * @return 整理后code
     */
    public User initCode(User user) {
        if (user.getCode() == null) {
            user.setCode("41");
        }else if (Integer.parseInt(user.getCode().substring(9,12)) > 0){
            user.setCode(user.getCode().substring(0, 12));
        } else if (Integer.parseInt(user.getCode().substring(6, 9)) > 0) {
            user.setCode(user.getCode().substring(0, 9));
        } else if (Integer.parseInt(user.getCode().substring(4, 6)) > 0) {
            user.setCode(user.getCode().substring(0,6));
        }else if (Integer.parseInt(user.getCode().substring(2,4)) > 0){
            user.setCode(user.getCode().substring(0,4));
        }else {
            user.setCode(user.getCode().substring(0,2));
        }
        return user;
    }
}
