package com.example.demo.Repositories;

import com.example.demo.Configurations.DatabaseConfig;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@Repository
public class BestillingRepository {

    public void insertBestilling(int brugerID, String bestilling, String dato) throws SQLException, ClassNotFoundException {

        String SQLInsertBruger = "INSERT INTO bestillinger" +
                "(brugerID, " +
                "bestilling, " +
                "dato) " +

                "VALUES " +
                "('" + brugerID + "', '" +
                bestilling + "', '" +
                dato + "')";
        SQLExecute(SQLInsertBruger);

    }

    public ResultSet selectAlleBestillinger() throws SQLException, ClassNotFoundException {

        Statement stmt = DatabaseConfig.getConnection().createStatement();
        String selectAlleBestillinger= "SELECT bestillinger.bestillingsID, bestillinger.bestilling, bestillinger.dato, brugere.fornavn, brugere.efternavn, brugere.telefon, brugere.email " +
                "FROM bestillinger " +
                "LEFT JOIN brugere " +
                "ON bestillinger.brugerID = brugere.brugerID " +
                "ORDER BY bestillinger.bestillingsID asc;";
        ResultSet resultSet = stmt.executeQuery(selectAlleBestillinger);
        return resultSet;
    }

    public void SQLExecute(String SQL) throws SQLException, ClassNotFoundException {

        Statement stmt = DatabaseConfig.getConnection().createStatement();
        stmt.execute(SQL);
        stmt.close();
    }
}
