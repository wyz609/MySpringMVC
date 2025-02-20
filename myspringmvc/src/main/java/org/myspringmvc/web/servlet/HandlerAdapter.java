package org.myspringmvc.web.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class name: HandlerAdapter
 * Package: org.myspringmvc.web.bind.servlet
 * Description: 通过处理器适配器调用处理器方法
 *
 * @Create: 2025/2/20 15:56
 * @Author: jay
 * @Version: 1.0
 */
public interface HandlerAdapter {
    /**
     * 执行处理器方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}

