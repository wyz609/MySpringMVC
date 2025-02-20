package org.myspringmvc.web.servlet.mvc.method.annotation;

import jakarta.servlet.http.HttpServletRequest;
import org.myspringmvc.web.servlet.HandlerMapping;
import org.springframework.web.servlet.HandlerExecutionChain;

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
    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        return null;
    }
}

