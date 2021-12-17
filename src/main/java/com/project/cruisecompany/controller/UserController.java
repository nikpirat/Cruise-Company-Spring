package com.project.cruisecompany.controller;

import com.project.cruisecompany.model.CruiseInfo;
import com.project.cruisecompany.model.Role;
import com.project.cruisecompany.model.Ship;

import com.project.cruisecompany.model.User;
import com.project.cruisecompany.model.enums.RoomType;
import com.project.cruisecompany.service.CruiseInfoService;
import com.project.cruisecompany.service.SecurityService;
import com.project.cruisecompany.service.ShipService;
import com.project.cruisecompany.service.UserService;
import com.project.cruisecompany.service.impl.ShipServiceImpl;
import com.project.cruisecompany.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    private final ShipService shipService;

    private final CruiseInfoService cruiseInfoService;

    private final UserValidator userValidator;

    private final SecurityService securityService;

    @Autowired
    public UserController(UserService userService, ShipServiceImpl shipService, CruiseInfoService cruiseInfoService, UserValidator userValidator, SecurityService securityService) {
        this.userService = userService;
        this.shipService = shipService;
        this.cruiseInfoService = cruiseInfoService;
        this.userValidator = userValidator;
        this.securityService = securityService;
    }

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        System.out.println("hello");
        return "WEB-INF/views/index.jsp";
    }
    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "index";
        userService.save(user);
        securityService.autoLogin(user.getUsername(), user.getPassword());

        return "redirect:/user";
    }

    @GetMapping("/user")
    public String userCabinet(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        User user = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("user", user);

        for (Role role:user.getRoles())
            if (role.getName().equals("ROLE_ADMIN")) return "admin";

        model.addAttribute("shipList", shipService.findAll());
//        List<CruiseInfo> cruiseInfoList = cruiseInfoService.findAllByUserId(user.getId());
//        model.addAttribute("userShips", shipService.findAllUserShips(cruiseInfoList));
//        model.addAttribute("cruisesInfo", cruiseInfoList);
        //todo

        return "user";
    }
    @PostMapping("/user")
    public String userCabinet(@ModelAttribute("shipId") String[] ships, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user = userService.findByUsername(username);


            List<Ship> shipsInOrder = new ArrayList<>();
            for (String order : ships)
                shipsInOrder.add(shipService.findById(Long.valueOf(order)));

            model.addAttribute("orderedCruises", shipsInOrder);
            model.addAttribute("PRESIDENT", RoomType.PRESIDENT.getPrice());
            model.addAttribute("COMFORT", RoomType.PRESIDENT.getPrice());
            model.addAttribute("STANDART", RoomType.PRESIDENT.getPrice());
            model.addAttribute("user", user);
        }
        return "orderDetails";
    }

    @GetMapping("/topUp")
    public String topUp() {
        return "topUp";
    }
    @PostMapping("/topUp")
    public String topUp(@ModelAttribute("amount") String amount) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user = userService.findByUsername(username);

            if (NumberUtils.isNumber(amount) && Float.parseFloat(amount) >= 0) {
                user.setBalance(user.getBalance() + Float.parseFloat(amount));
                userService.update(user);
            }
        }
        return "redirect:/user";
    }

    @GetMapping("/order")
    public String order(){
        return "orderDetails";
    }
    @PostMapping("/order")
    public String order(@ModelAttribute("shipId") String[] ships, @ModelAttribute("type") String type,@ModelAttribute("id") Long id, Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            CruiseInfo cruiseInfo = new CruiseInfo();
            float finalPrice = 0;

            String username = ((UserDetails) principal).getUsername();
            User user = userService.findByUsername(username);

            for(String orderId:ships){
                Ship ship = shipService.findById(Long.valueOf(orderId));
                cruiseInfo.setShipId(Math.toIntExact(ship.getId()));

                if ((type)==null || (type+orderId).isEmpty()){
                    model.addAttribute("chooseRoomType", true);
                    return "redirect:/orderDetails";
                }
                RoomType roomType = RoomType.valueOf(("type" + orderId));

                cruiseInfo.setTotalPrice(ship.getPrice() + roomType.getPrice());
                cruiseInfo.setUserId(Math.toIntExact(id));
                cruiseInfo.setRoomType(roomType);
                finalPrice += ship.getPrice() + roomType.getPrice();

                if (user.getBalance() - finalPrice >= 0)
                    cruiseInfo = cruiseInfoService.create(cruiseInfo);
            }
            if ((user.getBalance() - finalPrice) >= 0) {
                user.setBalance(user.getBalance() - finalPrice);
                userService.update(user);
            } else {
                model.addAttribute("notEnoughMoney", true);
                model.addAttribute("orderPrice", finalPrice);
                model.addAttribute("user", user);
                return "redirect:/orderDetails";
            }
        }
        return "redirect:/user";
    }

    @GetMapping("/admin")
    public String admin(Model model){
//        int recordsPerPage = 3;
//        int currentPage = 1;
//        String recordsPerPageString = req.getParameter("recordsPerPage");
//        if (recordsPerPageString != null && !recordsPerPageString.isEmpty()) {
//            recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
//            currentPage = Integer.parseInt(req.getParameter("currentPage"));
//        }//todo pagination
        List<User> listUsers = userService.findAll();
        model.addAttribute("usersPag", listUsers);
        return "/admin";
    }

}
