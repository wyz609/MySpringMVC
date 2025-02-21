package org.myspringmvc.web.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.myspringmvc.context.WebApplicationContext;
import org.myspringmvc.web.constant.Constant;
import org.myspringmvc.web.servlet.ModelAndView;
import org.myspringmvc.web.servlet.View;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Objects;


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

    private HandlerMapping handlerMapping;

    private HandlerAdapter handlerAdapter;

    private ViewResolver viewResolver;

    @Override
    public void init() throws ServletException {
        System.out.println("进入init初始化方法体中");
        try {
            ServletConfig servletConfig = this.getServletConfig();
            String contextConfigLocation = servletConfig.getInitParameter(Constant.CONTEXT_CONFIG_LOCATION);
            String springMvcXmlPath = getSpringMvcXmlPath(contextConfigLocation);
            System.out.println("Spring MVC配置文件解析完成："+springMvcXmlPath);

            WebApplicationContext webApplicationContext = new WebApplicationContext(springMvcXmlPath, this.getServletContext());
            //将Spring MVC容器存储到Servlet上下文，以备后续使用
            this.getServletContext().setAttribute(Constant.WEB_APPLICATION_CONTEXT,webApplicationContext);

            //初始化HandlerMapping
             this.handlerMapping = (HandlerMapping) webApplicationContext.getBean(Constant.HANDLER_MAPPING);
             //初始化HandlerAdapter
             this.handlerAdapter = (HandlerAdapter) webApplicationContext.getBean(Constant.HANDLER_ADAPTER);
             //初始化ViewResolver
             this.viewResolver = (ViewResolver) webApplicationContext.getBean(Constant.VIEW_RESOLVER);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 该方法的作用是获取SpringMVC配置文件的路径，它通过一个输入的contextConfigLocation字符串参数来判断配置文件路径，并返回解码后的路径
     * @param contextConfigLocation
     * @return
     */
    private String getSpringMvcXmlPath(String contextConfigLocation) throws ServletException {
        System.out.println("进入getSpringMvcXmlPath方法体");
        if (contextConfigLocation.startsWith(Constant.CLASSPATH)) {//这行代码判断contextConfigLocation是否以"classpath"开头，通常表示类路径
            //如果contextConfigLocation是以classpath:开头，表示路径是从classpath中或获取资源的。
            String path = contextConfigLocation.substring(Constant.CLASSPATH.length() + 1).trim();
            System.out.println(path);
            String SpringMvcXmlPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(path)).getPath();
            System.out.println(SpringMvcXmlPath);
            //对路径解码，防止路径中有%字符
            return URLDecoder.decode(SpringMvcXmlPath, Charset.defaultCharset());
        }
        return null;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req,resp);
    }

    /**
     * 处理请求的核心方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doDispatch(HttpServletRequest request, HttpServletResponse response){

        try {
            //根据请求获取处理器执行链
            HandlerExecutionChain mappedHandler = handlerMapping.getHandler(request);
            System.out.println(mappedHandler);
            //执行拦截器的preHandler
            if(!mappedHandler.applyPreHandle(request,response,mappedHandler.getHandler())){
                return;
            }

            HandlerAdapter ha = (HandlerAdapter)((WebApplicationContext) request.getServletContext().getAttribute(Constant.WEB_APPLICATION_CONTEXT)).getBean(Constant.HANDLER_ADAPTER);
            ModelAndView mv = ha.handle(request, response, mappedHandler.getHandler());

            mappedHandler.applyPostHandle(request,response,mappedHandler.getHandler(),mv);

            //处理响应
            View view = viewResolver.resolveViewName((String) mv.getView(),Locale.CHINA);
            view.render(mv.getModel(),request,response);

            mappedHandler.triggerAfterCompletion(request,response,mappedHandler.getHandler(),null);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

