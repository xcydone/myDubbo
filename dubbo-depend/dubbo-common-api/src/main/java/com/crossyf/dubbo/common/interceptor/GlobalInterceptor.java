package com.crossyf.dubbo.common.interceptor;


import com.crossyf.dubbo.common.utils.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class GlobalInterceptor implements HandlerInterceptor {
    private JWTUtil jwtUtil;

    public boolean isInterceptor;


    public GlobalInterceptor(JWTUtil jwtUtil, boolean isInterceptor) {
        this.jwtUtil = jwtUtil;
        this.isInterceptor = isInterceptor;
    }

    // preHandle 在controller方法执行之前执行（里面可以写登录的token验证）
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (!isInterceptor) {
            return true;
        }

        response.setHeader("Access-Control-Expose-Headers","token");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        String basePath = request.getContextPath();
        String token = getParam(request, "token");
        if (!StringUtils.isEmpty(token) && jwtUtil.verify(token)) {
            // token验证通过
            request.setAttribute("operId",jwtUtil.getJWTData(token).get("operId"));
            response.setHeader("token", jwtUtil.refreshToken(token));
            return true;
        } else {
            // token验证不通过
            response.sendRedirect(basePath + "/login/toLogin");
            return false;
        }
    }


    /**
     * 获取token信息
     * @param request
     * @return
     */
    private String getParam(HttpServletRequest request, String paramName) {
        //先从Header中获取请求参数
        String token = request.getHeader(paramName);
        if (StringUtils.isEmpty(token)) {
            Map<String, String[]> parm = request.getParameterMap();
            //再从参数中读取
            String tokens[] = parm.get(paramName);
            if (tokens == null) {
                return null;
            }
            token = tokens[0];
        }
        return token;
    }

}
