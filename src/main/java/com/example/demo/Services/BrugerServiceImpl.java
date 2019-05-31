package com.example.demo.Services;

import com.example.demo.Models.Bruger;
import com.example.demo.Repositories.BrugerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//lavet af Sofie og Christine
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
    public boolean validerBruger(Bruger bruger) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = brugerRepository.selectBruger(bruger);

        while (resultSet.next()){

            bruger.setBrugerID(resultSet.getInt("brugerID"));
            bruger.setBrugernavn(resultSet.getString("brugernavn"));
            bruger.setPassword(resultSet.getString("password"));
            bruger.setFornavn(resultSet.getString("fornavn"));
            bruger.setEfternavn(resultSet.getString("efternavn"));
            bruger.setAdresse(resultSet.getString("adresse"));
            bruger.setTelefon(resultSet.getInt("telefon"));
            bruger.setEmail(resultSet.getString("email"));

                return true;
        }

        return false;
    }

    @Override
    public boolean tjekAdminLogin(String brugernavn, String password) {

        if (brugernavn.equals("admin") && password.equals("admin")){
            return true;
        }
        return false;
    }



}
