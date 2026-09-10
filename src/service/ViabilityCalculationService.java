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
        if(curDate.isBefore(batch.getHarvestDate()) || curDate.isEqual(batch.getHarvestDate())){
            return batch.getInitialViability();
        }
        long duration=ChronoUnit.MONTHS.between(batch.getHarvestDate(), curDate);

        double degradedViability=batch.getInitialViability()*Math.pow(1.0-monthly_degradation_rate, duration);

        double finalViability=Math.max(0.0,degradedViability);
        return Math.round(finalViability*100.0)/100.0;
    }
    public boolean isViableForPlanting(SeedBatch batch, LocalDate curtDate) throws ExpiredSeedException {
    double currentViability = calculateCurrentViability(batch, curtDate);
    double minThreshold = batch.getCropType().getMinimumViabilityThreshold();
    return currentViability >= minThreshold;
}
}

