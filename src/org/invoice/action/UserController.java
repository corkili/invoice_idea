package org.invoice.action;

import org.apache.log4j.Logger;
import org.invoice.model.User;
import org.invoice.service.UserService;
import org.invoice.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * 用户控制器
 * Created by 李浩然 on 2017/4/8.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/login", name = "登录", method = RequestMethod.GET)
    public ModelAndView inputLoginInformation(@ModelAttribute("user") User user){
        logger.info("input Login Information");
        ModelAndView modelAndView = new ModelAndView("login");
        if (user == null) {
            modelAndView.addObject("user", new User());
        } else {
            modelAndView.addObject("user", user);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/login", name = "登录", method = RequestMethod.POST)
    public String validateLoginInformation(@ModelAttribute("user") User user,
                                           BindingResult bindingResult, HttpSession session,
                                           RedirectAttributes redirectAttributes) {
        logger.info("validate Login Information");
        if (!userService.login(user, bindingResult)) {
            FieldError fieldError = bindingResult.getFieldError();
            logger.info("Code:" + fieldError.getCode() + ", field" + fieldError.getField());
            return "login";
        } else {
            redirectAttributes.addFlashAttribute("message", "Login successful!");
            redirectAttributes.addFlashAttribute("user", userService.findUserByUserName(user.getUsername()));
//            session.setAttribute("user", user);
            return "redirect:/main";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(@ModelAttribute("user") User user) {
        logger.info("input register information");
        ModelAndView modelAndView = new ModelAndView("signin");
        if (user == null) {
            logger.info("user is null");
            modelAndView.addObject("user", new User());
        } else {
            modelAndView.addObject("user", user);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        logger.info("validate register information");
        if (!userService.register(user, bindingResult)) {
            FieldError fieldError = bindingResult.getFieldError();
            logger.info("Code:" + fieldError.getCode() + ", field" + fieldError.getField());
            return "signin";
        } else {
            redirectAttributes.addFlashAttribute("message", "Login successful!");
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/user_manage", method = RequestMethod.GET)
    public ModelAndView userManage() {
        ModelAndView modelAndView = new ModelAndView("user_manage");
        modelAndView.addObject("has_authority", false);
        return modelAndView;
    }

    @RequestMapping(value = "/main", name = "主页")
    public ModelAndView loginSuccessfulAndTurnToMainPage(@ModelAttribute("user") User user) {
        return new ModelAndView("main").addObject("user", user);
    }
}
