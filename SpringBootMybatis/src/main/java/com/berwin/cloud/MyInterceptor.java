package com.berwin.cloud;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.berwin.cloud.model.UserEntity;
import com.berwin.cloud.util.WebHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zengnianmei
 * @version 1.0, 2016/9/8.
 */
public class MyInterceptor implements HandlerInterceptor {
    //在请求处理之前进行调用（Controller方法调用之前）
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

//        Object obj = request.getSession().getAttribute("cur_user");
//        if (obj == null || !(obj instanceof UserEntity)) {
        String pin = WebHelper.getPin();
        if(StringUtils.isEmpty(pin)){
            response.sendRedirect(request.getContextPath() + "/login");
            return true;
        }

        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
