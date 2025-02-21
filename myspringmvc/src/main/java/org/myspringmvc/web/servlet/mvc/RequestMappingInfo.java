package org.myspringmvc.web.servlet.mvc;

import java.util.Objects;

/**
 * Class name: RequestMappingInfo
 * Package: org.myspringmvc.web.servlet.mvc.method.annotation
 * Description: 该类的主要功能是封装和表示HTTP请求的元信息，用于请求路由和映射
 *
 * @Create: 2025/2/21 12:19
 * @Author: jay
 * @Version: 1.0
 */
public class RequestMappingInfo {

    private String requestURI;//请求路径的URI
    private String requestMethod;//请求的HTTP方法 如GET，POST...

    public RequestMappingInfo() {
    }

    public RequestMappingInfo(String requestURI, String requestMethod) {
        this.requestURI = requestURI;
        this.requestMethod = requestMethod;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestMappingInfo that = (RequestMappingInfo) o;
        return Objects.equals(requestURI, that.requestURI) && Objects.equals(requestMethod, that.requestMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestURI, requestMethod);
    }

    @Override
    public String toString() {
        return "RequestMappingInfo{" +
                "requestURI='" + requestURI + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                '}';
    }
}

