package model;

public enum CropType {
    RICE(0.80,25.0),
    MAIZE(0.90,20.0),
    RAGI(0.80,10.0),
    SOYBEAN(0.80,75.0);

    private  final double StandardGerminationRate;
    private  final double StandardSeedRatePerHectareKg;

   CropType(double StandardGerminationRate, double StandardSeedRatePerHectareKg) {
        this.StandardGerminationRate = StandardGerminationRate;
        this.StandardSeedRatePerHectareKg = StandardSeedRatePerHectareKg;
    }

    public double getStandardGerminationRate() {
        return StandardGerminationRate;
    }

    public double getStandardSeedRatePerHectareKg() {
        return StandardSeedRatePerHectareKg;
    }
    public double getMinimumViabilityThreshold() {
        return this.StandardGerminationRate * 100.0;
    }
}

