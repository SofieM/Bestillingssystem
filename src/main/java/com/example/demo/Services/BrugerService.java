package com.example.demo.Services;

import com.example.demo.Models.Bruger;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface BrugerService {

    public void opretBruger (Bruger bruger) throws SQLException, ClassNotFoundException;
    public boolean validerBruger(Bruger bruger) throws SQLException, ClassNotFoundException;
    public boolean tjekAdminLogin (String brugernavn, String password);

}
