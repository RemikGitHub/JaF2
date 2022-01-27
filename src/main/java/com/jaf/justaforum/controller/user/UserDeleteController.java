package com.jaf.justaforum.controller.user;

import com.jaf.justaforum.exception.NotAuthorizedException;
import com.jaf.justaforum.exception.UserNotFoundException;
import com.jaf.justaforum.service.UserService;
import com.jaf.justaforum.validation.UserValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//serwlet wykorzystywany do usuwania użytkownika
@WebServlet("/del-user")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"USER", "MODERATOR", "ADMIN"})
        }
)
public class UserDeleteController extends HttpServlet {
    private final UserService userService = new UserService();

    //usuwa użytkownika
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.valueOf(request.getParameter("id"));
            String username = request.getUserPrincipal().getName();

            UserValidation.delUserValidation(username, id);
            userService.delUserById(id);

            response.sendRedirect("/logout");
        } catch (NotAuthorizedException e) {
            request.setAttribute("notAuthorized", e.getMessage());
            request.getRequestDispatcher("WEB-INF/views/posts/myposts.jsp").forward(request, response);
        } catch (UserNotFoundException e) {
            request.setAttribute("userNotFound", e.getMessage());
            request.getRequestDispatcher("WEB-INF/views/posts/myposts.jsp").forward(request, response);
        }
    }
}
