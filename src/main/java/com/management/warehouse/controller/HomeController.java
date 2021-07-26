package com.management.warehouse.controller;

import com.management.warehouse.service.ContainerService;
import com.management.warehouse.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.GregorianCalendar;

@Controller
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    ContainerService containerService;

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:login?logout";
    }

    @GetMapping("/user")
    public String userPage(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
            return "userPage";
        } else {
            return "home";
        }
    }

    @GetMapping("/details")
    public String userDetails(Model model) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        model.addAttribute("portalUser", userService.findByLogin(user.getUsername()));
        return "details";
    }
}
