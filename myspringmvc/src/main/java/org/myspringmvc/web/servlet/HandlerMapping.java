package org.myspringmvc.web.servlet;

import jakarta.servlet.http.HttpServletRequest;
import org.myspringmvc.web.servlet.HandlerExecutionChain;

/**
 * Class name: HandlerMapping
 * Package: org.myspringmvc.web.bind.servlet
 * Description:主要是通过请求获取对应的处理器执行链
 *
 * @Create: 2025/2/20 15:58
 * @Author: jay
 * @Version: 1.0
 */
public interface HandlerMapping {
    /**
     * 主要是根据请求获取处理器执行链
     * @param request
     * @return
     * @throws Exception
     */
    HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;
}

