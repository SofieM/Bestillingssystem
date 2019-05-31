package com.example.demo.Repositories;

import com.example.demo.Configurations.DatabaseConfig;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//lavet af Sofie og Christine
@Repository
public class MenuRepository {
    //joiner de to tabeller vare og pris på prisID og sorterer dem efter vareID - vores "menukort"
    public ResultSet selectMenu() throws SQLException, ClassNotFoundException {

        Statement stmt = DatabaseConfig.getConnection().createStatement();
        String selectMenu = "SELECT vare.vareID, vare.navn, pris.pris " +
                            "FROM vare " +
                            "LEFT JOIN pris " +
                            "ON vare.prisID = pris.prisID " +
                            "ORDER BY vare.vareID asc;";
        ResultSet resultSet = stmt.executeQuery(selectMenu);
        return resultSet;
    }

    public void SQLExecute(String SQL) throws SQLException, ClassNotFoundException {

        Statement stmt = DatabaseConfig.getConnection().createStatement();
        stmt.execute(SQL);
        stmt.close();
    }
}
