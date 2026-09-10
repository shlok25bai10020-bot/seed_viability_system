package service;

import exception.ExpiredSeedException;
import model.SeedBatch;
import java.time.LocalDate;

public class ReportService {
    private final InventoryService inventoryService;
    private final ViabilityCalculationService viabilityService;

    public ReportService(InventoryService inventoryService, ViabilityCalculationService viabilityService) {
        this.inventoryService = inventoryService;
        this.viabilityService = viabilityService;
    }

    public void generateStatusReport(LocalDate currDate) {
        System.out.println("SEED INVENTORY REPORT:");

        for (SeedBatch b : inventoryService.getallBatches()) {
            try {
                double viability = viabilityService.calculateCurrentViability(b, currDate);
                double min = b.getCropType().getMinimumViabilityThreshold();

                System.out.println(b.getBatchId() +": " + " "+b.getCropType()+": " +  
                                   b.getQuantity() +" "+ Math.round(viability) + "% " + 
                                   Math.round(min)+"% " );

            } catch (ExpiredSeedException e) {
                System.out.println(b.getBatchId() +   b.getCropType() +  
                                   b.getQuantity() );
            }
        }
    }
}