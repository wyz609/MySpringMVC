package org.myspringmvc.context;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.io.SAXReader;
import org.myspringmvc.stereotype.Controller;
import org.myspringmvc.web.bind.annotation.RequestMapping;
import org.myspringmvc.web.bind.method.HandlerMethod;
import org.myspringmvc.web.constant.Constant;
import org.myspringmvc.web.servlet.HandlerAdapter;
import org.myspringmvc.web.servlet.HandlerInterceptor;
import org.myspringmvc.web.servlet.HandlerMapping;
import org.myspringmvc.web.servlet.mvc.RequestMappingInfo;
import org.myspringmvc.web.servlet.view.InternalResourceViewResolver;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class name: ApplicationContext
 * Package: org.myspringmvc.context
 * Description: Spring 容器，启动服务器时，进行初始化
 *
 * @Create: 2025/2/20 19:11
 * @Author: jay
 * @Version: 1.0
 */
public class ApplicationContext {
    //这个beanMap的主要作用就是将创建实例的类存储到该Map中
    private Map<String,Object> beanMap = new HashMap<String,Object>();

    public ApplicationContext(String xmlPath) throws Exception {
        //组价扫描
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(xmlPath));//Document对象表示整个XML文档
        Element componentScanElement = (Element) document.selectSingleNode("/beans/context:component-scan");
        String basePackage = componentScanElement.attributeValue("base-package");
        System.out.println("组件扫描：" + basePackage);

        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = componentScan(basePackage);
        //System.out.println("Spring Web容器当前状态：" + beanMap);

        //创建视图解析器
        //Element viewResolverBean = (Element) document.selectSingleNode("/beans/bean");
        Element rootElement = document.getRootElement();
        Element viewResolverBean = rootElement.element("bean");

        //System.out.println(viewResolverBean);
        String viewResolverClassName = viewResolverBean.attributeValue("class");
        Class<?> viewResolverClass = Class.forName(viewResolverClassName);
        Object viewResolverObj = viewResolverClass.getDeclaredConstructor().newInstance();
        //System.out.println(viewResolverObj);

        if(viewResolverObj instanceof InternalResourceViewResolver internalResourceViewResolver){
            System.out.println("进入拼接前缀和后缀循环体循环体...");
            List<Element> properties = viewResolverBean.elements("property");
            //
            Element prefixProperty = null;
            Element suffixProperty = null;
            for (Element property : properties) {
                if ("prefix".equals(property.attributeValue("name"))) {
                    prefixProperty = property;
                    continue;
                }else {
                    suffixProperty = property;
                }
            }
            assert prefixProperty != null;
            internalResourceViewResolver.setPrefix(prefixProperty.attributeValue("value"));

            assert suffixProperty != null;
            internalResourceViewResolver.setSuffix(suffixProperty.attributeValue("value"));

        }

        beanMap.put(Constant.VIEW_RESOLVER,viewResolverObj);
        //System.out.println("Spring WEB容器当前状态：" + beanMap);

        //创建所有拦截器对象
        Element interceptorsElement =(Element) rootElement.element("interceptors");
        List<Element> interceptorBeans = interceptorsElement.elements("bean");
        List<HandlerInterceptor> interceptors = new ArrayList<>();
        for (Element interceptorBean : interceptorBeans) {
            String className = interceptorBean.attributeValue("class");
            Class<?> clazz = Class.forName(className);
            interceptors.add((HandlerInterceptor) clazz.getDeclaredConstructor().newInstance());
        }

        beanMap.put(Constant.INTERCEPTORS,interceptors);
        //System.out.println("Spring WEB容器当前状态：" + beanMap);


        //将这个包下所有的类实例化：org.myspringmvc.web.servlet.mvc.method.annotation
        String dirPath = Thread.currentThread().getContextClassLoader().getResource(Constant.PACKAGE_AUTO_CREATE.replace(".", "/")).getPath();
        File file = new File(URLDecoder.decode(dirPath));
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File classfile : files) {
                if(classfile.getName().endsWith(".class")){

                    String ClassName =  Constant.PACKAGE_AUTO_CREATE + "." + classfile.getName().substring(0,classfile.getName().lastIndexOf("."));
                    Class<?> clazz = Class.forName(ClassName);
                    Object bean = clazz.getDeclaredConstructor().newInstance();
                    if(bean instanceof HandlerMapping){
                        beanMap.put(Constant.HANDLER_MAPPING,bean);
                    }
                    if(bean instanceof HandlerAdapter){
                        beanMap.put(Constant.HANDLER_ADAPTER,bean);
                    }
                }
            }
        }

        System.out.println("Spring MVC容器当前状态：" + beanMap);

    }

    private Map<RequestMappingInfo,HandlerMethod> componentScan(String basePackage) throws Exception {
        //初始化HandlerMethod
        Map<RequestMappingInfo,HandlerMethod> handlerMethodMap = new HashMap<>();

        String dirPath = Thread.currentThread().getContextClassLoader().getResource(basePackage.replace(".", "/")).getPath();
        File file = new File(URLDecoder.decode(dirPath));
        if (file.isDirectory()) {
            System.out.println("进入循环体...");
            File[] files = file.listFiles();
            for(File classFile : files){
                if(classFile.getName().endsWith(".class")){
                    String ClassName = basePackage + "." + classFile.getName().substring(0,classFile.getName().lastIndexOf("."));
                    Class<?> clazz = Class.forName(ClassName);
                    Constructor<?> defaultCon = clazz.getDeclaredConstructor();
                    Object bean = defaultCon.newInstance();
                    System.out.println("bean:" + bean);
                    System.out.println(firstCharLowerCase("ClassName:" + clazz.getSimpleName()));
                    beanMap.put(firstCharLowerCase(clazz.getSimpleName()),bean);

                    if(clazz.isAnnotationPresent(Controller.class)){
                        //获取该类中所有的方法
                        Method[] methods = clazz.getDeclaredMethods();
                        for (Method method : methods) {
                            if(method.isAnnotationPresent(RequestMapping.class)){
                                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                                //创建RequestMappingInfo对象
                                RequestMappingInfo requestMappingInfo = new RequestMappingInfo();
                                requestMappingInfo.setRequestURI(requestMapping.value()[0]);
                                requestMappingInfo.setRequestMethod(requestMapping.method().toString());
                                //创建HandlerMethod对象
                                HandlerMethod handlerMethod = new HandlerMethod();
                                handlerMethod.setMethod(method);
                                handlerMethod.setHandler(bean);

                                handlerMethodMap.put(requestMappingInfo,handlerMethod);
                            }
                        }
                    }

                }
            }
        }
        return handlerMethodMap;

    }

    private String firstCharLowerCase(String simpleName) {
        return simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
    }


    public Object getBean(String beanName) {
        return beanMap.get(beanName);
    }

}

