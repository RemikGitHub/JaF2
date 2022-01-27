package com.jaf.justaforum.controller.general;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//serwlet wykorzystywany do wyświetlania strony "about-us"
@WebServlet("/about-us")
public class AboutUsController extends HttpServlet {
    
	 //wyświetla strone "about-us"
	 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/views/about.jsp").forward(request, response);
    }
}
