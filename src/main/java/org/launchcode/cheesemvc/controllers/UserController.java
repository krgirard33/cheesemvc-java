package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {


    @RequestMapping(value="user/add")
    public String add(Model model) {
        model.addAttribute("title", "Add User");
        model.addAttribute(new User());

        return "user/add";
    }

}
