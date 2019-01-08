package com.medblaze.appscommon.interceptor;

import com.medblaze.appscommon.util.JwtUtil;
import com.medblaze.beans.model.RequestInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MedblazeInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(MedblazeInterceptor.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("executing preHandle ...");
        String header = request.getHeader("Authorization");
        if(header!=null && !header.isEmpty()){
            String [] array = jwtUtil.getTenantName(header);
            RequestInfo requestInfo = new RequestInfo();
            requestInfo.setUsername(array[0]);
            requestInfo.setTenantName(array[1]);
            RequestContextProvider.setRequestInfo(requestInfo);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("executing postHandle ...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("executing afterCompletion ...");
    }
}
