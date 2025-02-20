package org.myspringmvc.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class name: Controller
 * Package: org.myspringmvc.stereotype
 * Description:用来标注处理器，被标注的处理器，纳入IoC容器中管理，该注解只能允许出现在类上，另外可以被反射机制读取
 *
 * @Create: 2025/2/20 15:44
 * @Author: jay
 * @Version: 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}

