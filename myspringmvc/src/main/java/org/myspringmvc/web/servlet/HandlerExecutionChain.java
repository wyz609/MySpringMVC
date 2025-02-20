package org.myspringmvc.web.servlet;

import java.util.List;

/**
 * Class name: HandlerExecutionChain
 * Package: org.myspringmvc.web.bind.servlet
 * Description: 处理执行链
 *
 * @Create: 2025/2/20 15:57
 * @Author: jay
 * @Version: 1.0
 */
public class HandlerExecutionChain {

    private Object handler;//表示处理器对象，不能为null;

    private List<HandlerInterceptor> interceptors;//表示拦截器对象列表,不嫩为null;

    private int interceptorIndex = -1;//拦截器索引，默认为-1.表示无连接器;

    public HandlerExecutionChain(Object handler, List<HandlerInterceptor> interceptors, int interceptorIndex) {
        this.handler = handler;
        this.interceptors = interceptors;
        this.interceptorIndex = interceptorIndex;
    }

    public HandlerExecutionChain() {
    }

    public int getInterceptorIndex() {
        return interceptorIndex;
    }

    public void setInterceptorIndex(int interceptorIndex) {
        this.interceptorIndex = interceptorIndex;
    }

    public List<HandlerInterceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<HandlerInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public Object getHandler() {
        return handler;
    }

    public void setHandler(Object handler) {
        this.handler = handler;
    }
}

