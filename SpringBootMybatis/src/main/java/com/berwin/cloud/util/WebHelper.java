package com.berwin.cloud.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.berwin.cloud.model.UserEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author zengnianmei
 * @version 1.0, 2016/9/8.
 */
public class WebHelper {
    private final static String CUR_USER = "cur_user";

    /**
     * 获取当前Request
     * @return
     */
    private static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return requestAttributes.getRequest();
    }

    /**
     * 获取当前Session
     * @return
     */
    private static HttpSession getSession() {
        return getRequest().getSession(true);
    }

    /**
     * 获取当前session里面放置的User对象
     * @return
     */
    public static UserEntity getUser(){
        return (UserEntity)getSession().getAttribute(CUR_USER);
    }

    /**
     * 把当前User对象放置到session里面
     * @param user
     */
    public static void setUser(UserEntity user){
        getSession().setAttribute(CUR_USER, user);
    }
}
