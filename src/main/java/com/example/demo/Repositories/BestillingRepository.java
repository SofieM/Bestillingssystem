package com.example.demo.Repositories;

import com.example.demo.Configurations.DatabaseConfig;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@Repository
public class BestillingRepository {

    public void insertBestilling(int brugerID, String bestilling, String dato, String klokkeslet) throws SQLException, ClassNotFoundException {

        String insertBestilling = "INSERT INTO bestilling" +
                "(brugerID, " +
                "bestilling, " +
                "dato, " +
                "klokkeslet) " +

                "VALUES " +
                "('" + brugerID + "', '" +
                bestilling + "', '" +
                dato +"', '" +
                klokkeslet + "')";
        SQLExecute(insertBestilling);
    }

    public ResultSet selectBestillinger() throws SQLException, ClassNotFoundException {

        Statement stmt = DatabaseConfig.getConnection().createStatement();
        String selectAlleBestillinger= "SELECT bestilling.bestillingsID, bestilling.bestilling, bestilling.dato, bestilling.klokkeslet, bestilling.status, bruger.fornavn, bruger.efternavn, bruger.telefon, bruger.email " +
                "FROM bestilling " +
                "LEFT JOIN bruger " +
                "ON bestilling.brugerID = bruger.brugerID " +
                "WHERE bestilling.status IS NULL " +
                "ORDER BY bestilling.bestillingsID asc;";
        ResultSet resultSet = stmt.executeQuery(selectAlleBestillinger);
        return resultSet;
    }

    public void deleteBestilling(int id) throws SQLException, ClassNotFoundException{

        String sqlDeleteBestillinger = "DELETE from bestilling " +
                                        "WHERE bestillingsID = " + id + ";";
        SQLExecute(sqlDeleteBestillinger);
    }

    public ResultSet findBestilling (int id) throws SQLException, ClassNotFoundException{

        Statement stmt = DatabaseConfig.getConnection().createStatement();
        String findBestilling = "SELECT DISTINCT bestillingsID, brugerID, bestilling, dato, klokkeslet, status " +
                                "FROM bestilling " +
                                "WHERE bestillingsID = " + id + ";";
        ResultSet resultSet = stmt.executeQuery(findBestilling);
        return resultSet;
    }

    public void updateGodkendtBestilling(int bestillingsID, String status) throws SQLException, ClassNotFoundException {

        String updateBestilling = "UPDATE bestilling " +
                "SET " + "status = '" + status + "' " +
                "WHERE bestillingsID = " + bestillingsID +";";
        SQLExecute(updateBestilling);
    }
    public ResultSet selectGodkendteBestillinger() throws SQLException, ClassNotFoundException {

        Statement stmt = DatabaseConfig.getConnection().createStatement();
        String selectGodkendteBestillinger= "SELECT bestilling.bestillingsID, bestilling.bestilling, bestilling.dato, bestilling.klokkeslet, bestilling.status, bruger.fornavn, bruger.efternavn, bruger.telefon, bruger.email " +
                "FROM bestilling " +
                "LEFT JOIN bruger " +
                "ON bestilling.brugerID = bruger.brugerID " +
                "WHERE bestilling.status = 'godkendt' " +
                "ORDER BY bestilling.dato asc, bestilling.klokkeslet asc ;";
        ResultSet resultSet = stmt.executeQuery(selectGodkendteBestillinger);
        return resultSet;
    }

    public ResultSet findBrugersBestillinger(int id) throws SQLException, ClassNotFoundException{

        Statement stmt = DatabaseConfig.getConnection().createStatement();
        String findBrugersBestillinger = "SELECT bestillingsID, brugerID, bestilling, dato, klokkeslet " +
                                         "FROM bestilling " +
                                         "WHERE brugerID = " + id + " AND status = 'godkendt' " +
                                         "ORDER BY dato asc;";
        ResultSet resultSet = stmt.executeQuery(findBrugersBestillinger);
        return resultSet;
    }

    public void SQLExecute(String SQL) throws SQLException, ClassNotFoundException {

        Statement stmt = DatabaseConfig.getConnection().createStatement();
        stmt.execute(SQL);
        stmt.close();
    }
}
