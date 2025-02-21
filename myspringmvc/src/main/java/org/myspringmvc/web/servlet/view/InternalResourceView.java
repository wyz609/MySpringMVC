package org.myspringmvc.web.servlet.view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.myspringmvc.web.servlet.View;

import java.util.Map;

/**
 * Class name: internalResourceView
 * Package: org.myspringmvc.web.bind.servlet.view
 * Description:
 *
 * @Create: 2025/2/20 15:55
 * @Author: jay
 * @Version: 1.0
 */
public class InternalResourceView implements View {

    private String contentType;
    private String path;

    public InternalResourceView(String contentType, String path) {
        this.contentType = contentType;
        this.path = path;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置响应内容类型
        response.setContentType(getContentType());
        //向request域中绑定数据
        if(model != null) {
            model.forEach(request::setAttribute);
        }
        //进行转发
        request.getRequestDispatcher(getPath()).forward(request, response);
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }
}

