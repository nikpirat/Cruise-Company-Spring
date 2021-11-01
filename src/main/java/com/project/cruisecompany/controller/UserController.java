package com.project.cruisecompany.controller;

import com.project.cruisecompany.model.User;
import com.project.cruisecompany.repository.UserRepository;

import com.project.cruisecompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

//    private final UserService userService;
//    private final SecurityService securityService;
//    private final UserValidator userValidator;
//
//    @Autowired
//    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator) {
//        this.userService = userService;
//        this.securityService = securityService;
//        this.userValidator = userValidator;
//    }
//
//    @GetMapping("/registration")
//    public String registration(Model model){
//        model.addAttribute("userForm", new User());
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model){
//        userValidator.validate(userForm,bindingResult);
//
//        if (bindingResult.hasErrors())
//            return "registration";
//        userService.save(userForm);
//        securityService.autoLogin(userForm.getUsername(),userForm.getPassword());
//
//        return "redirect:/welcome";
//    }
//
//    @GetMapping("/login")
//    public String login(Model model, String error, String logout){
//        if (error!=null)
//            model.addAttribute("error","Username or password is incorrect");
//        if (logout!=null)
//            model.addAttribute("message","Logged out successfully");
//        return "login";
//    }
//
//    @GetMapping(value = {"/welcome","/"})
//    public String welcome(Model model){return "welcome";}
//
//    @GetMapping("/admin")
//    public String admin(Model model){return "admin";}


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.save(user);

        return "welcome";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userService.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }
}
