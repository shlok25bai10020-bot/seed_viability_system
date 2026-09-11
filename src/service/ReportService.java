package service;

import exception.ExpiredSeedException;
import model.SeedBatch;
import java.time.LocalDate;

public class ReportService {
    InventoryService storeData;
    ViabilityCalculationService decayCalc;

    public ReportService(InventoryService storeData, ViabilityCalculationService decayCalc) {
        this.storeData = storeData;
        this.decayCalc = decayCalc;
    }

    public void generateStatusReport(LocalDate asOfDate) {
        Object[] batchArray = storeData.getallBatches().toArray();

        if (batchArray.length == 0) {
            System.out.println("Nothing in stock right now.");
            return;
        }

        System.out.println("SEED INVENTORY REPORT:");

        int idx = 0;
        do {
            SeedBatch target = (SeedBatch) batchArray[idx];

            String idTag = target.getBatchId();
            String cropLabel = target.getCropType().name();
            double rawQty = target.getQuantity();

            System.out.print("Batch ID: " + idTag + " | Crop: " + cropLabel + " | Qty: " + (long) rawQty);

            boolean hasFailed = false;
            double pct = 0.0;

            try {
                pct = decayCalc.calculateCurrentViability(target, asOfDate);
            } catch (ExpiredSeedException issue) {
                hasFailed = true;
            }

            if (hasFailed) {
                System.out.println(" | Status: EXPIRED");
            } else {
                double floorReq = target.getCropType().getMinimumViabilityThreshold();

                long displayPct = Math.round(pct);
                long displayMin = Math.round(floorReq);

                String label = "BELOW_THRESHOLD";
                if (pct >= floorReq) {
                    label = "APPROVED";
                }

                System.out.println(" | Viability: " + displayPct + "% | Min Required: " + displayMin + "% | Status: " + label);
            }

            idx++;
        } while (idx < batchArray.length);
    }
}