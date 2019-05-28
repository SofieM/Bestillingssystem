package com.example.demo.Services;

import com.example.demo.Models.Bestilling;
import com.example.demo.Repositories.BestillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
public class BestillingsServiceImpl implements BestillingsService{

    @Autowired
    BestillingRepository bestillingRepository;

    @Override
    public void tilføjBestilling(int brugerID, Bestilling bestilling) throws SQLException, ClassNotFoundException {
        bestillingRepository.insertBestilling(brugerID, bestilling.getBestilling(), bestilling.getDato());

    }

    @Override
    public List<Bestilling> hentAlleBestillinger() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = bestillingRepository.selectAlleBestillinger();

        List<Bestilling> bestillinger = new ArrayList<>();

        while (resultSet.next()) {

            int bestillingsID = resultSet.getInt("bestillingsID");
            String bestilling = resultSet.getString("bestilling");
            String dato = resultSet.getString("dato");
            String brugerFornavn = resultSet.getString("fornavn");
            String brugerEfternavn = resultSet.getString("efternavn");
            int brugerTelefon = resultSet.getInt("telefon");
            String brugerEmail = resultSet.getString("email");

            bestillinger.add(new Bestilling(bestillingsID, bestilling, dato, brugerFornavn,brugerEfternavn,brugerTelefon,brugerEmail));
        }

        return bestillinger;
    }

    @Override
    public void sletBestilling(int id) throws SQLException, ClassNotFoundException {
        bestillingRepository.deleteBestilling(id);
    }

    @Override
    public void godkendBestilling (int id) throws SQLException, ClassNotFoundException{

        ResultSet resultSet = bestillingRepository.findBestilling(id);

        while (resultSet.next()) {

            int bestillingsID = resultSet.getInt("bestillingsID");
            int brugerID = resultSet.getInt("brugerID");
            String bestilling = resultSet.getString("bestilling");
            String dato = resultSet.getString("dato");

            bestillingRepository.insertGodkendtBestilling(bestillingsID, brugerID, bestilling, dato);
        }

    }

    @Override
    public List<Bestilling> hentGodkendteBestillinger() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = bestillingRepository.selectGodkendteBestillinger();

        List<Bestilling> godkendteBestillinger = new ArrayList<>();

        while (resultSet.next()) {

            int bestillingsID = resultSet.getInt("bestillingsID");
            String bestilling = resultSet.getString("bestilling");
            String dato = resultSet.getString("dato");
            String brugerFornavn = resultSet.getString("fornavn");
            String brugerEfternavn = resultSet.getString("efternavn");
            int brugerTelefon = resultSet.getInt("telefon");
            String brugerEmail = resultSet.getString("email");

            godkendteBestillinger.add(new Bestilling(bestillingsID, bestilling, dato, brugerFornavn,brugerEfternavn,brugerTelefon,brugerEmail));
        }

        return godkendteBestillinger;
    }

    @Override
    public List<Bestilling> hentBrugersBestillinger(int id) throws SQLException, ClassNotFoundException{

            ResultSet resultSet = bestillingRepository.findBrugersBestillinger(id);

            List<Bestilling> brugersBstillinger = new ArrayList<>();

            while (resultSet.next()) {

                int bestillingsID = resultSet.getInt("bestillingsID");
                String bestilling = resultSet.getString("bestilling");
                String dato = resultSet.getString("dato");


                brugersBstillinger.add(new Bestilling(bestillingsID, bestilling, dato));
            }

            return brugersBstillinger;
        }
    }



