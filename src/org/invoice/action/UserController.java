package org.invoice.action;

import org.apache.log4j.Logger;
import org.invoice.model.User;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by 李浩然 on 2017/4/8.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String inputLoginInformation(Model model){
        logger.info("input Login Information");
        model.addAttribute("user", new User());
        return "loginForm";
    }

    @RequestMapping(value = "/login_validate", method = RequestMethod.POST)
    public String validateLoginInformation(@ModelAttribute User user,
                                           BindingResult bindingResult, Model model,
                                           RedirectAttributes redirectAttributes) {
        logger.info("validate Login Information");
        LoginValidator loginValidator = new LoginValidator(userService);
        loginValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            logger.info("Code:" + fieldError.getCode() + ", field" + fieldError.getField());
            return "loginForm";
        }
        redirectAttributes.addFlashAttribute("message", "Login successful!");
        model.addAttribute("user", userService.findUserByUserNameAndPasswordFromDB(
                user.getUsername(), user.getPassword()));
        return "redirect:/main";
    }

    @RequestMapping(value = "/main")
    public String loginSuccessfulAndTurnToMainPage() {
        return "main";
    }
}
