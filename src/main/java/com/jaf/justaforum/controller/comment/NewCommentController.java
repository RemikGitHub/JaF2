package com.jaf.justaforum.controller.comment;

import com.jaf.justaforum.dto.NewCommentDto;
import com.jaf.justaforum.exception.InvalidCommentContentException;
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

//serwlet wykorzystywany do tworzenia komentarzy
@WebServlet("/new-comment")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = {"USER", "MODERATOR", "ADMIN"}),
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"USER", "MODERATOR", "ADMIN"})
        }
)
public class NewCommentController extends HttpServlet {
    private final CommentService commentService = new CommentService();

    //tworzy komentarz
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String content = request.getParameter("commnetContent");
            String username = request.getUserPrincipal().getName();
            Long id = Long.valueOf(request.getParameter("id"));

            NewCommentDto newCommentDto = new NewCommentDto(content, username, id);

            CommentValidation.newCommentValidation(content);
            commentService.addNewComment(newCommentDto);

        } catch (InvalidCommentContentException e) {
            request.setAttribute("commentContentError", e.getMessage());
        }

        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }
}
