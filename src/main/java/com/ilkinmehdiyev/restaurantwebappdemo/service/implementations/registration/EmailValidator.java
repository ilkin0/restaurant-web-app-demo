package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.registration;

import com.ilkinmehdiyev.restaurantwebappdemo.exception.EmailIsNotValidException;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {

    public static String EMAIL_VALIDATION_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static  Pattern pattern = Pattern.compile(EMAIL_VALIDATION_PATTERN);

    @Override
    public boolean test(String email) throws EmailIsNotValidException{
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
