package org.myspringmvc.context;

import jakarta.servlet.ServletContext;

/**
 * Class name: WebApplicationContext
 * Package: org.myspringmvc.context
 * Description:
 *
 * @Create: 2025/2/20 19:28
 * @Author: jay
 * @Version: 1.0
 */
public class WebApplicationContext extends ApplicationContext{


    private final ServletContext servletContext;

    public WebApplicationContext(String xmlPath, ServletContext context) throws Exception {
        super(xmlPath);
        this.servletContext = context;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }
}

