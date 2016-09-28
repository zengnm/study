package com.berwin.cloud.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    /**
     * 从cookie中获取当前用户pin
     * @return
     */
    public static String getPin(){
        Cookie[] cookies = getRequest().getCookies();
        String curUser = null;
        if(null == cookies)return curUser;
        for(Cookie cookie: cookies){
            if(CUR_USER.equals(cookie.getName())){
                curUser = cookie.getValue();
                break;
            }
        }
        return curUser;
    }

    /**
     * 设置cookie
     * @param response
     * @param user
     */
    public static void setCookie(HttpServletResponse response, UserEntity user){
        Cookie cookie = new Cookie(CUR_USER, user.getName());

        //cookie.setMaxAge(60) ;//设定有效时间 以s为单位;默认为-1，表示关闭浏览器，cookie就会消失
        //cookie.setPath("/") ;//设置Cookie路径
        //cookie.setDomain(".boot.org") ; //设置域名，要以“.”开头

        //发送Cookie文件
        response.addCookie(cookie);
    }

    public static void delCookie(HttpServletResponse response){
        Cookie[] cookies = getRequest().getCookies();
        if(null == cookies)return;
        for(Cookie cookie: cookies){
            if(CUR_USER.equals(cookie.getName())){
                cookie.setValue(null);
                cookie.setMaxAge(0);// 立即销毁cookie
                cookie.setPath("/");
                response.addCookie(cookie);
                break;
            }
        }
    }

}
