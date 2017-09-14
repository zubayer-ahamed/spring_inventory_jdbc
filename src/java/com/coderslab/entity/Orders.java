package com.coderslab.entity;

import java.util.Date;

public class Orders {

    private int oid;
    private int cid;
    private double total;
    private String orderType;
    private Date orderDate;

    public Orders() {
    }

    public Orders(int oid, int cid, double total, String orderType, Date orderDate) {
        this.oid = oid;
        this.cid = cid;
        this.total = total;
        this.orderType = orderType;
        this.orderDate = orderDate;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Orders{" + "oid=" + oid + ", cid=" + cid + ", total=" + total + ", orderType=" + orderType + ", orderDate=" + orderDate + '}';
    }

}
