package com.example.demo.Services;

import com.example.demo.Models.Bestilling;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

//lavet af Sofie og Christine
//interfacet implementeres af BestillingServiceImpl
//og kaldes i vores controllerklasse, for at få adgang til metoderne
@Service
public interface BestillingsService {

    void tilføjBestilling(int brugerID, Bestilling bestilling) throws SQLException, ClassNotFoundException;
    List<Bestilling> hentAlleBestillinger() throws SQLException, ClassNotFoundException;
    List<Bestilling> hentGodkendteBestillinger() throws SQLException, ClassNotFoundException;
    List<Bestilling> hentBrugersBestillinger (int id) throws SQLException, ClassNotFoundException;
    void sletBestilling(int id) throws SQLException, ClassNotFoundException;
    void godkendBestilling(int id) throws SQLException, ClassNotFoundException;

}
