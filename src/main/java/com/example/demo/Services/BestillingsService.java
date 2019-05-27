package com.example.demo.Services;

import com.example.demo.Models.Bestilling;
import com.example.demo.Models.Menu;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface BestillingsService {

    void tilføjBestilling(int brugerID, Bestilling bestilling) throws SQLException, ClassNotFoundException;
    public List<Bestilling> hentAlleBestillinger() throws SQLException, ClassNotFoundException;

}
