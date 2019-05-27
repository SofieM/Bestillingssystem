package com.example.demo.Services;

import com.example.demo.Models.Bestilling;
import com.example.demo.Repositories.BestillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
public class BestillingsServiceImpl implements BestillingsService{

    @Autowired
    BestillingRepository bestillingRepository;

    @Override
    public void tilføjBestilling(int brugerID, Bestilling bestilling) throws SQLException, ClassNotFoundException {
        bestillingRepository.insertBestilling(brugerID, bestilling.getBestilling(), bestilling.getDato());

    }
}


