package com.example.demo.Services;

import com.example.demo.Models.Menu;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface MenuService {

    public List<Menu> hentMenu() throws SQLException, ClassNotFoundException;
}
