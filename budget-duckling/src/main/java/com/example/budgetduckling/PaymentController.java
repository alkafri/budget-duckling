package com.example.budgetduckling;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class PaymentController {
    private PaymentDAO paymentDAO;

    public PaymentController(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @RequestMapping("/invoices")
    public String getAllPayments(Model model, @RequestParam(value = "username")String userName, @RequestParam(required=false, value = "msg")String msgValue) {

        List<Payment> payments = paymentDAO.getAllPayments(userName);

        model.addAttribute("allPayments", payments);
        model.addAttribute("parusername", userName);

        //model.addAttribute("styColor", "red");

        if(Objects.equals(msgValue, "saved")) {
            model.addAttribute("msg", "SAVED! Your invoices has been added to the list!");
        } else if (Objects.equals(msgValue, "deleted")) {
            model.addAttribute("msg", "DELETED! Record deleted successfully!");
        }else if (Objects.equals(msgValue, "updated")){
            model.addAttribute("msg", "UPDATED! Record updated successfully!");
        }
        return "invoices";
    }





    @RequestMapping("/invoicesadd")
    public String showPaymentForm(Model model, @RequestParam(required=false, value = "username")String userName) {
        model.addAttribute("parusername", userName);
        return "invoicesadd";
    }

    @RequestMapping("/addrecord")
    public String addRecord(@RequestParam(value = "paymenttitle") String paymentTitle,
                            @RequestParam(value = "paymentdate") Date paymentDate,
                            @RequestParam(value = "paymentdescription") String paymentDescription,
                            @RequestParam(value = "paymentcategory") String paymentCategory,
                            @RequestParam(value = "paymentamount") Double paymentAmount,
                            @RequestParam(value = "paymentuser") String paymentUser,
                            Model model, RedirectAttributes redirectAttributes,@ModelAttribute("Payment") Payment payment) throws SQLException {

        paymentDAO.addPayment(paymentTitle,paymentDate,paymentDescription,paymentCategory,paymentAmount,paymentUser);


        return "redirect:/invoices?username="+paymentUser+"&msg=saved";
    }





    @RequestMapping("invoices/delete")
    public String deleteRecord(Model model,
                               @RequestParam(value = "id")int recID, @RequestParam(value = "username")String paymentUser) {
        paymentDAO.deletePayment(recID);
        return "redirect:/invoices?username="+paymentUser+"&msg=deleted";
    }





    @RequestMapping("invoicesupdate")
    public String getOnePayment(Model model,
                                @RequestParam(value = "id")int recID, @RequestParam(value = "username")String paymentUser) {
        Payment payments = paymentDAO.getOnePayment(recID);

        model.addAttribute("pId", payments.payment_id);
        model.addAttribute("pTitle", payments.payment_title);
        model.addAttribute("pDate", payments.payment_date);
        model.addAttribute("pDescription", payments.payment_description);
        model.addAttribute("pCategory", payments.payment_category);
        model.addAttribute("pPrice", payments.payment_price);

        model.addAttribute("parusername", paymentUser);


        return "invoicesupdate";
    }

    @RequestMapping("invoicesupdateg")
    public String updateRecord(@RequestParam(value = "paymentid") int paymentId,
                               @RequestParam(value = "paymenttitle") String paymentTitle,
                               @RequestParam(value = "paymentdate") Date paymentDate,
                               @RequestParam(value = "paymentdescription") String paymentDescription,
                               @RequestParam(value = "paymentcategory") String paymentCategory,
                               @RequestParam(value = "paymentamount") Double paymentAmount,
                               @RequestParam(value = "paymentuser") String paymentUser,
                               Model model, RedirectAttributes redirectAttributes,@ModelAttribute("Payment") Payment payment) throws SQLException {
        paymentDAO.updatePayment(paymentId,paymentTitle,paymentDate,paymentDescription,paymentCategory,paymentAmount);
        return "redirect:/invoices?username="+paymentUser+"&msg=updated";
    }



}
