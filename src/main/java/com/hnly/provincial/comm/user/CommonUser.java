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
        return TokenUtil.getTokenUserId(authorization);
    }
}
