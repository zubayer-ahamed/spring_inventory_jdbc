package com.coderslab.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    

    @RequestMapping("/home")
    public String homePage() {
        return "index";
    }
    
    @RequestMapping("/order_details")
    public String orderDetailsPage() {
        return "order_details";
    }
    
    @RequestMapping("/product_server/{id}")
    public String productServerPage(ModelMap modelMap ,@PathVariable("id") String id, HttpServletRequest request){
        //modelMap.addAttribute("val", id);
        request.setAttribute("val", id);
        return "product_server";
    }
    
    

}
