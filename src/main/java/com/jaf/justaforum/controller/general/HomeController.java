package com.jaf.justaforum.controller.general;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

//serwlet wykorzystywany do wyświetlania strony głównej
@WebServlet("")
public class HomeController extends HttpServlet {

    //wyświetla strone główną
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);
    }
}