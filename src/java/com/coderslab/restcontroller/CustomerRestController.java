package com.coderslab.restcontroller;

import com.coderslab.dao.CustomerDao;
import com.coderslab.entity.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    @Autowired
    private CustomerDao customerDao;
    
    @RequestMapping(value = "/allCustomer", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Customer> getAllCustomer(){
        return customerDao.getAllCustomer();
    }
    
}
