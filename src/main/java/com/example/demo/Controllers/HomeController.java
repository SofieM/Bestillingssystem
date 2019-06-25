package com.example.demo.Controllers;

import com.example.demo.Models.Bestilling;
import com.example.demo.Models.Bruger;
import com.example.demo.Models.Menu;
import com.example.demo.Services.BestillingsService;
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
//lavet af Sofie og Christine
@Controller
public class HomeController {

    @Autowired
    BrugerService brugerService;

    @Autowired
    MenuService menuService;

    @Autowired
    BestillingsService bestillingsService;

    @GetMapping("/")
    public String home(Model model) throws SQLException, ClassNotFoundException {
        List<Menu> menu = menuService.hentMenu();
        model.addAttribute("menu", menu);
        return "home";
    }

    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute (name="bruger") Bruger bruger, HttpSession session, WebRequest wr) throws SQLException, ClassNotFoundException {

        String brugernavn = wr.getParameter("brugernavn");
        String password = wr.getParameter("password");

        if(brugerService.tjekAdminLogin(brugernavn,password)){
            session.setAttribute("logget_ind", true);
            return "redirect:/adminSide";
        }
        //finder Bruger-attributter og gemmer dem som sessions-attributter, så brugerens informationer er
        //tilgængelige i hele sessionen
        //sessions-attributterne benyttes når brugeren laver en ny bestilling, så man ved hvilken bruger, bestillingen er lavet af
        if (brugerService.validerBruger(bruger)) {

            session.setAttribute("brugerId",bruger.getBrugerID());
            session.setAttribute("brugernavn",bruger.getBrugernavn());
            session.setAttribute("password",bruger.getPassword());
            session.setAttribute("fornavn", bruger.getFornavn());
            session.setAttribute("efternavn",bruger.getEfternavn());
            session.setAttribute("adresse", bruger.getAdresse());
            session.setAttribute("telefon",bruger.getTelefon());
            session.setAttribute("email", bruger.getEmail());
            session.setAttribute("logget_ind", true);
            return "redirect:/BrugerSide";
        }
        else{
            model.addAttribute("error", true);
            return "login";
        }
    }

    @GetMapping("/opretBruger")
    public String opretBruger(Model model) {

        model.addAttribute("bruger", new Bruger());
        return "opretBruger";
    }

    @PostMapping("/opretBruger")
    public String opretBruger(@ModelAttribute Bruger bruger) throws SQLException, ClassNotFoundException {

        brugerService.opretBruger(bruger);
        return "redirect:/login";
    }

    @GetMapping("/BrugerSide")
    public String brugerSide(HttpSession session){

        try {
            Object v = session.getAttribute("logget_ind");
            if(v instanceof Boolean && (Boolean) v) {

                return "BrugerSide";
            } else {
                return "redirect:/";
            }
        } catch (Exception ee) {
            return "redirect:/";
        }
    }

    @GetMapping("/lavBestilling")
    public String lavBestilling(Model model, HttpSession session) throws SQLException, ClassNotFoundException {

        try {
            Object v = session.getAttribute("logget_ind");
            if(v instanceof Boolean && (Boolean) v) {
                List<Menu> menu = menuService.hentMenu();
                model.addAttribute("menu", menu);
                model.addAttribute("bestilling", new Bestilling());

                return "lavBestilling";
            } else {
                return "redirect:/";
            }
        } catch (Exception ee) {
            return "redirect:/";
        }

    }

    @PostMapping("/lavBestilling")
    public String lavBestilling(@ModelAttribute Bestilling bestilling, @ModelAttribute(name="bruger") Bruger bruger, HttpSession session) throws SQLException, ClassNotFoundException {
        int brugerID = Integer.parseInt(session.getAttribute("brugerId").toString()); //parser sessions-attributtet (som er et objekt) til en int
        bestillingsService.tilføjBestilling(brugerID,bestilling);
        return "redirect:/lavBestilling";
    }

    @GetMapping("/seBestillinger/{id}")
    public String seBestillinger(@PathVariable ("id") String id , HttpSession session, Model model) throws SQLException, ClassNotFoundException {
        try {
            Object v = session.getAttribute("logget_ind");
            if(v instanceof Boolean && (Boolean) v) {
                int brugerID = Integer.parseInt(session.getAttribute("brugerId").toString());
                List<Bestilling> brugersBestillinger = bestillingsService.hentBrugersBestillinger(brugerID);
                model.addAttribute("brugersBestillinger", brugersBestillinger);

                return "seBestillinger";
            } else {
                return "redirect:/";
            }
        } catch (Exception ee) {
            return "redirect:/";
        }

    }

    @GetMapping("/adminSide")
    public String adminSide(HttpSession session){
        try {
            Object v = session.getAttribute("logget_ind");
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
    public String alleBestillinger(Model model, HttpSession session) throws SQLException, ClassNotFoundException {

        try {
            Object v = session.getAttribute("logget_ind");
            if(v instanceof Boolean && (Boolean) v) {
                List<Bestilling> alleBestillinger = bestillingsService.hentAlleBestillinger();
                model.addAttribute("alleBestillinger",alleBestillinger);
                return "alleBestillinger";
            } else {
                return "redirect:/";
            }
        } catch (Exception ee) {
            return "redirect:/";
        }

    }

    @GetMapping("afvis/{id}")
    public String afvisBestilling(@PathVariable ("id") int id, HttpSession session) throws SQLException, ClassNotFoundException {
        try {
            Object v = session.getAttribute("logget_ind");
            if(v instanceof Boolean && (Boolean) v) {
                bestillingsService.sletBestilling(id);
                return "redirect:/alleBestillinger";
            } else {
                return "redirect:/";
            }
        } catch (Exception ee) {
            return "redirect:/";
        }

    }

    @GetMapping("godkend/{id}")
    public String godkendBestilling(@PathVariable ("id") int id, HttpSession session) throws SQLException, ClassNotFoundException{

        try {
            Object v = session.getAttribute("logget_ind");
            if(v instanceof Boolean && (Boolean) v) {
                bestillingsService.godkendBestilling(id);
                return "redirect:/alleBestillinger";
            } else {
                return "redirect:/";
            }
        } catch (Exception ee) {
            return "redirect:/";
        }

    }

    @GetMapping("/godkendteBestillinger")
    public String godkendteBestillinger(Model model, HttpSession session) throws SQLException, ClassNotFoundException {

        try {
            Object v = session.getAttribute("logget_ind");
            if(v instanceof Boolean && (Boolean) v) {
                List<Bestilling> godkendteBestillinger = bestillingsService.hentGodkendteBestillinger();
                model.addAttribute("godkendteBestillinger",godkendteBestillinger);
                return "godkendteBestillinger";
            } else {
                return "redirect:/";
            }
        } catch (Exception ee) {
            return "redirect:/";
        }

    }

    @GetMapping("afslut/{id}")
    public String afslutBestilling(@PathVariable ("id") int id, HttpSession session) throws SQLException, ClassNotFoundException {
        try {
            Object v = session.getAttribute("logget_ind");
            if(v instanceof Boolean && (Boolean) v) {
                bestillingsService.sletBestilling(id);
                return "redirect:/godkendteBestillinger";
            } else {
                return "redirect:/";
            }
        } catch (Exception ee) {
            return "redirect:/";
        }


    }

}
