/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin;

import controller.Format;
import dao.AdminDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
public class AdminControlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminDAO dao = new AdminDAO();

        request.setAttribute("totalAnime", dao.getTotalAnime());
        request.setAttribute("totalUser", dao.getTotalUser());
        request.setAttribute("totaComment", dao.getTotalComment());

        int total = dao.getTotalMoneyADS() + dao.getTotalMoneyPayment();
        Format fm = new Format();
        request.setAttribute("totalMoney", fm.formatNumber(total));

        request.setAttribute("admin", dao.getRoleAdmin());
        request.setAttribute("vip", dao.getRoleVIP());
        request.setAttribute("user", dao.getRoleUser());
        request.getRequestDispatcher("./admin/AdminPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
