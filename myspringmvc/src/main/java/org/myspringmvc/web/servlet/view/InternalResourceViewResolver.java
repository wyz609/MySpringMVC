package org.myspringmvc.web.servlet.view;

import org.myspringmvc.web.servlet.ViewResolver;
import org.myspringmvc.web.servlet.View;

import java.util.Locale;

/**
 * Class name: InternalResourceViewResolver
 * Package: org.myspringmvc.web.bind.servlet.view
 * Description:
 *
 * @Create: 2025/2/20 15:55
 * @Author: jay
 * @Version: 1.0
 */
public class InternalResourceViewResolver implements ViewResolver {

    private String prefix;//匹配前置字符串
    private String suffix;//匹配后置字符串

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        return new InternalResourceView("text/html;charset=UTF-8",prefix + viewName + suffix);
    }
}

