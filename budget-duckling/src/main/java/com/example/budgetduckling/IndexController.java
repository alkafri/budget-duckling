package com.example.budgetduckling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.*;
import java.util.Objects;

@Controller
public class IndexController {

    public Connection conn;
    public Statement stmt;

    public String curUserName = "";
    private String globUserName = "";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void SetIndexController(String globUserName) {
        this.globUserName = globUserName;
    }

    public String GetIndexController(){
        return globUserName;
    }

    /**
     * This method to check the username in DB
     */
    @RequestMapping(value="index", method = RequestMethod.GET)
    public String getUserName(@RequestParam(value = "username") String userName, @RequestParam(value = "pass") String curPassword, Model model, RedirectAttributes redirectAttributes) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/budget_ducklings", "root", "");
        Statement stmt = conn.createStatement();
        String query = "SELECT username FROM users WHERE username = '" + userName + "'";

        SetIndexController(userName);

        ResultSet rs = stmt.executeQuery(query);


        while (rs.next()) {
            curUserName = (rs.getString("username"));
        }
        if (curUserName != "") {
            boolean resPassChk = getPassword(curUserName,curPassword);
            if(resPassChk == true){
                return "redirect:/invoices?username="+curUserName;
            }else{
                model.addAttribute("wrongMsg", "Wrong Password or/and User name, please check and try again");
                //return "redirect:/index?msg=ok";
                return "index";
            }
        } else {
            return "index.html";
        }

    }


    /**
     * This method to add new record in DB
     */
    /*
    @RequestMapping(value="invoicesadd", method = RequestMethod.GET)
    public String insertNewPayment(@RequestParam(value = "paymenttitle") String paymentTitle,
                                   @RequestParam(value = "paymentdate") String paymentDate,
                                   @RequestParam(value = "paymentdescription") String paymentDescription,
                                   @RequestParam(value = "paymentcategory") String paymentCategory,
                                   @RequestParam(value = "paymentamount") int paymentAmount,
                                   Model model, RedirectAttributes redirectAttributes) throws SQLException {

        System.out.println("Hello World");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/budget_ducklings", "root", "");
        Statement stmt = conn.createStatement();

        //String query = "SELECT username FROM users WHERE username = '" + userName + "'";


        return "invoices.html";
    }
    */







    /**
     * This method to check the correct password
     */
    public boolean getPassword(String chkUserName, String chkPassword) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/budget_ducklings", "root", "");
        Statement stmt = conn.createStatement();

        String queryPassword = "SELECT password FROM users WHERE username = '" + chkUserName + "'";

        ResultSet rs = stmt.executeQuery(queryPassword);

        String curPassword = "";
        while (rs.next()) {
            curPassword = (rs.getString("password"));
        }
        if (curPassword != "") {
            if(Objects.equals(curPassword, chkPassword)) {
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }
}
