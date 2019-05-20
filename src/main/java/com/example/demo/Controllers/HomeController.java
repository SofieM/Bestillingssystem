package com.example.demo.Controllers;

import com.example.demo.Models.Bruger;
import com.example.demo.Services.BrugerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    BrugerService brugerService;

    @GetMapping("/")
    public String home(){

        return "home";
    }

    @GetMapping("/login")
    public String login(Model model, Bruger bruger, WebRequest wr) throws SQLException, ClassNotFoundException {

        List<Bruger> brugere = brugerService.findBruger();
        model.addAttribute("brugere", brugere);

        return "login";
    }

    @PostMapping("/login")
    public String login(Bruger bruger) throws SQLException, ClassNotFoundException {

    String brugernavn = bruger.getBrugernavn();
    String password = bruger.getPassword();

    if(brugerService.tjekAdminLogin(brugernavn,password)){

        return "adminSide";
    }

    if (brugerService.validerBruger(brugernavn, password)) {

        return "redirect:/BrugerSide";
    }
    else{
        return "login";
    }
    }

    @GetMapping("/BrugerSide")
    public String brugerSide(Model model) throws SQLException, ClassNotFoundException {

        List<Bruger> brugere = brugerService.findBruger();
        model.addAttribute("brugere", brugere);
        return "BrugerSide";
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
