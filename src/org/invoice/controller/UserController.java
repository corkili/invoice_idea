package org.invoice.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.invoice.domain.User;
import org.invoice.service.UserService;
import org.invoice.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 李浩然 on 2017/4/8.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    private static final Log logger = LogFactory.getLog(UserController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String inputLoginInformation(Model model){
        model.addAttribute("user", new User());
        return "loginForm";
    }

    @RequestMapping(value = "/login_validate", method = RequestMethod.POST)
    public String validateLoginInformation(@ModelAttribute User user,
                                           BindingResult bindingResult, Model model) {
        LoginValidator loginValidator = new LoginValidator();
        loginValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            logger.info("Code: " + fieldError.getCode() + ", field: " + fieldError.getField());
            return "loginForm";
        }
        if(userService.validateUserLoginInformation(user)) {
            model.addAttribute("user", user);
            return "main";
        } else {
            return "loginForm";
        }
    }
}
