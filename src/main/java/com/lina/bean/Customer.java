package com.lina.bean;

public class Customer {
    private int customer_id;
    private int store_id;
    private String first_name;
    private String last_name;
    private String email;
    private String create_date;

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getCreate_date() {
        return create_date;
    }
}
