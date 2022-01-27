package com.jaf.justaforum.controller.post;

import com.jaf.justaforum.dto.CommentDto;
import com.jaf.justaforum.dto.PostDto;
import com.jaf.justaforum.exception.PostNotFoundException;
import com.jaf.justaforum.service.CommentService;
import com.jaf.justaforum.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

//serwket wykorzystywany do pokazania pojedynczego posta wraz z komentarzami
@WebServlet("/single-post")
public class SinglePostController extends HttpServlet {
    private final PostService postService = new PostService();
    private final CommentService commentService = new CommentService();

    //zwraca widok pojedynczego posta wraz z jego komentarzami
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Long postId = Long.valueOf(request.getParameter("id"));
            PostDto post = postService.getPostById(postId);
            List<CommentDto> comments = commentService.getPostComments(postId);

            request.setAttribute("post", post);
            request.setAttribute("comments", comments);

        } catch (PostNotFoundException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }
        request.getRequestDispatcher("WEB-INF/views/posts/singlepost.jsp").forward(request, response);
    }

}
