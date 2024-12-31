public class Admin {
    private RentalAgency.AddAgent agency;  // Changed to use AddAgent since it's a concrete class
    
    public Admin(RentalAgency.AddAgent agency) {
        this.agency = agency;
    }

    public void addVehicle(RentalAgency agency, Vehicle vehicle) {
        if (agency == null) {
            throw new IllegalArgumentException("Agency cannot be null");
        }
        agency.addVehicle(vehicle);
        System.out.println("Vehicle added: " + vehicle.getVehicleId());
    }

    public void removeVehicle(RentalAgency agency, String vehicleId) {
        if (agency == null) {
            throw new IllegalArgumentException("Agency cannot be null");
        }
        if (agency.removeVehicle(vehicleId)) {
            System.out.println("Vehicle removed: " + vehicleId);
        } else {
            System.out.println("Vehicle not found: " + vehicleId);
        }
    }

    public void generateReport(RentalAgency agency) {
        if (agency == null) {
            throw new IllegalArgumentException("Agency cannot be null");
        }
        agency.listTransactions();
    }

    // DiscountManager remains unchanged
    public static class DiscountManager {
        private double percentageDiscount;
    
        public DiscountManager(double percentageDiscount) {
            this.percentageDiscount = percentageDiscount;
        }
    
        public double applyDiscount(double totalCost) {
            return totalCost - (totalCost * percentageDiscount / 100);
        }
    
        public void setPercentageDiscount(double percentageDiscount) {
            this.percentageDiscount = percentageDiscount;
        }
    
        public double getPercentageDiscount() {
            return percentageDiscount;
        }
    }

    // Add these methods to RentalAgency.java after the existing code

    public void addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        DataStore.getVehicleFleet().put(vehicle.getVehicleId(), vehicle);
    }

    public boolean removeVehicle(String vehicleId) {
        if (vehicleId == null || vehicleId.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID cannot be null or empty");
        }
        return DataStore.getVehicleFleet().remove(vehicleId) != null;
    }

    public void listTransactions() {
        System.out.println("\n=== Agency Transactions ===");
        for (RentalTransaction transaction : DataStore.getTransactions()) {
            System.out.println("Transaction ID: " + transaction.getTransactionId());
            System.out.println("Customer: " + transaction.getCustomerEmail());
            System.out.println("Vehicle: " + transaction.getVin());
            System.out.println("Duration: " + transaction.getRentalDays() + " days");
            System.out.println("Total Cost: $" + transaction.getTotalCost());
            System.out.println("------------------------");
        }
    }
}