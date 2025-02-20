package org.myspringmvc.web.bind.servlet.view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.myspringmvc.web.bind.servlet.View;

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
public class internalResourceView implements View {
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    @Override
    public String getContentType() {
        return View.super.getContentType();
    }
}

