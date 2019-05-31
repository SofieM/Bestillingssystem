package com.example.demo.Services;

import com.example.demo.Models.Menu;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

//lavet af Sofie og Christine
//interfacet implementeres af MenuServiceImpl
//og kaldes i vores controllerklasse, for at få adgang til metoderne
@Service
public interface MenuService {

    public List<Menu> hentMenu() throws SQLException, ClassNotFoundException;
}
