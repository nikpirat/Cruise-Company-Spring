package com.project.cruisecompany.controller;

import com.project.cruisecompany.model.User;
import com.project.cruisecompany.service.SecurityService;
import com.project.cruisecompany.service.UserService;
import com.project.cruisecompany.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;
    private final UserValidator userValidator;

    @Autowired
    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model){
        userValidator.validate(userForm,bindingResult);

        if (bindingResult.hasErrors())
            return "registration";
        userService.create(userForm);
        securityService.autoLogin(userForm.getName(),userForm.getPassword());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout){
        if (error!=null)
            model.addAttribute("error","Username or password is incorrect");
        if (logout!=null)
            model.addAttribute("message","Logged out successfully");
        return "login";
    }

    @GetMapping(value = {"/welcome","/"})
    public String welcome(Model model){return "welcome";}

    @GetMapping("/admin")
    public String admin(Model model){return "admin";}

}
