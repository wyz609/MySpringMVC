package org.myspringmvc.web.bind.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * Class name: View
 * Package: org.myspringmvc.web.bind.servlet
 * Description:
 *
 * @Create: 2025/2/20 15:58
 * @Author: jay
 * @Version: 1.0
 */
public interface View {

    /**
     * 获取响应的内容类型
     * @return
     */
    default String getContentType() {
        return null;
    }


    /**
     * 渲染视图
     * @param model
     * @param request
     * @param response
     * @throws Exception
     */
    void render(@Nullable Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception;
}

