/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin;

import dao.MovieDAO;
import entity.Episodes;
import entity.Movie;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class EditEpisodesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MovieDAO dao = new MovieDAO();
        String id = request.getParameter("mid");
        String epNum = request.getParameter("epNum");
        
        request.setAttribute("maxEp", dao.getEpisodeCountByMovieId(id));
        
        if (epNum.isEmpty() == true) {
            int nextEpNum = dao.getEpisodeCountByMovieId(id);
            request.setAttribute("nextEp", nextEpNum);
        } else {
            request.setAttribute("nextEp", epNum);
            Episodes episodes = dao.getEpisodes(id, Integer.parseInt(epNum));
            request.setAttribute("detail", episodes);
        }

        
        request.getRequestDispatcher("./admin/EditEpisodes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String movieId = request.getParameter("movieId");
        String epNum = request.getParameter("epNum");
        int epNumCast = Integer.parseInt(epNum);
        String duration = request.getParameter("ep_duration");
        int durationCast = Integer.parseInt(duration);
        String movieLink = request.getParameter("ep_Link");

        MovieDAO dao = new MovieDAO();
        
        System.out.println(durationCast + " " + movieLink);

        dao.updateEpisodes(movieId, epNumCast, durationCast, movieLink);
        response.sendRedirect("EditMovie?mid=" + movieId);
    }

}
