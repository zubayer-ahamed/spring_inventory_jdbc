package com.coderslab.entity;

public class Customer {

    private int cid;
    private String cname;
    private String phone;

    public Customer() {
    }

    public Customer(int cid, String cname, String phone) {
        this.cid = cid;
        this.cname = cname;
        this.phone = phone;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" + "cid=" + cid + ", cname=" + cname + ", phone=" + phone + '}';
    }

}
