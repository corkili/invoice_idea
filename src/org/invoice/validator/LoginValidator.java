package org.invoice.validator;

import org.apache.log4j.Logger;
import org.invoice.model.User;
import org.invoice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by 李浩然 on 2017/4/8.
 */
public class LoginValidator implements Validator {

    private UserService userService;

    public LoginValidator(UserService userService) {
        this.userService = userService;
    }

    private Logger logger = Logger.getLogger(LoginValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        logger.info("validating the login information");
        User user = (User) target;
        if(StringUtils.isEmpty(user.getUsername())) {
            logger.info("validate: \"username\" is empty!");
            errors.rejectValue("username", "username.required", "用户名不能为空");
            return;
        }
        if(StringUtils.isEmpty(user.getPassword())) {
            logger.info("validate: \"password\" is empty!");
            errors.rejectValue("password", "password.required", "密码不能为空");
            return;
        }
        if(userService == null) {
            logger.info("userService is null");
        }
        User userTest = userService.findUserByUserNameFromDB(user.getUsername());
        if (userTest == null) {
            logger.info("validate: there is no user in database");
            errors.rejectValue("username", "username.not_exists", "用户不存在");
            return;
        }
        userTest = userService.findUserByUserNameAndPasswordFromDB(user.getUsername(), user.getPassword());
        if (userTest == null) {
            logger.info("validate: password error!");
            errors.rejectValue("password", "password.error", "密码错误");
            return;
        }
    }
}
