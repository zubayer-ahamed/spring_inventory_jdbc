/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.coderslab.dao.OrderDetailsDao;
import com.coderslab.entity.OrderDetails;

/**
 *
 * @author cyclingbd007
 */
public class Test {
    public static void main(String[] args) {
        OrderDetailsDao dao  = new OrderDetailsDao();
//        OrderDetails o = new OrderDetails();
//        o.setOid(1);
//        o.setPid(1);
//        o.setPrice(500);
//        o.setQty(10);
//        dao.saveOrderDetails(o);
        System.out.println(dao.getAllOrderDetails().toString());
    }
}
