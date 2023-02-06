package com.example.budgetduckling;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class PaymentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public  List<Payment> getAllPayments(String userName) {
        return jdbcTemplate.query("SELECT id,user_name,payment_title,payment_date,payment_description,payment_category,payment_price FROM payments WHERE user_name = '" + userName + "'", new PaymentRowMapper());
    }

    public Payment getOnePayment(int recID) {
        //return jdbcTemplate.query("SELECT id,user_name,payment_title,payment_date,payment_description,payment_category,payment_price,user_name FROM payments WHERE id = " + recID, new PaymentRowMapper());
        return jdbcTemplate.queryForObject("select id,user_name,payment_title,payment_date,payment_description,payment_category,payment_price,user_name FROM payments WHERE id = ?", new Object[] { recID },
                new PaymentRowMapper());
    }


    public int addPayment(String pTitle, Date pDate, String pDescription,String pCategory, Double pAmount,String userName) {

        return jdbcTemplate.update("INSERT INTO payments (user_name,payment_title, payment_date,payment_description,payment_category, payment_price) VALUES (?, ?, ?, ?, ?, ?)",
                userName,pTitle,pDate,pDescription,pCategory,pAmount);
    }

    public int deletePayment(int id) {
        return jdbcTemplate.update("DELETE FROM payments WHERE id = ?", id);
    }

    public int updatePayment(int pID, String pTitle, Date pDate, String pDescription,String pCategory, Double pAmount) {
        return jdbcTemplate.update("UPDATE payments SET payment_title = ?, payment_date = ?, payment_description = ?, payment_category = ?, payment_price = ? WHERE id = ?",
                pTitle,pDate,pDescription,pCategory,pAmount,pID);
    }

}
