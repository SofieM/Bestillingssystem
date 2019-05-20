package com.example.demo.Repositories;

import com.example.demo.Configurations.DatabaseConfig;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class MenuRepository {

    public ResultSet selectMenu() throws SQLException, ClassNotFoundException {

        Statement stmt = DatabaseConfig.getConnection().createStatement();
        String selectMenu = "SELECT menuItems.itemID, menuItems.navn, priser.pris " +
                            "FROM menuItems " +
                            "LEFT JOIN priser " +
                            "ON menuItems.prisID = priser.prisID " +
                            "ORDER BY menuItems.ItemID asc;";
        ResultSet resultSet = stmt.executeQuery(selectMenu);
        return resultSet;
    }

    public void SQLExecute(String SQL) throws SQLException, ClassNotFoundException {

        Statement stmt = DatabaseConfig.getConnection().createStatement();
        stmt.execute(SQL);
        stmt.close();
    }
}
