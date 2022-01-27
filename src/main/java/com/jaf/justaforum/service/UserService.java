package com.jaf.justaforum.service;

import com.jaf.justaforum.dao.TokenDao;
import com.jaf.justaforum.dao.UserDao;
import com.jaf.justaforum.dto.UserRegistrationDto;
import com.jaf.justaforum.exception.NotAuthorizedException;
import com.jaf.justaforum.exception.UserNotFoundException;
import com.jaf.justaforum.model.Token;
import com.jaf.justaforum.model.User;
import com.jaf.justaforum.util.EmailUtil;
import org.apache.commons.codec.digest.DigestUtils;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class UserService {
    private final UserDao userDao = new UserDao();
    private final TokenDao tokenDao = new TokenDao();

    /*
	 metoda przyjmuje obiekt klasy UserRegistrationDto zawięrający dane użytkownika podane podczas rejestracji,
	 hashuje hasło, zapisuję użytkownika do bazy danych, generuje token po czym wysyła go na maila podanego
	 przez użytkownika (w skrócie rejestracja użytkownika)
	 */
    public void register(UserRegistrationDto userRegistrationDto) {
        User userToSave = UserMapper.map(userRegistrationDto);
        try {
            hashPasswordWithSha256(userToSave);
            userDao.save(userToSave);
            Token token = new Token(userToSave.getId());
            tokenDao.saveToken(token);
            EmailUtil.sendActivationEmail(userToSave.getEmail(),token.getToken());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //zamiana jawnego hasła usera na hashowanie za pomocą "Sha256"
    private void hashPasswordWithSha256(User user) throws NoSuchAlgorithmException {
        String sha256Password = DigestUtils.sha256Hex(user.getPassword());
        user.setPassword(sha256Password);
    }

    //ustawia usera jako "aktywny" po potwierdzeniu maila
    public void confirmUser(Token token) {
        Short active = 1;

        userDao.updateActive(token.getUserId(), active);
        tokenDao.deleteToken(token.getId());
    }

    //przyjmuje nazwę uzytkownika, po czym go zwraca z bazy danych
    public User getUserByUsername(String username) throws UserNotFoundException {
        return userDao.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    //przyjmuje id usera po czym go usuwa
    public void delUserById(Long id) throws UserNotFoundException, NotAuthorizedException {
        userDao.deleteById(id);
    }

    //przyjmuje username oraz nowe hasło, po czym zmienia hasło podanemu użytkownikowi
    public void changePassword(String username, String newPassword) {
        userDao.updatePasswordByUsername(username, DigestUtils.sha256Hex(newPassword));
    }

    //maper, który konwertuje obiekt klasy UserRegistrationDto na obiekt klasy User
    private static class UserMapper {
        static User map(UserRegistrationDto userRegistrationDto) {
            return new User(
                    userRegistrationDto.getUsername(),
                    userRegistrationDto.getEmail(),
                    userRegistrationDto.getPassword(),
                    LocalDateTime.now(),
                    (short) 0
            );
        }
    }
}