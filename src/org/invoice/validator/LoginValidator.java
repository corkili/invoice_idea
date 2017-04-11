package org.invoice.validator;

import org.invoice.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by 李浩然 on 2017/4/8.
 */
public class LoginValidator implements Validator {



    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmpty(errors, "username", "username.required");
        ValidationUtils.rejectIfEmpty(errors, "password", "password.required");
    }
}
