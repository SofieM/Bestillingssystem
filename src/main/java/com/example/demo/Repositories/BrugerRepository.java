package com.example.demo.Repositories;

import com.example.demo.Configurations.DatabaseConfig;
import org.springframework.stereotype.Repository;

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

    String SQLInsertBruger = "INSERT INTO brugere" +
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

public ResultSet selectBruger() throws SQLException, ClassNotFoundException {

    Statement stmt = DatabaseConfig.getConnection().createStatement();
    String SQLSelectBruger = "SELECT brugernavn, password from brugere";
    ResultSet resultSet = stmt.executeQuery(SQLSelectBruger);
    return resultSet;
}

public void SQLExecute(String SQL) throws SQLException, ClassNotFoundException {

    Statement stmt = DatabaseConfig.getConnection().createStatement();
    stmt.execute(SQL);
    stmt.close();
}
}
