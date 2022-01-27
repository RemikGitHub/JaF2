package com.jaf.justaforum.controller.post;

import com.jaf.justaforum.exception.UserNotFoundException;
import com.jaf.justaforum.model.User;
import com.jaf.justaforum.service.PostService;
import com.jaf.justaforum.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//serwlet wykorzystywany do zwracania postów użytownika
@WebServlet("/my-posts")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = {"USER","MODERATOR","ADMIN"}),
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"USER","MODERATOR","ADMIN"})
        }
)
public class MyPostsController extends HttpServlet {
    private final PostService postService = new PostService();
    private final UserService userService = new UserService();

    //zwraca posty użytkownika
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String username = request.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            request.setAttribute("myPosts", postService.getPostsByUsername(username));
            request.setAttribute("userEmail", user.getEmail());
            request.setAttribute("userId", user.getId());
            request.setAttribute("numberOfPosts", postService.getNumberOfUserPosts(username));

        } catch (UserNotFoundException e) {
            request.setAttribute("error", e.getMessage());
        }
        request.getRequestDispatcher("WEB-INF/views/posts/myposts.jsp").forward(request, response);
    }
}
