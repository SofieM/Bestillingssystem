package com.example.demo.Services;

import com.example.demo.Models.Bestilling;
import com.example.demo.Repositories.BestillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Indeholder metoder, der håndterer bestillinger
//lavet af Sofie og Christine
@Service
public class BestillingsServiceImpl implements BestillingsService {

    //laver en instans af BestillingRepository,
    //så BestillingsServiceImpl kan kommunikere med databasen via. repository
    @Autowired
    BestillingRepository bestillingRepository;

    @Override
    public void tilføjBestilling(int brugerID, Bestilling bestilling) throws SQLException, ClassNotFoundException {
        bestillingRepository.insertBestilling(brugerID, bestilling.getBestilling(), bestilling.getDato(), bestilling.getKlokkeslet());

    }

    @Override
    public List<Bestilling> hentAlleBestillinger() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = bestillingRepository.selectBestillinger();

        List<Bestilling> bestillinger = indlæsBestilling(resultSet);

        return bestillinger;
    }

    @Override
    public void sletBestilling(int id) throws SQLException, ClassNotFoundException {
        bestillingRepository.deleteBestilling(id);
    }

    @Override
    public void godkendBestilling(int id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = bestillingRepository.findBestilling(id);

        while (resultSet.next()) {

            int bestillingsID = resultSet.getInt("bestillingsID");
            String status = "godkendt";

            bestillingRepository.updateGodkendtBestilling(bestillingsID, status);
        }
    }

    @Override
    public List<Bestilling> hentGodkendteBestillinger() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = bestillingRepository.selectGodkendteBestillinger();

        List<Bestilling> godkendteBestillinger = indlæsBestilling(resultSet);

        return godkendteBestillinger;
    }

    @Override
    public List<Bestilling> hentBrugersBestillinger(int id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = bestillingRepository.findBrugersBestillinger(id);

        List<Bestilling> brugersBestillinger = new ArrayList<>();

        while (resultSet.next()) {

            int bestillingsID = resultSet.getInt("bestillingsID");
            int brugerID = resultSet.getInt("brugerID");
            String bestilling = resultSet.getString("bestilling");
            String dato = resultSet.getString("dato");
            String klokkeslet = resultSet.getString("klokkeslet");


            brugersBestillinger.add(new Bestilling(bestillingsID, brugerID, bestilling, dato, klokkeslet));
        }

        return brugersBestillinger;

    }

    //læser et resultset igennem og lægger hver værdi ind i en ArrayList
    //kaldes fra hentAlleBestillinger og hentGodkendte bestillinger
    //lavet for at undgå redundant kode i de to metoder
    public List<Bestilling> indlæsBestilling(ResultSet resultSet) throws SQLException {


        List<Bestilling> bestillinger = new ArrayList<>();

        while (resultSet.next()) {

            int bestillingsID = resultSet.getInt("bestillingsID");
            String bestilling = resultSet.getString("bestilling");
            String dato = resultSet.getString("dato");
            String klokkeslet = resultSet.getString("klokkeslet");
            String status = resultSet.getString("status");
            String brugerFornavn = resultSet.getString("fornavn");
            String brugerEfternavn = resultSet.getString("efternavn");
            int brugerTelefon = resultSet.getInt("telefon");
            String brugerEmail = resultSet.getString("email");


            bestillinger.add(new Bestilling(bestillingsID, bestilling, dato, klokkeslet, status, brugerFornavn, brugerEfternavn, brugerTelefon, brugerEmail));
        }
            return bestillinger;
    }

}



