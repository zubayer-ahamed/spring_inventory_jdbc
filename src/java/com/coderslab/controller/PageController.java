
package com.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    
    @RequestMapping("/")
    public String indexPage(){
        return "index";
    }
    
    @RequestMapping("/home")
    public String homePage(){
        return "index";
    }
    
    @RequestMapping("/customer")
    public String customerPage(){
        return "customer";
    }
    
}
