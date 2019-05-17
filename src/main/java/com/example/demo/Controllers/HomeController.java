package com.example.demo.Controllers;

import com.example.demo.Models.Bruger;
import com.example.demo.Services.BrugerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Controller
public class HomeController {

    @Autowired
    BrugerService brugerService;

    @GetMapping("/")
    public String home(){

        return "home";
    }

    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @GetMapping("/opretBruger")
    public String opretBruger(Model model){

        model.addAttribute("bruger", new Bruger());
        return "opretBruger";
    }

    @PostMapping
    public String opretBruger(@ModelAttribute Bruger bruger) throws SQLException, ClassNotFoundException {

        brugerService.opretBruger(bruger);
        return "redirect:/";
    }
}
