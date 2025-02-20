package org.myspringmvc.web.servlet;

import org.springframework.web.servlet.View;

import java.util.Locale;

/**
 * Class name: ViewResolver
 * Package: org.myspringmvc.web.bind.servlet
 * Description: 解析逻辑视图名称，返回视图对象
 *
 * @Create: 2025/2/20 15:58
 * @Author: jay
 * @Version: 1.0
 */
public interface ViewResolver {
    /**
     * 解析逻辑视图名称，返回视图对象
     * @param viewName
     * @param locale
     * @return
     * @throws Exception
     */
    View resolveViewName(String viewName, Locale locale) throws Exception;

}

