package com.example.budgetduckling;

import java.util.Date;

public class Payment {
    public int payment_id;
    public String payment_user_name;
    public String payment_title;
    public Date payment_date;
    public String payment_description;
    public String payment_category;
    public Double payment_price;



    public int getPaymentId() {
        return payment_id;
    }

    public void setPaymentId(int id) {
        this.payment_id = id;
    }

    public String getPaymentUserName() {
        return payment_user_name;
    }

    public void setPaymentUserName(String user_name) {
        this.payment_user_name = user_name;
    }

    public String getPaymentTitle() {
        return payment_title;
    }

    public void setPaymentTitle(String title) {
        this.payment_title = title;
    }

    public Date getPaymentDate() {
        return payment_date;
    }

    public void setPaymentDate(Date payment_date) {
        this.payment_date = payment_date;
    }

    public String getPaymentDescription() {
        return payment_description;
    }

    public void setPaymentDescription(String description) {this.payment_description = description;}

    public String getPaymentCategory() {
        return payment_category;
    }

    public void setPaymentCategory(String category) {
        this.payment_category = category;
    }

    public Double getPaymentPrice() {
        return payment_price;
    }

    public void setPaymentPrice(Double price) {this.payment_price = price;}


}
