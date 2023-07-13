/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
public class EditAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String update_id = request.getParameter("update_id");
        AccountDAO dao = new AccountDAO();
        request.setAttribute("account_update", dao.getAccountByID(update_id));
        request.getRequestDispatcher("./admin/UpdateAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId_get = request.getParameter("userID");
        int account_role = Integer.parseInt(request.getParameter("account_role"));

        AccountDAO ac = new AccountDAO();
        ac.updateRole(userId_get, account_role);
        response.sendRedirect("AccountManagement");

//         PrintWriter pr = resp.getWriter();
//         pr.print("123" + " " + account_role);
    }

}
