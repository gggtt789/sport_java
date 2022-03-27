package com.sport.sport.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

    @RequestMapping("/pages/teams")
    public String teams() { return "teams"; }

    @RequestMapping("/pages/members")
    public String members() { return "members"; }
}
