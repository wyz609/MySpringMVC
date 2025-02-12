package com.wen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Class name: RequestMappingTestController
 * Package: com.wen.controller
 * Description:
 *
 * @Create: 2025/2/12
 * @Author: jay
 * @Version: 1.0
 */
@Controller
public class RequestMappingTestController {

    @RequestMapping({"/testValue1","/testValue2"})
    public String testValue(){
        return "testvalue";
    }

    //@RequestMapping("/x?z/testAntValue")
//    @RequestMapping("/x*z/testAntValue")
    @RequestMapping("/testAntValue/**")
    public String testAntValue(){
        return "testAntValue";
    }

    @RequestMapping("/user/*/profile/**")
    public String handlerRequest(){
        return "profile";
    }

    @RequestMapping(value = "/testRESTful/{id}/{username}/{age}")
    public String testRESTful(@PathVariable("id") int id, @PathVariable("username")String username,@PathVariable("age")int age){
        System.out.println("id="+id+",username="+username+",age="+age);
        return "testRESTful";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(){
        return "success";
    }
}
