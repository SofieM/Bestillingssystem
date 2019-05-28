package com.example.demo.Repositories;

import com.example.demo.Configurations.DatabaseConfig;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
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

    public void deleteBestilling(int id) throws SQLException, ClassNotFoundException{

        String sqlDeleteBestillinger = "DELETE from bestillinger " +
                                        "WHERE bestillingsID = " + id + ";";
        SQLExecute(sqlDeleteBestillinger);
    }

    public ResultSet findBestilling (int id) throws SQLException, ClassNotFoundException{

        Statement stmt = DatabaseConfig.getConnection().createStatement();
        String findBestilling = "SELECT DISTINCT bestillingsID, brugerID, bestilling, dato " +
                                "FROM bestillinger " +
                                "WHERE bestillingsID = " + id + ";";
        ResultSet resultSet = stmt.executeQuery(findBestilling);
        return resultSet;
    }

    public void insertGodkendtBestilling(int bestillingsID, int brugerID, String bestilling, String dato) throws SQLException, ClassNotFoundException {

        String insertGodkendtBestilling = "INSERT INTO godkendtebestillinger" +
                "(bestillingsID, " +
                "brugerID, " +
                "bestilling, " +
                "dato) " +

                "VALUES " +
                "('" + bestillingsID + "', '" + brugerID + "', '" +
                bestilling + "', '" +
                dato + "')";
        SQLExecute(insertGodkendtBestilling);
    }
    public ResultSet selectGodkendteBestillinger() throws SQLException, ClassNotFoundException {

        Statement stmt = DatabaseConfig.getConnection().createStatement();
        String selectGodkendteBestillinger= "SELECT godkendtebestillinger.bestillingsID, godkendtebestillinger.bestilling, godkendtebestillinger.dato, brugere.fornavn, brugere.efternavn, brugere.telefon, brugere.email " +
                "FROM godkendtebestillinger " +
                "LEFT JOIN brugere " +
                "ON godkendtebestillinger.brugerID = brugere.brugerID " +
                "ORDER BY godkendtebestillinger.dato asc ;";
        ResultSet resultSet = stmt.executeQuery(selectGodkendteBestillinger);
        return resultSet;
    }

    public ResultSet findBrugersBestillinger(int id) throws SQLException, ClassNotFoundException{

        Statement stmt = DatabaseConfig.getConnection().createStatement();
        String findBrugersBestillinger = "SELECT bestillingsID, bestilling, dato " +
                                         "FROM godkendtebestillinger " +
                                         "WHERE brugerID = " + id + ";";
        ResultSet resultSet = stmt.executeQuery(findBrugersBestillinger);
        return resultSet;
    }

    public void SQLExecute(String SQL) throws SQLException, ClassNotFoundException {

        Statement stmt = DatabaseConfig.getConnection().createStatement();
        stmt.execute(SQL);
        stmt.close();
    }
}
