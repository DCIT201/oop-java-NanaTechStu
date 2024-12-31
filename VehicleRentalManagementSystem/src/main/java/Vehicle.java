import java.util.Map;

public abstract class Vehicle {

    private String vehicleId;
    private String model;
    private double baseRentalRate;
    private boolean isAvailable;
    private String rating;
    private String rules;
    private DataStore dataStore = new DataStore(); // Assuming DataStore is defined elsewhere

    // Constructor
    public Vehicle(String vehicleId, String model, double baseRentalRate, String rating, String rules) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.baseRentalRate = baseRentalRate;
        this.isAvailable = true; // Default to available
        this.rating = rating;
        this.rules = rules;
    }

    // Getters and setters
    public String getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }

    public double getBaseRentalRate() {
        return baseRentalRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getRating() {
        return rating;
    }

    public String getRules() {
        return rules;
    }

    // Rent the vehicle
    public String rent(Customer customer, int days) {
        if (!isAvailableForRental()) {
            throw new IllegalStateException("Vehicle is not available for rent");
        }

        double rentalCost = calculateRentalCost(days);
        String transactionId = "TX" + System.currentTimeMillis();

        RentalTransaction transaction = new RentalTransaction(
                transactionId,
                this.vehicleId,
                customer.getEmail(),
                java.time.LocalDate.now().toString(),
                java.time.LocalDate.now().plusDays(days).toString(),
                rentalCost,
                days,
                customer,
                this
        );

        this.isAvailable = false;
        dataStore.addTransaction(transaction);
        dataStore.setCurrentRental(customer.getEmail(), this.vehicleId);
        dataStore.addRentalHistory(customer.getEmail(), this.vehicleId);

        return transactionId;
    }

    // Return the vehicle
    public boolean returnVehicle() {
        if (this.isAvailable) {
            return false; // Vehicle is already available
        }

        this.isAvailable = true;

        String customerId = dataStore.getCurrentRentals()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(this.vehicleId))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        if (customerId != null) {
            dataStore.updateLoyaltyPoints(customerId, 50);
            dataStore.getCurrentRentals().remove(customerId);

            int points = dataStore.getLoyaltyPoints().getOrDefault(customerId, 0);
            System.out.println("Loyalty Points: " + points);
            if (points >= 100) {
                System.out.println("Status: Gold Member");
            } else if (points >= 50) {
                System.out.println("Status: Silver Member");
            } else {
                System.out.println("Status: Bronze Member");
            }
        }

        return true;
    }

    // Abstract methods
    public abstract double calculateRentalCost(int days);

    public abstract boolean isAvailableForRental();
}
