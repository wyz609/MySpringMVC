package org.myspringmvc.ui;

import java.util.LinkedHashMap;

/**
 * Class name: ModelMap
 * Package: org.myspringmvc.ui
 * Description: 将数据存储到域中
 *
 * @Create: 2025/2/20 16:45
 * @Author: jay
 * @Version: 1.0
 */
public class ModelMap extends LinkedHashMap<String, Object> {
    public ModelMap() {}

    public ModelMap addAttribute(String name, Object value) {
        this.put(name, value);
        return this;
    }
}

