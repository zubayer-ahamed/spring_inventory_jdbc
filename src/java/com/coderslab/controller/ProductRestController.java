
package com.coderslab.controller;

import com.coderslab.dao.ProductDao;
import com.coderslab.entity.Product;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductRestController {
    
    @Autowired
    private ProductDao productDao;
    
    @RequestMapping(value = "/allProduct", method = RequestMethod.GET,  headers = "Accept=application/json")
    public List<Product> getAllProduct() {
        List<Product> listProduct = productDao.getAllProduct();
        return listProduct;
    }
    
}
