package com.example.demo.Services;

import com.example.demo.Models.Bestilling;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
@Service
public interface BestillingsService {

    void tilføjBestilling(int brugerID, Bestilling bestilling) throws SQLException, ClassNotFoundException;

}
