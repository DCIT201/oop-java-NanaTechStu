public class Car extends Vehicle {

    private String agency;
    private boolean hasAirConditioning;
    private String rating;
    private String rules;

    // Constructor
    public Car(String vehicleId, String agency, String model, double baseRentalRate,
            boolean hasAirConditioning, String rating, String rules) {
        super(vehicleId, model, baseRentalRate);  // Call parent constructor with vehicleId, model, and baseRentalRate
        this.agency = agency;                      // Set agency
        this.rating = rating;                      // Set rating
        this.rules = rules;                        // Set rules
        this.hasAirConditioning = hasAirConditioning;  // Set air conditioning status
    }

    // Getters and Setters for the fields
    public String getAgency() {
        return agency;
    }

    public boolean hasAirConditioning() {
        return hasAirConditioning;
    }

    public String getRating() {
        return rating;
    }

    public String getRules() {
        return rules;
    }

    // Static utility method to retrieve rental rules
    public static String retrieveRentalRules(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            return car.getRules();
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
        return isAvailable();  // Return the availability status from the parent class
    }
}
