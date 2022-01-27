package com.jaf.justaforum.controller.user;

import com.jaf.justaforum.exception.InvalidConfirmPasswordException;
import com.jaf.justaforum.exception.InvalidOldPasswordException;
import com.jaf.justaforum.exception.InvalidPasswordException;
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

//serwlet wykorzystywany do zmiany hasłą
@WebServlet("/change-password")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"USER", "MODERATOR", "ADMIN"}),
                @HttpMethodConstraint(value = "GET", rolesAllowed = {"USER", "MODERATOR", "ADMIN"})
        }
)
public class ChangePasswordController extends HttpServlet {
    private final UserService userService = new UserService();

    //zmienia hasło
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmNewPassword = request.getParameter("confirmNewPassword");
            String username = request.getUserPrincipal().getName();

            UserValidation.oldPasswordValidation(username, oldPassword);
            UserValidation.passwordValidation(newPassword);
            UserValidation.confirmPasswordValidation(newPassword, confirmNewPassword);

            userService.changePassword(username, newPassword);

            request.setAttribute("success", "You changed your password.");

        } catch (InvalidOldPasswordException e) {
            request.setAttribute("oldPasswordError", e.getMessage());
        } catch (InvalidPasswordException e) {
            request.setAttribute("newPasswordError", e.getMessage());
        } catch (InvalidConfirmPasswordException e) {
            request.setAttribute("confirmNewPasswordError", e.getMessage());
        } catch (UserNotFoundException e) {
            request.setAttribute("userError", e.getMessage());
        }

        request.getRequestDispatcher("WEB-INF/views/auth/change-password.jsp").forward(request, response);
    }

    //zwraca widok, gdzie użytkownik może zmienić hasło
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/views/auth/change-password.jsp").forward(request, response);
    }
}
