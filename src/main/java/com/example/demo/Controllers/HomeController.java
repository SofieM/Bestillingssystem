package com.example.demo.Controllers;

import com.example.demo.Models.Bruger;
import com.example.demo.Models.Menu;
import com.example.demo.Services.BrugerService;
import com.example.demo.Services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    BrugerService brugerService;

    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public String home(){

        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) throws SQLException, ClassNotFoundException {

        //List<Bruger> brugere = brugerService.findBruger(bruger);
        //model.addAttribute("brugere", brugere);

        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute (name="bruger") Bruger bruger, HttpSession session) throws SQLException, ClassNotFoundException {

        //String brugernavn = wr.getParameter("brugernavn");
        //String password = wr.getParameter("password");

        //if(brugerService.tjekAdminLogin(brugernavn,password)){

          //  return "redirect:/adminSide";
        //}

        if (brugerService.validerBruger(bruger)) {

            session.setAttribute("brugerId",bruger.getBrugerID());
            session.setAttribute("brugernavn",bruger.getBrugernavn());
            session.setAttribute("password",bruger.getPassword());
            session.setAttribute("fornavn", bruger.getFornavn());
            session.setAttribute("efternavn",bruger.getEfternavn());
            session.setAttribute("adresse", bruger.getAdresse());
            session.setAttribute("telefon",bruger.getTelefon());
            session.setAttribute("email", bruger.getEmail());


            //session.setAttribute("bruger", bruger);
            return "redirect:/BrugerSide";
        }
        else{
            model.addAttribute("error", true);
            return "login";
        }
    }

    @GetMapping("/opretBruger")
    public String opretBruger(Model model){

        model.addAttribute("bruger", new Bruger());
        return "opretBruger";
    }

    @PostMapping("/opretBruger")
    public String opretBruger(@ModelAttribute Bruger bruger) throws SQLException, ClassNotFoundException {

        brugerService.opretBruger(bruger);
        return "redirect:/login";
    }

    @GetMapping("/BrugerSide")
    public String brugerSide(Model model) throws SQLException, ClassNotFoundException {


        //model.addAttribute("brugere", brugerService.findBruger(id));
                List<Menu> menu = menuService.hentMenu();
                model.addAttribute("menu", menu);

                return "BrugerSide";
    }

    @GetMapping("/lavBestilling")
    public String lavBestilling(Model model) throws SQLException, ClassNotFoundException {
        List<Menu> menu = menuService.hentMenu();
        model.addAttribute("menu", menu);

        return "lavBestilling";
    }

    @GetMapping("/seBestillinger")
    public String seBestillinger(){

        return "seBestillinger";
    }

    @GetMapping("/adminSide")
    public String adminSide(HttpSession session){

        try {
            Object v = session.getAttribute("logged_in");
            if(v instanceof Boolean && (Boolean) v) {

                return "adminSide";
            } else {
                return "redirect:/";
            }
        } catch (Exception ee) {
            return "redirect:/";
        }
    }

    @GetMapping("/alleBestillinger")
    public String alleBestillinger(){

        return "alleBestillinger";
    }

    @GetMapping("/godkendteBestillinger")
    public String godkendteBestillinger(){

        return "godkendteBestillinger";
    }


}
