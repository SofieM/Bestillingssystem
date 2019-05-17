package com.example.demo;

import com.example.demo.Repositories.BrugerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class BestillingssystemApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SpringApplication.run(BestillingssystemApplication.class, args);

        BrugerRepository br = new BrugerRepository();
        br.createBrugerTabel();
    }

}
