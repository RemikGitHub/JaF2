package com.jaf.justaforum.controller.post;

import com.jaf.justaforum.dto.NewPostDto;
import com.jaf.justaforum.exception.InvalidPostCategoryException;
import com.jaf.justaforum.exception.InvalidPostContentException;
import com.jaf.justaforum.exception.InvalidPostTitleException;
import com.jaf.justaforum.model.PostCategory;
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
import java.util.Arrays;

//serwlet wykorzystywany do tworzenia posta
@WebServlet("/new-post")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = {"USER","MODERATOR","ADMIN"}),
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"USER","MODERATOR","ADMIN"})
        }
)
public class NewPostController extends HttpServlet {
    private final PostService postService = new PostService();
    
	 //zwraca widok, który umożliwia dodanie posta
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("postCategory", Arrays.asList(PostCategory.values()));

        request.getRequestDispatcher("WEB-INF/views/posts/newpost.jsp").forward(request, response);
    }

    //dodaje post
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            NewPostDto newPostDto = newPostDto(request);
            PostValidation.newPostValidation(newPostDto);
            postService.addNewPost(newPostDto);

            request.setAttribute("confirmAddPost", "The post has been added");
        } catch (InvalidPostTitleException e) {
            request.setAttribute("postTitleError", e.getMessage());
        } catch (InvalidPostContentException e) {
            request.setAttribute("postContentError", e.getMessage());
        } catch (InvalidPostCategoryException e) {
            request.setAttribute("postCategoryError", e.getMessage());
        }
        request.setAttribute("postCategory", Arrays.asList(PostCategory.values()));
        request.getRequestDispatcher("/WEB-INF/views/posts/newpost.jsp").forward(request, response);
    }

    private NewPostDto newPostDto(HttpServletRequest request) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String category = request.getParameter("postCategory");
        String username = request.getUserPrincipal().getName();

        return new NewPostDto(title, content, category, username);
    }
}
