public class Motorcycle extends Vehicle {

    private boolean isHelmetRequired;  // Motorcycle-specific feature
    private boolean hasSidecar; // Whether the motorcycle has a sidecar
    private String rating;
    private String rules;

    // Constructor
    public Motorcycle(String vehicleId, String model, double baseRentalRate, boolean hasSidecar, boolean isHelmetRequired, String rating, String rules) {
        super(vehicleId, model, baseRentalRate);  // Call parent constructor with vehicleId, model, and baseRentalRate
        this.isHelmetRequired = isHelmetRequired;  // Set helmet requirement
        this.rating = rating;                      // Set rating
        this.hasSidecar = hasSidecar;             // Set sidecar status
        this.rules = rules;                        // Set rules
    }

    // Getters and Setters for the fields
    public boolean isHelmetRequired() {
        return isHelmetRequired;
    }

    public String getRating() {
        return rating;
    }

    public String getRules() {
        return rules;
    }

    public boolean hasSidecar() {
        return hasSidecar;
    }

    // Static utility method to retrieve rental rules
    public static String retrieveRentalRules(Vehicle vehicle) {
        if (vehicle instanceof Motorcycle) {
            Motorcycle motorcycle = (Motorcycle) vehicle;
            return motorcycle.getRules();
        }
        return "No specific rules available for this type of vehicle.";
    }

    @Override
    public double calculateRentalCost(int days) {
        return getBaseRentalRate() * days;  // Motorcycle-specific rental cost calculation (can be customized further)
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();  // Return the availability status from the parent class
    }
}
