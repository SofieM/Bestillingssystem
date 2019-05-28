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
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    BrugerService brugerService;

    @Autowired
    MenuService menuService;

    @Autowired
    BestillingsService bestillingsService;

    @GetMapping("/")
    public String home(){

        return "home";
    }

//    @GetMapping("/order/{id}")
//    public String newOrder(@PathVariable("id") Integer id,  HttpSession session) {
//
//        Object order = session.getAttribute(id.toString());
//        if(order instanceof Integer){
//
//            int amount = (int) order;
//            for (int i = 0; i < ; i++) {
//                amount++;
//                System.out.println(session.getAttribute(id.toString()));
//                System.out.println(id);
//                System.out.println("amount: " + amount);
//            }
//
////            List<Bestilling> bestilling = new ArrayList<>();
////            bestilling.add(new Bestilling(id, amount));
////
////            for (int i = 0; i < bestilling.size() ; i++) {
////
////                System.out.println("ID" + bestilling.get(i).getItemID());
////                System.out.println("Amount" + bestilling.get(i).getAmount());
////
////            }
//        } else {
//            // DEN IKKE FINDES
//            session.setAttribute(id.toString(), 1);
//            System.out.println("findes ikke");
//            System.out.println(session.getAttribute(id.toString()));
//        }
//
//        return "redirect:/lavBestilling";
//    }



    @GetMapping("/login")
    public String login(Model model) throws SQLException, ClassNotFoundException {

        //List<Bruger> brugere = brugerService.findBruger(bruger);
        //model.addAttribute("brugere", brugere);

        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute (name="bruger") Bruger bruger, HttpSession session, WebRequest wr) throws SQLException, ClassNotFoundException {

        String brugernavn = wr.getParameter("brugernavn");
        String password = wr.getParameter("password");

        if(brugerService.tjekAdminLogin(brugernavn,password)){

            return "redirect:/adminSide";
        }

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

                List<Menu> menu = menuService.hentMenu();
                model.addAttribute("menu", menu);

                return "BrugerSide";
    }

    @GetMapping("/lavBestilling")
    public String lavBestilling(Model model, Bestilling bestilling) throws SQLException, ClassNotFoundException {
        List<Menu> menu = menuService.hentMenu();
        model.addAttribute("menu", menu);
        model.addAttribute("bestilling", new Bestilling());

        return "lavBestilling";
    }

    @PostMapping("/lavBestilling")
    public String lavBestilling(@ModelAttribute Bestilling bestilling, @ModelAttribute(name="bruger") Bruger bruger, HttpSession session) throws SQLException, ClassNotFoundException {
        int brugerID = Integer.parseInt(session.getAttribute("brugerId").toString());
        bestillingsService.tilføjBestilling(brugerID,bestilling);
        return "redirect:/lavBestilling";
    }

    @GetMapping("/seBestillinger/{id}")
    public String seBestillinger(@PathVariable ("id") String id , HttpSession session){
        return "seBestillinger";
    }

    @GetMapping("/adminSide")
    public String adminSide(HttpSession session){

//        try {
//            Object v = session.getAttribute("logged_in");
//            if(v instanceof Boolean && (Boolean) v) {
//
//                return "adminSide";
//            } else {
//                return "redirect:/";
//            }
//        } catch (Exception ee) {
//            return "redirect:/";
//        }
        return "adminSide";
    }

    @GetMapping("/alleBestillinger")
    public String alleBestillinger(Model model) throws SQLException, ClassNotFoundException {
        List<Bestilling> alleBestillinger = bestillingsService.hentAlleBestillinger();
        model.addAttribute("alleBestillinger",alleBestillinger);
        return "alleBestillinger";
    }

    @GetMapping("afvis/{id}")
    public String afvisBestilling(@PathVariable ("id") int id) throws SQLException, ClassNotFoundException {

        bestillingsService.sletBestilling(id);
        return "redirect:/alleBestillinger";

    }

    @GetMapping("godkend/{id}")
    public String godkendBestilling(@PathVariable ("id") int id) throws SQLException, ClassNotFoundException{

        bestillingsService.godkendBestilling(id);
        bestillingsService.sletBestilling(id);
        return "redirect:/alleBestillinger";
    }

    @GetMapping("/godkendteBestillinger")
    public String godkendteBestillinger(Model model) throws SQLException, ClassNotFoundException {
        List<Bestilling> godkendteBestillinger = bestillingsService.hentGodkendteBestillinger();
        model.addAttribute("godkendteBestillinger",godkendteBestillinger);
        return "godkendteBestillinger";
    }


}
