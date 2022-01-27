package com.jaf.justaforum.validation;

import com.jaf.justaforum.dao.UserDao;
import com.jaf.justaforum.exception.*;
import com.jaf.justaforum.dto.UserRegistrationDto;
import com.jaf.justaforum.model.User;
import com.jaf.justaforum.util.RegexChecker;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Optional;

public class UserValidation {
    private static final UserDao userDao = new UserDao();
    private static final String usernameRegex = "^[A-Za-z]\\w{5,29}$";
    private static final String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z\\d\\s:]).{8,20}$";

    //sprawdza czy usuwany user istnieje w bazie danych oraz czy możemy usunąć to konto
    public static void delUserValidation(String username, Long id) throws UserNotFoundException, NotAuthorizedException {
        Optional<User> optionalUser = userDao.findById(id);

        if(optionalUser.isEmpty()) throw new UserNotFoundException("The user does not exist.");
        if (!optionalUser.get().getUsername().equals(username)) throw new NotAuthorizedException("You are not authorized!");
    }

    //sprawdza czy podane dane podczas rejestracji są prawidłowe
    public static void newUserValidation(UserRegistrationDto userRegistrationDto) throws InvalidUsernameException, InvalidPasswordException, InvalidEmailException, InvalidConfirmPasswordException {
        usernameValidation(userRegistrationDto.getUsername());
        emailValidation(userRegistrationDto.getEmail());
        passwordValidation(userRegistrationDto.getPassword());
        confirmPasswordValidation(userRegistrationDto.getPassword(), userRegistrationDto.getConfirmPassword());
    }

    //sprawdza czy nazwa użytkownika jest prawidłowa (od 6 do 30 znaków, zaczyna się od litery oraz nie zawiera innych znaków niż alfanumeryczne oraz "_")
    public static void usernameValidation(String username) throws InvalidUsernameException {
        String errorMessage = "";

        if(!RegexChecker.regexCheck(usernameRegex, username)){

            if (username.length() < 6 || username.length() > 30) errorMessage += "Username must be between 6 and 30 characters long. ";
            if (!RegexChecker.regexCheck("^[a-zA-Z].*", username)) errorMessage += "Username must start with a letter. ";
            if (!RegexChecker.regexCheck("^[a-zA-Z0-9_]*$", username)) errorMessage += "Only alphanumeric characters and underscores \"_\" are allowed. ";

            throw new InvalidUsernameException(errorMessage);
        }

        if (userDao.findByUsername(username).isPresent()) {
            errorMessage += "This username already exists. ";

            throw new InvalidUsernameException(errorMessage);
        }
    }

    //sprawdza czy podano prawidłowy email (czy posiada znak @ itp. oraz czy już nie istnieje podany email w bazie danych)
    public static void emailValidation(String email) throws InvalidEmailException {
        String errorMessage = "";

        if(!RegexChecker.regexCheck(emailRegex, email)){

            errorMessage = "Invalid email.";

            throw new InvalidEmailException(errorMessage);
        }

        if (userDao.findByEmail(email).isPresent()) {
            errorMessage += "This email already exists. ";

            throw new InvalidEmailException(errorMessage);
        }
    }

    //sprawdza czy podane hasło jest odpowiednio silne (od 8 do 20 znaków, zawiera co najmniej jeden znak specjalny itp.)
    public static void passwordValidation(String password) throws InvalidPasswordException {
        String errorMessage = "";

        if(!RegexChecker.regexCheck(passwordRegex, password)){

            if (password.length() < 8 || password.length() > 20) errorMessage += "Password must be between 8 and 20 characters long. ";
            if (!RegexChecker.regexCheck(".*[0-9].*", password)) errorMessage += "Must contain at least one digit. ";
            if (!RegexChecker.regexCheck(".*[a-z].*", password)) errorMessage += "Must contain at least one lowercase Latin character [a-z]. ";
            if (!RegexChecker.regexCheck(".*[A-Z].*", password)) errorMessage += "Must contain at least one uppercase Latin character [A-Z]. ";
            if (!RegexChecker.regexCheck(".*[^a-zA-Z\\d\\s:].*", password)) errorMessage += "Must contain at least one special character like. ";

            throw new InvalidPasswordException(errorMessage);
        }
    }

    //sprawdza czy hasło oraz hasło z pola "powtwierdź hasło" są takie same
    public static void confirmPasswordValidation(String password, String confirmPassword) throws InvalidConfirmPasswordException {
        if(!password.equals(confirmPassword)){
            throw new InvalidConfirmPasswordException("Those passwords didn’t match.");
        }
    }

    //sprawdza czy podczas zmieniania hasła, podaliśmy prawidłowe stare hasło
    public static void oldPasswordValidation(String username, String oldPassword) throws InvalidOldPasswordException, UserNotFoundException {
        String userPasswordHash = userDao.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found.")).getPassword();
        String oldPasswordHash = DigestUtils.sha256Hex(oldPassword);

        if(!userPasswordHash.equals(oldPasswordHash)){
            throw new InvalidOldPasswordException("Invalid old password");
        }
    }
}
