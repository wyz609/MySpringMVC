package org.myspringmvc.web.bind.servlet;

import org.springframework.web.servlet.View;

import java.util.Locale;

/**
 * Class name: ViewResolver
 * Package: org.myspringmvc.web.bind.servlet
 * Description:
 *
 * @Create: 2025/2/20 15:58
 * @Author: jay
 * @Version: 1.0
 */
public interface ViewResolver {
    View resolveViewName(String viewName, Locale locale) throws Exception;

}

