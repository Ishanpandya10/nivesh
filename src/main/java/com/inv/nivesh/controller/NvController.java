package com.inv.nivesh.controller;

import com.inv.nivesh.entity.User;
import com.inv.nivesh.service.MyJdbcUserDetailsManager;
import com.inv.nivesh.service.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NvController {

    @Autowired
    MyJdbcUserDetailsManager myJdbcUserDetailsManager;

    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/index")
    public String showUserList(Model model) {
        return "index";
    }

    @GetMapping("/registerUser")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "createUser";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute User user, Model model) {
        System.out.println("user=> " + user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        myJdbcUserDetailsManager.createUser(new SecurityUser(user));
        return "createUser";
    }
}
