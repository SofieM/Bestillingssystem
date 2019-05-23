package com.example.demo.Services;

import com.example.demo.Models.Bruger;
import com.example.demo.Models.Menu;
import com.example.demo.Repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Override
    public List<Menu> hentMenu() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = menuRepository.selectMenu();

        List<Menu> menu = new ArrayList<>();

        while (resultSet.next()) {

            int itemId = resultSet.getInt("itemID");
            String navn = resultSet.getString("navn");
            int pris = resultSet.getInt("pris");

            menu.add(new Menu(itemId,navn, pris));
        }

        return menu;
    }
}
