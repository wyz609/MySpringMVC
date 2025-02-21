package org.myspringmvc.web.servlet.mvc.method.annotation;

import jakarta.servlet.http.HttpServletRequest;
import org.myspringmvc.context.WebApplicationContext;
import org.myspringmvc.web.bind.method.HandlerMethod;
import org.myspringmvc.web.constant.Constant;
import org.myspringmvc.web.servlet.HandlerInterceptor;
import org.myspringmvc.web.servlet.HandlerMapping;
import org.myspringmvc.web.servlet.mvc.RequestMappingInfo;
import org.myspringmvc.web.servlet.HandlerExecutionChain;

import java.util.List;
import java.util.Map;

/**
 * Class name: RequestMappingHandlerMapping
 * Package: org.myspringmvc.web.bind.servlet.mvc.method.annotation
 * Description: HandlerMapping具体实现方法
 *
 * @Create: 2025/2/20 15:54
 * @Author: jay
 * @Version: 1.0
 */
public class RequestMappingHandlerMapping implements HandlerMapping {
    private Map<RequestMappingInfo, HandlerMethod> map;

    public RequestMappingHandlerMapping(Map<RequestMappingInfo,HandlerMethod> map) {
        this.map = map;
    }

    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        RequestMappingInfo requestMappingInfo = new RequestMappingInfo(request.getServletPath(), request.getMethod());
        HandlerExecutionChain handlerExecutionChain = new HandlerExecutionChain();
        handlerExecutionChain.setHandler(map.get(requestMappingInfo));
        WebApplicationContext wac = (WebApplicationContext) request.getServletContext().getAttribute(Constant.WEB_APPLICATION_CONTEXT);
        handlerExecutionChain.setInterceptors((List<HandlerInterceptor>)wac.getBean(Constant.INTERCEPTORS));

        return null;
    }
}

