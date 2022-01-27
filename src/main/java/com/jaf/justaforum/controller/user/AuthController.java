package com.jaf.justaforum.controller.user;

import com.jaf.justaforum.dao.UserDao;
import com.jaf.justaforum.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

//serwlet wykorzystywany do logowania użytkownika
@WebServlet("/auth")
public class AuthController extends HttpServlet {
    private final UserDao userDao = new UserDao();

    //loguje użytkownika oraz sprawdza czy użytkownik zaznaczył opcje "remember me"
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<User> user = userDao.findByUsername(username);
        if (request.getParameter("rememberMe") != null) {
            request.getSession().setMaxInactiveInterval(-1);
        }

        if (user.isPresent() && user.get().getActive() == 0) {
            request.setAttribute("confirmError", "You have to active your email: " + user.get().getEmail());
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        } else {
            String url = (request.getContextPath() + "/j_security_check?j_username=" + username + "&j_password=" + password);
            response.sendRedirect(url);
        }
    }
	 
    //zwraca widok, który informuje użytkownika o podaniu błędnej nazwy lub hasła
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect("/login?error=true");
    }
}
