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
    public boolean validerBruger(String brugernavn, String password) throws SQLException, ClassNotFoundException{

        ResultSet resultSet = brugerRepository.selectBruger();

        List<Bruger> brugere = new ArrayList<>();

        while (resultSet.next()){

            String gemtBrugernavn = resultSet.getString("brugernavn");
            String gemtPassword = resultSet.getString("password");

            brugere.add(new Bruger(gemtBrugernavn, gemtPassword));
        }

        String bNavn;
        String pWord;
        for (int i = 0; i < brugere.size(); i++) {

            bNavn = brugere.get(i).getBrugernavn();
            pWord = brugere.get(i).getPassword();
            if (brugernavn.equals(bNavn) && password.equals(pWord)){

                return true;
            }

            else{

                return false;
            }
        }
        return false;
    }

    @Override
    public List<Bruger> findBruger() {
        return null;
    }
}
