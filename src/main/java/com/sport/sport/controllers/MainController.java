package com.sport.sport.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/pages/teams/{id}")
    public String team(@PathVariable Long id) { return "team"; }

    @RequestMapping("/pages/events/{id}")
    public String event(@PathVariable Long id) { return "event"; }

    @RequestMapping("/pages/members/{id}")
    public String member(@PathVariable Long id) { return "member"; }
}
