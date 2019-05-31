package com.example.demo.Services;

import com.example.demo.Models.Bruger;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

//lavet af Sofie og Christine
//interfacet implementeres af BrugerServiceImpl
//og kaldes i vores controllerklasse, for at få adgang til metoderne
@Service
public interface BrugerService {

    public void opretBruger (Bruger bruger) throws SQLException, ClassNotFoundException;
    public boolean validerBruger(Bruger bruger) throws SQLException, ClassNotFoundException;
    public boolean tjekAdminLogin (String brugernavn, String password);

}
