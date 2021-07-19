package com.management.warehouse.controller;

import com.management.warehouse.model.PortalUser;
import com.management.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/list")
    public String usersList(Model model,
                           @RequestParam("page")Optional<Integer> page,
                           @RequestParam("size")Optional<Integer> size
    ) {
        int currentPage = page.orElse(1);
        int currentSize = size.orElse(5);
        Page<PortalUser> portalUserPage = userService.getUsersPaginated(
                PageRequest.of(currentPage - 1, currentSize)
        );
        model.addAttribute("size", currentSize);
        model.addAttribute("portalUserPage", portalUserPage);
        int totalPages = portalUserPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(1);
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "user";
    }

    @GetMapping("/list/lastName")
    public String userListByName(Model model,
                                 @RequestParam("page")Optional<Integer>page,
                                 @RequestParam("size")Optional<Integer>size,
                                 @RequestParam("startsWith") String startsWith
    ){
        int currentPage = page.orElse(1);
        int currentSize = size.orElse(5);
        Page<PortalUser> portalUserPage = userService.findByLastNameBeginsWith(
                startsWith,
                PageRequest.of(currentPage-1, currentSize)
        );
        model.addAttribute("size", currentSize);
        model.addAttribute("portalUserPage", portalUserPage);
        int totalPages = portalUserPage.getTotalPages();
        if (totalPages>0){
            List<Integer> pageNumbers = new ArrayList<>();
            for(int i= 1; i<= totalPages; i++){
                pageNumbers.add(1);
            }
            model.addAttribute("pageNumbers", pageNumbers);

        }
        return "user";
    }

    @GetMapping("/register")
    public String userRegistration(Model model) {
        model.addAttribute("portalUser", new PortalUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid PortalUser portalUser,
                               BindingResult result) {
        if(userService.findByLogin(portalUser.getLogin()) != null) {
            result.rejectValue("login", "Duplicate.portalUser.login");
        }
        if (portalUser.getPassword().length() < 4) {
            result.rejectValue("password", "Min.portalUser.password");
        }
        if (result.hasErrors()) {
            return "register";
        } else {
            userService.save(portalUser);
            return "redirect:/login";
        }

    }


    @RequestMapping("/paginated")
    public String getUsersPaginated(Model model, @RequestParam("page")Optional<Integer> page) {
        int currentPage = page.orElse(1);
        Page<PortalUser> userPage = userService.getUsersPaginated(PageRequest.of(currentPage-1, 5));
        model.addAttribute("userPage", userPage);
        int totalPages = userPage.getTotalPages();
        if (totalPages>0) {
            List<Integer> pageNumbers = new ArrayList<>();
            for(int i=1; i<=totalPages; i++) {
                pageNumbers.add(i);
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "user";
    }
}
