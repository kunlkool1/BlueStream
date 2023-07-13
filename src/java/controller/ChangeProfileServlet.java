/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class ChangeProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Format ns = new Format();
        Account ac = new Account();
        HttpSession session = request.getSession();
        ac = (Account) session.getAttribute("account");
        String userName = ac.getUserName();
        String passWord = ac.getPassword();
        String fName = ns.nameStandardization(request.getParameter("fullname"));
        String phone = request.getParameter("phone");
        String yearOfBirth = request.getParameter("yearOfBirth");
        Part part = request.getPart("avatar_pic");

        String avatar_filename = part.getSubmittedFileName();

        int index = avatar_filename.lastIndexOf(".");
        String ext = avatar_filename.substring(index + 1);
        avatar_filename = userName + "_avatar123." + ext;
        
        String appPath = getServletContext().getRealPath("");
        File rootDir = new File(appPath).getParentFile().getParentFile();
        avatar_filename = "\\img\\avatar\\" + avatar_filename;
        
        String upLoadPath = rootDir.getAbsolutePath() + "\\web" + avatar_filename;
        
        FileOutputStream fos = new FileOutputStream(upLoadPath);
        InputStream is = part.getInputStream();
        
        byte[] data = new byte[is.available()];
        is.read(data);
        fos.write(data);
        fos.close();

        AccountDAO dao = new AccountDAO();
        dao.updateInfor(userName, fName, phone, yearOfBirth, avatar_filename);
        Account a = dao.CheckLogin(userName, passWord);
        session.setAttribute("account", a);
        request.setAttribute("finish_messe", "<div class=\"col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-12 col-xs-12 finish_mess\">\n"
                + "                                    <Center>\n"
                + "                                        <h4>Change Profile successfully!!</h4>\n"
                + "                                    </Center>\n"
                + "                                </div>");
        request.getRequestDispatcher("profile.jsp").forward(request, response);

    }

}
