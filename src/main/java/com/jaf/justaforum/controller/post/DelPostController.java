package com.jaf.justaforum.controller.post;

import com.jaf.justaforum.exception.NotAuthorizedException;
import com.jaf.justaforum.exception.PostNotFoundException;
import com.jaf.justaforum.service.PostService;
import com.jaf.justaforum.validation.PostValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//serwlet wykorzystywany do usuwania posta
@WebServlet("/del-post")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = {"USER", "MODERATOR", "ADMIN"}),
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"USER", "MODERATOR", "ADMIN"})
        }
)
public class DelPostController extends HttpServlet {
    private final PostService postService = new PostService();

    //usuwa post
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.valueOf(request.getParameter("id"));
            String username = request.getUserPrincipal().getName();

            PostValidation.delPostValidation(username, id);
            postService.delPostById(id);

            response.sendRedirect("/my-posts");
        } catch (NotAuthorizedException e) {
            request.setAttribute("notAuthorized", e.getMessage());
            request.getRequestDispatcher("WEB-INF/views/posts/myposts.jsp").forward(request, response);
        } catch (PostNotFoundException e) {
            request.setAttribute("postNotFound", e.getMessage());
            request.getRequestDispatcher("WEB-INF/views/posts/myposts.jsp").forward(request, response);
        }
    }
}
