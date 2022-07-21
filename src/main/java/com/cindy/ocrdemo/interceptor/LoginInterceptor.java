package com.cindy.ocrdemo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.cindy.ocrdemo.anno.NoNeedLogin;
import com.cindy.ocrdemo.common.CommonResult;
import com.cindy.ocrdemo.common.ResultCode;
import com.cindy.ocrdemo.dto.UserContext;
import com.cindy.ocrdemo.dto.UserReturnDTO;
import com.cindy.ocrdemo.service.UserService;
import com.cindy.ocrdemo.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    public static final String TOKEN_NAME = "token";

    @Autowired
    private UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("当前请求来自{}", request.getRequestURI());
        //跨域设置
        this.crossDomainConfig(response);

        //不需要登录的注解
        Boolean isNoNeedLogin = ((HandlerMethod) handler).getMethodAnnotation(NoNeedLogin.class) != null;
        if (isNoNeedLogin) {
            return true;
        }

        //需要做token校验, 消息头的token优先于请求query参数的token
        String xHeaderToken = request.getHeader(TOKEN_NAME);
        log.info("当前请求token为{}", xHeaderToken);
        String xRequestToken = request.getParameter(TOKEN_NAME);
        String xAccessToken = null != xHeaderToken ? xHeaderToken : xRequestToken;
        if (null == xAccessToken) {
            log.error("{}请求中header中没有携带token",request.getRequestURL());
            return false;
        }

        // 验证token是否失效
        if(!JwtUtil.verifyToken(xHeaderToken)){
            log.error("header中token已经失效，请重新登录");
            this.outputResult(response, CommonResult.failed(ResultCode.LOGIN_NULL_TOKEN));
            return false;
        }

        // 保存用户数据,方便后面获取用户信息可以直接使用
        UserReturnDTO userInfo = userService.getUserInfo(xAccessToken);
        UserContext.setBaseUser(userInfo);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserContext.remove();
    }

    /**
     * 配置跨域
     * @param response
     */
    private void crossDomainConfig(HttpServletResponse response) {
        // 允许所有请求
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Expose-Headers", "*");
        response.setHeader("Access-Control-Allow-Headers", "Authentication,Origin, X-Requested-With, Content-Type, " + "Accept, x-access-token");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires ", "-1");
    }


    /**
     * 错误输出
     *
     * @param response
     * @throws IOException
     */
    private void outputResult(HttpServletResponse response, CommonResult commonResult) throws IOException {
        String msg = JSONObject.toJSONString(commonResult);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(msg);
        response.flushBuffer();
    }
}
