package com.example.budgetduckling;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class PaymentRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment payment = new Payment();
        payment.setPaymentId(rs.getInt("id"));
        payment.setPaymentUserName(rs.getString("user_name"));
        payment.setPaymentTitle(rs.getString("payment_title"));
        payment.setPaymentDate(rs.getDate("payment_date"));
        payment.setPaymentDescription(rs.getString("payment_description"));
        payment.setPaymentCategory(rs.getString("payment_category"));
        payment.setPaymentPrice(rs.getDouble("payment_price"));

        return payment;
    }

}
