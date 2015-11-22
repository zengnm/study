package com.share.example.web.interceptor;

import com.share.example.web.annotation.RefererCheck;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zengnianmei
 * @version 1.0
 */
public class RequestRefererInterceptor extends HandlerInterceptorAdapter {

    /**
     * 系统日志
     */
    private static final Logger log = Logger.getLogger(RequestRefererInterceptor.class);

    /**
     * 校验不通过时的跳转路径
     */
    private String invalidRedirectUrl;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hMethod = (HandlerMethod) handler;
            RefererCheck pwdAnno = hMethod.getMethodAnnotation(RefererCheck.class);
            if (null != pwdAnno) {
                String referer = request.getHeader("referer");
//                if (!DujiaSafeConstant.validAddress(referer)) {
//                    log.error(String.format("referer验证失败.URI:%s,referer:%s", request.getRequestURI(), referer));
//                    response.sendRedirect(invalidRedirectUrl);
//                    return false;
//                }
            }
        }
        return true;
    }

    public void setInvalidRedirectUrl(String invalidRedirectUrl) {
        this.invalidRedirectUrl = invalidRedirectUrl;
    }
}
