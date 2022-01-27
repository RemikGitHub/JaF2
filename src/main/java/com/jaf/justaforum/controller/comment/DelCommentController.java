package com.jaf.justaforum.controller.comment;

import com.jaf.justaforum.exception.CommentNotFoundException;
import com.jaf.justaforum.exception.NotAuthorizedException;
import com.jaf.justaforum.service.CommentService;
import com.jaf.justaforum.validation.CommentValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//serwlet wykorzystywany do usuwania komentarzy
@WebServlet("/del-comment")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = {"USER", "MODERATOR", "ADMIN"}),
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"USER", "MODERATOR", "ADMIN"})
        }
)
public class DelCommentController extends HttpServlet {
    private final CommentService commentService = new CommentService();

    //usuwa komentarz
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Long id = Long.valueOf(request.getParameter("id"));
            String username = request.getUserPrincipal().getName();

            CommentValidation.deleteCommentValidation(username, id);
            commentService.delCommentById(id);
        } catch (NotAuthorizedException e) {
            request.setAttribute("notAuthorized", e.getMessage());
        } catch (CommentNotFoundException e) {
            request.setAttribute("commentNotFound", e.getMessage());
        }

        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }
}
