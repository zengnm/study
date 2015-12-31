package com.share.example.web.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zengnianmei
 * @version 1.0, 2015/12/11.
 */
public class ExampleMappingExceptionResolver extends SimpleMappingExceptionResolver {
    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(ExampleMappingExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //for log writer error
        logger.error("Asset System Error message: ", ex);
        return super.resolveException(request, response, handler, ex);
    }

}
