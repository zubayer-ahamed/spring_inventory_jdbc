package com.coderslab.entity;

public class OrderDetails {

    private int odid;
    private int oid;
    private int pid;
    private double price;
    private int qty;

    public OrderDetails() {
    }

    public OrderDetails(int odid, int oid, int pid, double price, int qty) {
        this.odid = odid;
        this.oid = oid;
        this.pid = pid;
        this.price = price;
        this.qty = qty;
    }

    public int getOdid() {
        return odid;
    }

    public void setOdid(int odid) {
        this.odid = odid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "odid=" + odid + ", oid=" + oid + ", pid=" + pid + ", price=" + price + ", qty=" + qty + '}';
    }

}
