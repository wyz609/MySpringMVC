package org.myspringmvc.web.bind.method;

import java.lang.reflect.Method;

/**
 * Class name: HandlerMethod
 * Package: org.myspringmvc.web.bind.method
 * Description: 处理器方法
 *
 * @Create: 2025/2/20 15:50
 * @Author: jay
 * @Version: 1.0
 */
public class HandlerMethod {
    /**
     * 处理器对象
     */
    private Object handler;

    /**
     * 处理器要执行放方法
     */
    private Method method;

    //创建无参和有参构造方法
    public HandlerMethod() {
    }

    public HandlerMethod(Object handler, Method method) {
        this.handler = handler;
        this.method = method;
    }

    public Object getHandler() {
        return handler;
    }

    public void setHandler(Object handler) {
        this.handler = handler;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}

