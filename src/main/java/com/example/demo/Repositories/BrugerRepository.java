package com.example.demo.Repositories;

import com.example.demo.Configurations.DatabaseConfig;
import com.example.demo.Models.Bruger;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class BrugerRepository {

public void createBrugerTabel() throws SQLException, ClassNotFoundException {

    String SQLCreateBruger = "CREATE TABLE IF NOT EXISTS brugere" +
            "(" +
            "brugerID int," +
            " brugernavn varchar(100)," +
            " password varchar(100), " + " fornavn varchar(100)," +
            " efternavn varchar(100)," + " adresse varchar(100)," +
            " telefon int," + " email varchar(100)"+
            ")";
            SQLExecute(SQLCreateBruger);
}

public void insertBruger(String brugernavn, String password, String fornavn, String efternavn, String adresse, int telefon, String email) throws SQLException, ClassNotFoundException {

    String SQLInsertBruger = "INSERT INTO bruger" +
            "(brugernavn, " +
            "password, " +
            "fornavn, " +
            "efternavn, " +
            "adresse, " +
            "telefon, " +
            "email) " +

            "VALUES " +
            "('" + brugernavn + "', '" +
            password + "', '" +
            fornavn + "', '" +
            efternavn + "', '" +
            adresse + "', '" +
            telefon + "', '" +
            email + "')";
            SQLExecute(SQLInsertBruger);


}

public ResultSet selectBruger(Bruger bruger) throws SQLException, ClassNotFoundException{

    Statement stmt = DatabaseConfig.getConnection().createStatement();
    String selectBruger = "SELECT brugerID, brugernavn, password, fornavn, efternavn, adresse, telefon, email from bruger WHERE brugernavn = '" + bruger.getBrugernavn() + "' AND password = '" + bruger.getPassword()+"'";
    ResultSet resultSet = stmt.executeQuery(selectBruger);
    return resultSet;
}

public void SQLExecute(String SQL) throws SQLException, ClassNotFoundException {

    Statement stmt = DatabaseConfig.getConnection().createStatement();
    stmt.execute(SQL);
    stmt.close();
}
}
