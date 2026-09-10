package service;

import exception.ExpiredSeedException;
import model.SeedBatch;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ViabilityCalculationService{
    private static final double monthly_degradation_rate=0.015;

    public double calculateCurrentViability(SeedBatch batch, LocalDate curDate)throws ExpiredSeedException{
        if(curDate.isAfter(batch.getExpiryDate())){
            throw new ExpiredSeedException("Batch " + batch.getBatchId() + " expired on " + batch.getExpiryDate());
        }
    }
}

