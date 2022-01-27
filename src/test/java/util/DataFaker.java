package util;

import com.github.javafaker.Faker;

public class DataFaker {
    public static String getLogin() {

        return new Faker().name().username().replace(".", "_");
    }

    public static String getPassword() {
        return new Faker().internet().password(4, 16, true, true, true) + "aA1!";
    }

    public static String getShortPassword() {
        return new Faker().internet().password(1, 6);
    }

    public static String getEmail() {
        return new Faker().internet().emailAddress();
    }

    public static String getTitle() {
        return new Faker().gameOfThrones().character() + " TEST";
    }

    public static String getContent() {
        return new Faker().gameOfThrones().quote();
    }
}
