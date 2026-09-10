import exception.ExpiredSeedException;
import exception.InadequateStockException;
import model.CropType;
import model.SeedBatch;
import service.InventoryService;
import service.ReportService;
import service.ViabilityCalculationService;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryService inventoryService = new InventoryService();
        ViabilityCalculationService viabilityService = new ViabilityCalculationService();
        ReportService reportService = new ReportService(inventoryService, viabilityService);

        // Pre-populating sample records
        inventoryService.addBatch(new SeedBatch("B101", CropType.RICE, 500, 95.0,
                LocalDate.now().minusMonths(6), LocalDate.now().plusMonths(12)));
        inventoryService.addBatch(new SeedBatch("B102", CropType.SOYBEAN, 300, 90.0,
                LocalDate.now().minusMonths(14), LocalDate.now().plusMonths(6)));
        inventoryService.addBatch(new SeedBatch("B103", CropType.RAGI, 200, 85.0,
                LocalDate.now().minusMonths(24), LocalDate.now().minusMonths(1)));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("--- Seed Viability Management System ---");
            System.out.println("1. View Inventory & Viability Report");
            System.out.println("2. Add Seed Batch");
            System.out.println("3. Check Batch Viability Status");
            System.out.println("4. Dispatch/Deduct Stock");
            System.out.println("5. Exit");
            System.out.print("Select Option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    reportService.generateStatusReport(LocalDate.now());
                    System.out.println();
                    break;

                case 2:
                    System.out.print("Enter Batch ID: ");
                    String id = scanner.nextLine().trim();
                    System.out.print("Enter Crop (RICE, MAIZE, RAGI, SOYBEAN): ");
                    String cropInput = scanner.nextLine().trim().toUpperCase();
                    CropType crop;
                    try {
                        crop = CropType.valueOf(cropInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid crop! Must be RICE, MAIZE, RAGI, or SOYBEAN.");
                        break;
                    }
                    System.out.print("Enter Quantity: ");
                    int qty = scanner.nextInt();
                    System.out.print("Enter Initial Viability %: ");
                    double viability = scanner.nextDouble();
                    scanner.nextLine(); 
                    inventoryService.addBatch(new SeedBatch(id, crop, qty, viability,
                            LocalDate.now(), LocalDate.now().plusYears(1)));
                    System.out.println("Batch successfully registered.\n");
                    break;

                case 3:
                    System.out.print("Enter Batch ID: ");
                    String checkId = scanner.nextLine().trim();

                    SeedBatch batch = inventoryService.getBatch(checkId).orElse(null);
                    if (batch == null) {
                        System.out.println("Batch not found.");
                        break;
                    }

                    try {
                        double currentViability = viabilityService.calculateCurrentViability(batch, LocalDate.now());
                        boolean suitable = viabilityService.isViableForPlanting(batch, LocalDate.now());
                        System.out.println("Current Viability: " + currentViability + "%");
                        System.out.println("Planting Ready   : " + (suitable ? "YES" : "NO") + "\n");
                    } catch (ExpiredSeedException e) {
                        System.out.println("Error: " + e.getMessage() + "\n");
                    }
                    break;

                case 4:
                    System.out.print("Enter Batch ID: ");
                    String deductId = scanner.nextLine().trim();

                    System.out.print("Enter Quantity to Deduct: ");
                    int deductQty = scanner.nextInt();
                    scanner.nextLine(); 

                    try {
                        inventoryService.deductStock(deductId, deductQty);
                        System.out.println("Stock updated successfully.\n");
                    } catch (InadequateStockException | IllegalArgumentException e) {
                        System.out.println("Transaction Failed: " + e.getMessage() + "\n");
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("Terminating system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid selection. Please choose an option from 1 to 5.\n");
            }
        }
        scanner.close();
    }
}