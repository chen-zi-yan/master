package com.hnly.provincial.config.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 刷新token有效期
 * </p>
 *
 * @author maqh
 * @version 1.0
 * @since 2021-05-25
 */
@Component
public class InterceptorConfig implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        return true;
        //if (request.getMethod().equals("OPTIONS")) {
        //    response.setStatus(HttpServletResponse.SC_OK);
        //    return true;
        //}
        //
        //response.setCharacterEncoding("utf-8");
        //
        //String token = request.getHeader("Authorization");
        //if (token != null) {
        //    boolean result = TokenUtil.verify(token.replace("Bearer ",""));
        //    if (result) {
        //        return true;
        //    }
        //}
        //
        //response.setCharacterEncoding("UTF-8");
        //response.setContentType("application/json; charset=utf-8");
        //PrintWriter out = response.getWriter();
        //out.append(new ObjectMapper().writeValueAsString(JsonBean.err(ResultEnum.NOSESSION)));
        //return false;
    }
}
