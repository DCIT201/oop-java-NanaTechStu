public class Car extends Vehicle {
    private String agency;
    private boolean hasAirConditioning;

    // Constructor updated to match parent class
    public Car(String vehicleId, String agency, String model, double baseRentalRate,
            boolean hasAirConditioning, String rating, String rules) {
        super(vehicleId, model, baseRentalRate, rating, rules);  // Updated to match parent constructor
        this.agency = agency;
        this.hasAirConditioning = hasAirConditioning;
    }

    // Getters and Setters for the fields
    public String getAgency() {
        return agency;
    }

    public boolean hasAirConditioning() {
        return hasAirConditioning;
    }

    // Static utility method to retrieve rental rules
    public static String retrieveRentalRules(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            return vehicle.getRules(); // Now using parent class getRules()
        }
        return "No specific rules available for this type of vehicle.";
    }

    @Override
    public double calculateRentalCost(int days) {
        double cost = getBaseRentalRate() * days;
        if (hasAirConditioning) {
            cost += 50; // surcharge for air conditioning
        }
        return cost;
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }
}