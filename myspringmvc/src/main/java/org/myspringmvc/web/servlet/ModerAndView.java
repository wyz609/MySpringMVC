package org.myspringmvc.web.servlet;

import org.myspringmvc.ui.ModelMap;

/**
 * Class name: ModerAndView
 * Package: org.myspringmvc.web.bind.servlet
 * Description:
 *
 * @Create: 2025/2/20 15:58
 * @Author: jay
 * @Version: 1.0
 */
public class ModerAndView {

    private Object view;
    private ModelMap  model;

    public ModerAndView() {
    }

    public ModerAndView(Object view, ModelMap model) {
        this.view = view;
        this.model = model;
    }

    public Object getView() {
        return view;
    }

    public void setView(Object view) {
        this.view = view;
    }

    public ModelMap getModel() {
        return model;
    }

    public void setModel(ModelMap model) {
        this.model = model;
    }

    /**
     * 发方法待实现
     * @param viewName
     */
    public void setViewName(String viewName) {
        //TODO
    }
}

