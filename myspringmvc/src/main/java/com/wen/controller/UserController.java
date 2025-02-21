package com.wen.controller;

import org.myspringmvc.stereotype.Controller;
import org.myspringmvc.web.bind.annotation.RequestMapping;
import org.myspringmvc.web.bind.annotation.RequestMethod;


/**
 * Class name: UserController
 * Package: com.wen.controller
 * Description:
 *
 * @Create: 2025/2/20 18:52
 * @Author: jay
 * @Version: 1.0
 */
@Controller
public class UserController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

}

