public class Motorcycle extends Vehicle {
    private boolean isHelmetRequired;
    private boolean hasSidecar;

    // Constructor updated to match parent class
    public Motorcycle(String vehicleId, String model, double baseRentalRate,
            boolean hasSidecar, boolean isHelmetRequired, String rating, String rules) {
        super(vehicleId, model, baseRentalRate, rating, rules);  // Updated to match parent constructor
        this.isHelmetRequired = isHelmetRequired;
        this.hasSidecar = hasSidecar;
    }

    // Getters and Setters for the fields
    public boolean isHelmetRequired() {
        return isHelmetRequired;
    }

    public boolean hasSidecar() {
        return hasSidecar;
    }

    // Static utility method to retrieve rental rules
    public static String retrieveRentalRules(Vehicle vehicle) {
        if (vehicle instanceof Motorcycle) {
            return vehicle.getRules(); // Now using parent class getRules()
        }
        return "No specific rules available for this type of vehicle.";
    }

    @Override
    public double calculateRentalCost(int days) {
        return getBaseRentalRate() * days;
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }
}