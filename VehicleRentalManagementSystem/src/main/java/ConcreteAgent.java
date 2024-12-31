public class ConcreteAgent extends RentalAgency {
    public ConcreteAgent(String param1, String param2, String param3, String param4, String param5, String param6) {
        super(param1, param2, param3, param4, param5, param6);
    }

    @Override
    public void addUser() {
        // Implementation
    }

    @Override
    public void showList() {
        // Implementation
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        DataStore.getVehicleFleet().put(vehicle.getVehicleId(), vehicle);
    }

    @Override
    public boolean removeVehicle(String vehicleId) {
        if (vehicleId == null || vehicleId.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID cannot be null or empty");
        }
        return DataStore.getVehicleFleet().remove(vehicleId) != null;
    }

    @Override
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