package com.wen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Class name: UserController
 * Package: com.wen.controller
 * Description:
 *
 * @Create: 2025/2/11
 * @Author: jay
 * @Version: 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/detail")
    public String toDetail(){
        return "/user/detail";
    }
}
