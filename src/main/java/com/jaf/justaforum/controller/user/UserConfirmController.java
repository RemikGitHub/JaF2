package com.jaf.justaforum.controller.user;

import com.jaf.justaforum.dao.TokenDao;
import com.jaf.justaforum.model.Token;
import com.jaf.justaforum.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

//serwlet wykorzystywany do potwierdzenia użytownika za pomocą maila
@WebServlet("/confirm")
public class UserConfirmController extends HttpServlet {
    private final TokenDao tokenDao = new TokenDao();
    private final UserService userService = new UserService();

    //zwraca widok, który informuje użytkownika czy konto zostało aktywowane
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String token = request.getParameter("token");

        Optional<Token> optionalToken = tokenDao.findByToken(token);

        if (optionalToken.isPresent()) {
            userService.confirmUser(optionalToken.get());
            response.sendRedirect("/login?confirm=true");
        } else {
            response.sendRedirect("/login?confirm=false");
        }
    }
}
