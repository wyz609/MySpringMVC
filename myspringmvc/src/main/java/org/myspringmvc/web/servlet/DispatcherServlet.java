package org.myspringmvc.web.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.myspringmvc.context.WebApplicationContext;
import org.myspringmvc.web.constant.Constant;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

/**
 * Class name: DispatcherServlet
 * Package: org.myspringmvc.web.bind.servlet
 * Description: SpringMVC 框架核心接口，用于控制前端发来的所有请求，进行控制请求处理
 *
 * @Create: 2025/2/20 15:56
 * @Author: jay
 * @Version: 1.0
 */
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            ServletConfig servletConfig = this.getServletConfig();
            String contextConfigLocation = servletConfig.getInitParameter(Constant.CONTEXT_CONFIG_LOCATION);
            String springMvcXmlPath = getSpringMvcXmlPath(contextConfigLocation);
            System.out.println("Spring MVC配置文件解析完成："+springMvcXmlPath);

            WebApplicationContext webApplicationContext = new WebApplicationContext(springMvcXmlPath, this.getServletContext());
            this.getServletContext().setAttribute(Constant.WEB_APPLICATION_CONTEXT,webApplicationContext);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 该方法的作用是获取SpringMVC配置文件的路径，它通过一个输入的contextConfigLocation字符串参数来判断配置文件路径，并返回解码后的路径
     * @param contextConfigLocation
     * @return
     */
    private String getSpringMvcXmlPath(String contextConfigLocation) {
        if (contextConfigLocation.startsWith(Constant.CLASSPATH)) {//这行代码判断contextConfigLocation是否以"classpath"开头，通常表示类路径
            //如果contextConfigLocation是以classpath；开头，表示路径是从classpath中或获取资源的。
            String path = contextConfigLocation.substring(Constant.CLASSPATH.length()).trim();
            String SpringMvcXmlPath = Thread.currentThread().getContextClassLoader().getResource(path).getPath();
            //对路径解码，防止路径中有%字符
            return URLDecoder.decode(SpringMvcXmlPath, Charset.defaultCharset());
        }
        return null;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * 处理请求的核心方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

