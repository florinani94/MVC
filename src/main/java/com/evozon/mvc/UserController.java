package com.evozon.mvc;

import com.evozon.mvc.validator.UserValidator;
import com.evozon.service.SendMail;
import com.evozon.domain.User;
import com.evozon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import java.util.UUID;

/**
 * Created by dianamohanu on 18/07/2016.
 */
@Controller
//@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SendMail orderManager;

    @Autowired
    private UserValidator validator;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String registerNewBackofficeUser(Model model) {
        model.addAttribute("user", new User());
        return "registerUser";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String messageNewBackofficeUser(Model model, @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String pass1, @RequestParam("pass2") String pass2, @RequestParam("path") String path, @ModelAttribute("user") User newUser, BindingResult result) {

        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setPassword(pass1);

        //validation
        validator.validate(newUser, result);

        //check validation errors
        if (result.hasErrors()) {
            return "registerUser";
        }

        int errors = 0;
        if (!pass1.equals(pass2)) {
            model.addAttribute("passwordError", "Passwords don't match!");
            errors++;
        }

        if (userService.checkIfEmailExists(email) == true) {
            model.addAttribute("emailError", "Email already used!");
            errors++;
        }

        if (userService.checkIfUsernameExists(username) == true) {
            model.addAttribute("usernameError", "Username already used!");
            errors++;
        }

        if (errors == 0) {
            User user = new User();
            user.setEmail(email);
            user.setUsername(username);

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(pass1);

            user.setPassword(hashedPassword);
            user.setEnabled(false);
            String keyUrl = username + UUID.randomUUID().toString();
            user.setKeyUrl(keyUrl);

            userService.addUser(user);
            orderManager.sendConfirmationMail(user, path);

            model.addAttribute("message1", "Information submitted successfully.");
            model.addAttribute("message2", "Please verify your email to confirm your account!");
        } else {
            model.addAttribute("message1", "Information NOT submitted.");
        }

        return "registerUser";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginBackofficeUser(Model model) {
        return "loginBackoffice";
    }

    @RequestMapping(value = "loginFailed", method = RequestMethod.GET)
    public String loginBackofficeFailed(Model model) {
        model.addAttribute("error", "true");

        return "loginBackoffice";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logoutBackoffice(Model model) {
        return "logoutBackoffice";
    }

    @RequestMapping(value = "confirm", method = RequestMethod.GET)
    public String confirmBackofficeUser(Model model, @RequestParam("key") String key) {
        if (userService.checkKey(key) == true) {
            userService.activateAccount(key);
            model.addAttribute("message", "Account confirmed successfully!");
        }

        model.addAttribute("message", "Account confirmed successfully!");

        return "confirmAccount";
    }
}
