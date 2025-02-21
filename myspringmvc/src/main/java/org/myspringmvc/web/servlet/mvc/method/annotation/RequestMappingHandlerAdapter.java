package org.myspringmvc.web.servlet.mvc.method.annotation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.myspringmvc.web.servlet.HandlerAdapter;
import org.myspringmvc.web.servlet.ModelAndView;

/**
 * Class name: RequestMappingHandlerAdapter
 * Package: org.myspringmvc.web.bind.servlet.mvc.method.annotation
 * Description: 专门对应 RequestMapping适配器类,HandlerAdapter接口的具体实现类
 *
 * @Create: 2025/2/20 15:53
 * @Author: jay
 * @Version: 1.0
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter {
    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //让handler方法返回一个固定的ModelAndView方法，后期详细编写handle方法
        modelAndView.setView("index");
        modelAndView.setModel(null);

        return modelAndView;
    }
}

