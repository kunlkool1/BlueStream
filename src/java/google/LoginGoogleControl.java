/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package google;

import dao.AccountDAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class LoginGoogleControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        System.out.println("Code: " + code);
        HttpSession session = request.getSession();
        
        if (code == null || code.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
            AccountDAO accountDAO = new AccountDAO();

            if (accountDAO.getAccountByEmail(googlePojo.getEmail()) == null) {
                Account account = new Account();
                String userIDGoogle = googlePojo.getId();
                ArrayList<Account> ul = accountDAO.checkLogin(userIDGoogle);
                if (ul.isEmpty()) {
                    account.setFullName(googlePojo.getEmail());
                    account.setEmail(googlePojo.getEmail());
                    account.setUser_id_google(googlePojo.getId());
                    try {
                        accountDAO.insertWithIDGoogle(account);
                        account = (Account) accountDAO.checkLogin(userIDGoogle).get(0);
                    } catch (Exception ex) {
                        Logger.getLogger(LoginGoogleControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    account = ul.get(0);
                }

                if (session != null && session.getAttribute("account") != null) {
                    response.sendRedirect(request.getContextPath());
                    return;
                }
                session.setAttribute("account", account);
                response.sendRedirect("home");
            } else {
                Account account = new Account();
                session.setAttribute("account", accountDAO.getAccountByEmail(googlePojo.getEmail()));
                response.sendRedirect("home");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
