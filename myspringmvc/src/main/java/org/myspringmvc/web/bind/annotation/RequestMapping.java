package org.myspringmvc.web.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class name: RequestMapping
 * Package: org.myspringmvc.web.bind.annotation
 * Description:用来标注处理器方法，允许标注方法和类，可以被反射机制读取
 *
 * @Create: 2025/2/20 15:44
 * @Author: jay
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    String[] value() default "";//用来指定请求路径

    RequestMethod method();//用来指定请求方式
}

