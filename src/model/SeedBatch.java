package model;
import java.time.LocalDate;
public class SeedBatch {
    private final String batch_Id;
    private final CropType crop_type;
    private  final String supplier_Name;
    private final LocalDate harvest_date;
    private final LocalDate expiry_date;
    private double quantity;
    private double initial_germination_rate;

    public SeedBatch(String batch_Id,CropType crop_type, String supplier_Name, LocalDate expiry_date, LocalDate harvest_date, double quantity, double initial_germination_rate){
        this.batch_Id=batch_Id;
        this.crop_type=crop_type;
        this.supplier_Name=supplier_Name;
        this.harvest_date=harvest_date;
        this.expiry_date=expiry_date;
        this.quantity=quantity;
        this.initial_germination_rate=initial_germination_rate;
    }
    public String getBatchId() { 
        return batch_Id; 
    }
    public CropType getCropType() {
         return crop_type;
         }
    public String getSupplierName() {
         return supplier_Name; 
        }
    public LocalDate getHarvestDate() { return harvest_date; }
    public LocalDate getExpiryDate() { return expiry_date; }
    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }
    public double getInitialGerminationRate() { return initial_germination_rate; }
    public void setInitialGerminationRate(double initial_germination_rate) { 
        this.initial_germination_rate = initial_germination_rate; 
    }
    @Override 
    public  String toString(){
        return  String.format("[%s] Crop: %-8s | Available: %6.1f kg | Harvest: %s | Expiry: %s | Base Germ: %.1f%%",batch_Id,crop_type,quantity,harvest_date,expiry_date,initial_germination_rate*100);
    }
}