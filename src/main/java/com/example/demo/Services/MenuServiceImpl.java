package com.example.demo.Services;


import com.example.demo.Models.Menu;
import com.example.demo.Repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//Indeholder metoder, der håndterer menu
//lavet af Sofie og Christine
@Service
public class MenuServiceImpl implements MenuService {

    //laver en instans af MenuRepository,
    //så MenuServiceImpl kan kommunikerer med databasen via. repository
    @Autowired
    MenuRepository menuRepository;

    //henter menuen ind gennem repository og gemmer værdierne i en ArrayList
    @Override
    public List<Menu> hentMenu() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = menuRepository.selectMenu();

        List<Menu> menu = new ArrayList<>();

        while (resultSet.next()) {

            int vareID = resultSet.getInt("vareID");
            String navn = resultSet.getString("navn");
            int pris = resultSet.getInt("pris");

            menu.add(new Menu(vareID,navn, pris));
        }

        return menu;
    }
}
