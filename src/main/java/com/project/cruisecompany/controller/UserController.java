package com.project.cruisecompany.controller;

import com.project.cruisecompany.model.CruiseInfo;
import com.project.cruisecompany.model.Ship;
import com.project.cruisecompany.model.User;
import com.project.cruisecompany.repository.CruiseInfoRepository;
import com.project.cruisecompany.repository.ShipRepository;

import com.project.cruisecompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    private final ShipRepository shipRepository;

    private final CruiseInfoRepository cruiseInfoRepository;

    @Autowired
    public UserController(UserService userService, ShipRepository shipRepository, CruiseInfoRepository cruiseInfoRepository) {
        this.userService = userService;
        this.shipRepository = shipRepository;
        this.cruiseInfoRepository = cruiseInfoRepository;
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
        userService.save(user);

        return "users";
    }



    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userService.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @PostMapping("/users")
    public String listUsers(){
        userService.findAll();

        return "users";
    }

    @GetMapping("/user")
    public String userCabinet(@RequestParam(value = "id", required = false) Long id, Model model){
        List<Ship> shipList = shipRepository.findAll();
        model.addAttribute("shipList", shipList);

        model.addAttribute("id", id);
//        List<CruiseInfo> cruiseInfoList = cruiseInfoRepository.findAll();

        return "user";
    }

    @PostMapping("/user")
    public String userCabinet(){
        shipRepository.findAll();
        return "user";
    }
}
