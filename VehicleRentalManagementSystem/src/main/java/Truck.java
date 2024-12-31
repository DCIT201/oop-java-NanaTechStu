public class Truck extends Vehicle {

    private double loadCapacity;  // Truck-specific feature
    private String rating;
    private String rules;

    // Constructor
    public Truck(String vehicleId, String model, double baseRentalRate, double loadCapacity, String rating, String rules) {
        super(vehicleId, model, baseRentalRate, rating, rules);  // Call parent constructor with vehicleId, model, and baseRentalRate
        this.loadCapacity = loadCapacity;          // Set load capacity
        this.rating = rating;                      // Set rating
        this.rules = rules;                        // Set rules
    }

    // Getters and Setters for the fields
    public double getLoadCapacity() {
        return loadCapacity;
    }

    public String getRating() {
        return rating;
    }

    public String getRules() {
        return rules;
    }

    // Static utility method to retrieve rental rules
    public static String retrieveRentalRules(Vehicle vehicle) {
        if (vehicle instanceof Truck) {
            Truck truck = (Truck) vehicle;
            return truck.getRules();
        }
        return "No specific rules available for this type of vehicle.";
    }

    @Override
    public double calculateRentalCost(int days) {
        return getBaseRentalRate() * days;  // Truck-specific rental cost calculation (can be customized further)
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();  // Return the availability status from the parent class
    }
}
