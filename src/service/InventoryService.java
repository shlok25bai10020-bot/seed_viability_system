package service;

import exception.InadequateStockException;
import model.SeedBatch;

import java.util.*;

public class InventoryService{
    private final Map<String, SeedBatch>inventory= new HashMap<>();

    public void addBatch(SeedBatch batch){
        if(batch==null || batch.getBatchId()==null){
            throw new IllegalArgumentException("The Batch or Batch ID cannot null");
        }
        inventory.put(batch.getBatchId(),batch);
    }
    public Optional<SeedBatch> getBatch(String batch_Id){
        return Optional.ofNullable(inventory.get(batch_Id));
    }
    public List<SeedBatch> getallBatches(){
        return new ArrayList<>(inventory.values());
    }
    public void deductStock(String batch_Id,double quantity) throws InadequateStockException{
        SeedBatch batch=inventory.get(batch_Id);
        if(batch==null){
            throw new IllegalArgumentException("Batch with ID " + batch_Id + " not found");
        }
        if(batch.getQuantity()<quantity){
            throw new IllegalArgumentException(
                String.format("Available stock is insufficient for required", batch.getQuantity(), quantity)
            );
        }
        batch.setQuantity(batch.getQuantity()-quantity);
    }
}
