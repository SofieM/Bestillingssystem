package com.example.demo.Configurations;

import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//lavet af Sofie og Christine
@Configuration
public class DatabaseConfig {
    //opretter forbindelse til vores online database hos GearHost
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connect = DriverManager
                .getConnection("jdbc:mysql://den1.mysql1.gear.host:3306/dat18a2", "dat18a2", "Lp724V-Ec?BN");

        return connect;
    }
}
