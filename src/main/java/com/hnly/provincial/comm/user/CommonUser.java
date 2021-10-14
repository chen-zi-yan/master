package com.hnly.provincial.comm.user;

import com.hnly.provincial.comm.utils.TokenUtil;
import com.hnly.provincial.entity.user.User;
import org.apache.commons.lang3.StringUtils;
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

    private static final String UNKNOWN = "unknown";

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
        String str;
        if (StringUtils.isEmpty(code)) {
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
        HttpServletRequest request = getRequest();
        String authorization = request.getHeader("Authorization");
        return TokenUtil.getTokenUserId(authorization);
    }

    /**
     * 获取request
     *
     * @return request
     */
    private HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

    /**
     * 获取客户端ip
     *
     * @return ip
     */
    public String getIp() {
        HttpServletRequest request = getRequest();
        return getIpAddress(request);
    }

    /**
     * 获取请求路径
     *
     * @return 路径
     */
    public String getServletPath() {
        return getRequest().getServletPath();
    }

    /**
     * 获取请求方式
     * @return 请求方式
     */
    public String getRequestMethod(){
        return getRequest().getMethod();
    }

    /**
     * 获取用户真实ip
     *
     * @param request request
     * @return ip
     */
    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        String forIp = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(forIp) && !UNKNOWN.equalsIgnoreCase(forIp)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = forIp.indexOf(",");
            if (index != -1) {
                return forIp.substring(0, index);
            } else {
                return forIp;
            }
        }
        forIp = ip;
        if (StringUtils.isNotEmpty(forIp) && !UNKNOWN.equalsIgnoreCase(forIp)) {
            return forIp;
        }
        if (StringUtils.isBlank(forIp) || UNKNOWN.equalsIgnoreCase(forIp)) {
            forIp = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(forIp) || UNKNOWN.equalsIgnoreCase(forIp)) {
            forIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(forIp) || UNKNOWN.equalsIgnoreCase(forIp)) {
            forIp = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(forIp) || UNKNOWN.equalsIgnoreCase(forIp)) {
            forIp = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(forIp) || UNKNOWN.equalsIgnoreCase(forIp)) {
            forIp = request.getRemoteAddr();
        }
        return forIp;
    }
}
