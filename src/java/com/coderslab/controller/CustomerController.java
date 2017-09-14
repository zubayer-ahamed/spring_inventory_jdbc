package com.coderslab.controller;

import com.coderslab.dao.CustomerDao;
import com.coderslab.entity.Customer;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String customerPage(ModelMap modelMap, HttpServletRequest request) {
        modelMap.addAttribute("sm", request.getParameter("sm"));
        modelMap.addAttribute("em", request.getParameter("em"));
        modelMap.addAttribute("customers", customerDao.getAllCustomer());
        return "customer";
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public String saveCustomer(ModelMap modelMap, HttpServletRequest request) {
        Customer customer = new Customer();
        customer.setCname(request.getParameter("cname"));
        customer.setPhone(request.getParameter("phone"));
        boolean status = customerDao.saveCustomer(customer);
        if (status) {
            modelMap.addAttribute("sm", "Customer Info Saved Successfully");
        } else {
            modelMap.addAttribute("em", "Customer Info Not Saved");
        }
        return "redirect:/customer";
    }

    @RequestMapping(value = "/editCustomer/{id}", method = RequestMethod.GET)
    public String editCustomer(@PathVariable("id") String id, ModelMap modelMap) {
        Customer customer = customerDao.getCustomer(Integer.parseInt(id));
        modelMap.addAttribute("customer", customer);
        modelMap.addAttribute("customers", customerDao.getAllCustomer());
        return "customer";
    }

    @RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
    public String updateCustomer(ModelMap modelMap, HttpServletRequest request) {
        Customer customer = new Customer();
        customer.setCid(Integer.parseInt(request.getParameter("cid")));
        customer.setCname(request.getParameter("cname"));
        customer.setPhone(request.getParameter("phone"));
        boolean status = customerDao.updateCustomer(customer);
        if (status) {
            modelMap.addAttribute("sm", "Customer Info Update Successfully");
        } else {
            modelMap.addAttribute("em", "Customer Info Not Update");
        }
        return "redirect:/customer";
    }

    @RequestMapping(value = "/deleteCustomer/{id}", method = RequestMethod.GET)
    public String deleteCustomer(@PathVariable("id") String id, ModelMap modelMap) {
        boolean status = customerDao.deleteCustomer(Integer.parseInt(id));
        if (status) {
            modelMap.addAttribute("sm", "Customer Info Deleted Successfully");
        } else {
            modelMap.addAttribute("em", "Customer Info Not Deleted");
        }
        return "redirect:/customer";
    }

}
