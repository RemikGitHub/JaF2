package com.jaf.justaforum.controller.user;

import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//serwlet wykorzystywany przez j_security_check do logowania
@WebServlet("/login")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = {"USER", "MODERATOR", "ADMIN"}),
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"USER", "MODERATOR", "ADMIN"})
        }
)
public class LoginController extends HttpServlet {
    
	 //zwraca widok porzednio odwiedzanej strony
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }
}