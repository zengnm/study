package com.share.example.web.interceptor;

//import com.jd.common.web.RequstPathExcluder;
//import com.jd.passport.inteceptor.mvc.SpringMvcInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class LoginInterceptor //extends SpringMvcInterceptor
{


    //@Autowired
    //private RequstPathExcluder requstPathExcluder;

    //@Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        //过滤不需要登录的东西哦
//        if (requstPathExcluder.isExclude(request)) {
//            return true;
//        }


        //request.setAttribute("com.jd.passport.utils.JdLoginUtils.pin", "yy-test1");


        //return super.preHandle(request, response, handler);
        return true;
    }

//    public void setRequstPathExcluder(RequstPathExcluder requstPathExcluder) {
//        this.requstPathExcluder = requstPathExcluder;
//    }
}
