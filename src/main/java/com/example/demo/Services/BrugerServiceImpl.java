package com.example.demo.Services;

import com.example.demo.Models.Bruger;
import com.example.demo.Repositories.BrugerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrugerServiceImpl implements BrugerService{

    @Autowired
    BrugerRepository brugerRepository;

    @Override
    public void opretBruger(Bruger bruger) throws SQLException, ClassNotFoundException {

    brugerRepository.insertBruger(bruger.getBrugernavn(),
            bruger.getPassword(),
            bruger.getFornavn(),
            bruger.getEfternavn(),
            bruger.getAdresse(),
            bruger.getTelefon(),
            bruger.getEmail());

    }
    @Override
    public boolean validerBruger(String brugernavn, String password) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = brugerRepository.selectBrugerLogin();

        List<Bruger> brugere = new ArrayList<>();

        while (resultSet.next()) {

            String gemtBrugernavn = resultSet.getString("brugernavn");
            String gemtPassword = resultSet.getString("password");
            
            brugere.add(new Bruger(gemtBrugernavn, gemtPassword));
        }

        String bNavn;
        String pWord;

        for (int i = 0; i < brugere.size(); i++) {

            bNavn = brugere.get(i).getBrugernavn();
            pWord = brugere.get(i).getPassword();


            if (brugernavn.equals(bNavn) && password.equals(pWord)) {

                return true;
            }

       }
            return false;

    }

    public Bruger findBruger(String brugernavn, String password) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = brugerRepository.selectBruger(brugernavn, password);

        Bruger bruger = new Bruger();
        while (resultSet.next()) {

            String gemtFornavn = resultSet.getString("fornavn");
            String gemtEfternavn = resultSet.getString("efternavn");
            String gemtAdresse = resultSet.getString("adresse");
            int gemtTelefon = resultSet.getInt("telefon");
            String gemtEmail = resultSet.getString("email");

            bruger = new Bruger(brugernavn, password, gemtFornavn, gemtEfternavn, gemtAdresse, gemtTelefon, gemtEmail);
        }

        return bruger;

    }

    @Override
    public boolean tjekAdminLogin(String username, String password) {

        if (username.equals("admin") && password.equals("admin")){
            return true;
        }
        return false;
    }

}
