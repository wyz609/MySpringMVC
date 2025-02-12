package com.wen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Class name: TestController
 * Package: com.wen.controller
 * Description:
 *
 * @Create: 2025/2/9
 * @Author: jay
 * @Version: 1.0
 */
@Controller
public class TestController {

    @RequestMapping("/test")
    public String dem01(){
        return "test";
    }

    @RequestMapping("/home")
    public String demo02(Model model){
        model.addAttribute("hello","hello SpringMVC...");
        return "home";
    }
}
