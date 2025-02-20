package org.myspringmvc.context;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URLDecoder;
import java.util.HashMap;
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

        componentScan(basePackage);
        System.out.println("Spring Web容器当前状态：" + beanMap);
    }

    private void componentScan(String basePackage) throws Exception {
        String dirPath = Thread.currentThread().getContextClassLoader().getResource(basePackage.replace(".", "/")).getPath();
        File file = new File(URLDecoder.decode(dirPath));
        if (file.isFile()) {
            File[] files = file.listFiles();
            for(File classFile : files ){
                if(classFile.getName().endsWith(".class")){
                    String ClassName = basePackage + "." + classFile.getName().substring(0,classFile.getName().lastIndexOf("."));
                    Class<?> clazz = Class.forName(ClassName);
                    Constructor<?> defaultCon = clazz.getDeclaredConstructor();
                    Object bean = defaultCon.newInstance();
                    beanMap.put(firstCharLowerCase(clazz.getSimpleName()),bean);

                }
            }
        }

    }

    private String firstCharLowerCase(String simpleName) {
        return simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
    }


    public Object getBean(String beanName) {
        return beanMap.get(beanName);
    }

}

