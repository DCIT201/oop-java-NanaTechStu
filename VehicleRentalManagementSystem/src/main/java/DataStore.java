import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataStore {

    // Maps to store customer and agency data
    public static final Map<String, String> customerMap = new HashMap<>();
    public static final Map<String, String> agencyMap = new HashMap<>();

    // Other data structures for rental management
    private static final Map<String, Integer> loyaltyPoints = new HashMap<>();
    public static final Map<String, Vehicle> vehicleFleet = new HashMap<>();
    private static final ArrayList<RentalTransaction> transactions = new ArrayList<>();
    private static final Map<String, ArrayList<String>> rentalHistory = new HashMap<>();
    private static final Map<String, String> currentRentals = new HashMap<>();
    private static final Map<String, Double> vehicleRatings = new HashMap<>();
    private static final Map<String, ArrayList<Integer>> customerRatings = new HashMap<>();

    // Static initializer block to populate the maps with some sample data
    static {
        // Sample customer data
        insertCustomerSample("customer1@example.com", "password123", 100,
                "rentalHistory1", "currentRental1", "5, 4, 3");

        // Sample vehicle fleet using a concrete subclass of Vehicle
        String vm1 = "VIN57283";
        String vm2 = "VIN57284";
        String vm3 = "VIN57288";
        String agent1 = "BMW";
        String agent2 = "Tesla";
        vehicleFleet.put(vm1, new Car(vm1, agent1, "X5", 3.4, true, "Excellent", "You pay for everything that goes wrong and the request of the next customer"));
        vehicleFleet.put(vm2, new Car(vm2, agent1, "X3", 2.5, false, "Good", "Do not mess up the car"));
        vehicleFleet.put(vm3, new Car(vm3, agent2, "XRP", 5.0, true, "Okay", "Have it back by 5"));
        String vm4 = "TRK001";
        String vm5 = "TRK002";
        String vm6 = "MOTO001";
        vehicleFleet.put(vm4, new Truck(vm4, "FreightMaster 3000", 80.0, 12.5, "Cargo", "Truck rules: No off-road use"));
        vehicleFleet.put(vm5, new Truck(vm5, "HaulPro 5000", 100.0, 15.0, "Delivery", "Truck rules: Maximum load is 15 tons"));
        vehicleFleet.put(vm6, new Motorcycle(vm6, "SportX 250", 20.0, true, true, "V-Twin", "Motorcycle rules: Helmet required"));
    }

    // Static method to insert sample customer data
    private static void insertCustomerSample(String email, String password, int loyaltyPoints,
                                            String rentalHistory, String currentRental,
                                            String ratings) {
        customerMap.put(email, password);
        DataStore.loyaltyPoints.put(email, loyaltyPoints);

        // Assuming rentalHistory and ratings are provided as comma-separated strings
        ArrayList<String> historyList = new ArrayList<>();
        for (String rental : rentalHistory.split(",")) {
            historyList.add(rental.trim());
        }
        DataStore.rentalHistory.put(email, historyList);

        DataStore.currentRentals.put(email, currentRental);
    }

    // Getter methods for the private fields
    public static Map<String, String> getCustomerMap() {
        return customerMap;
    }

    public static Map<String, Vehicle> getVehicleFleet() {
        return vehicleFleet;
    }

    public static Map<String, ArrayList<String>> getRentalHistory() {
        return rentalHistory;
    }

    // Continuation of DataStore.java

    // Getter methods for the private fields (continued)
    public static Map<String, Integer> getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public static ArrayList<RentalTransaction> getTransactions() {
        return transactions;
    }

    public static Map<String, String> getCurrentRentals() {
        return currentRentals;
    }

    public static Map<String, Double> getVehicleRatings() {
        return vehicleRatings;
    }

    public static Map<String, ArrayList<Integer>> getCustomerRatings() {
        return customerRatings;
    }

    // Method to add a transaction
    public static void addTransaction(RentalTransaction transaction) {
        transactions.add(transaction);
    }

    // Method to add a rental transaction to a customer's rental history
    public static void addRentalHistory(String customerEmail, String vehicleVin) {
        ArrayList<String> history = rentalHistory.getOrDefault(customerEmail, new ArrayList<>());
        history.add(vehicleVin);
        rentalHistory.put(customerEmail, history);
    }

    // Method to set current rental for a customer
    public static void setCurrentRental(String customerEmail, String vehicleVin) {
        currentRentals.put(customerEmail, vehicleVin);
    }

    // Method to update vehicle rating
    public static void updateVehicleRating(String vehicleVin, double rating) {
        vehicleRatings.put(vehicleVin, rating);
    }

    // Method to update customer ratings
    public static void updateCustomerRating(String customerEmail, int rating) {
        ArrayList<Integer> ratings = customerRatings.getOrDefault(customerEmail, new ArrayList<>());
        ratings.add(rating);
        customerRatings.put(customerEmail, ratings);
    }

    // Method to update loyalty points after a rental
    public static void updateLoyaltyPoints(String customerEmail, int points) {
        int currentPoints = loyaltyPoints.getOrDefault(customerEmail, 0);
        loyaltyPoints.put(customerEmail, currentPoints + points);
    }

    // Method to check availability of a vehicle
    public static boolean isVehicleAvailable(String vehicleVin) {
        Vehicle vehicle = vehicleFleet.get(vehicleVin);
        return vehicle != null && vehicle.isAvailableForRental();
    }

    // Method to rent a vehicle to a customer
    public static boolean rentVehicle(String customerEmail, String vehicleVin) {
        if (isVehicleAvailable(vehicleVin)) {
            Vehicle vehicle = vehicleFleet.get(vehicleVin);
            vehicle.setAvailable(false);  // Mark the vehicle as rented

            // Update rental history and current rental
            addRentalHistory(customerEmail, vehicleVin);
            setCurrentRental(customerEmail, vehicleVin);

            // Log transaction
            RentalTransaction transaction = new RentalTransaction(
                "TX" + System.currentTimeMillis(),
                vehicleVin,
                customerEmail,
                java.time.LocalDate.now().toString(),
                java.time.LocalDate.now().plusDays(10).toString(),
                vehicle.getBaseRentalRate() * 10,
                10,  // rental days
                new Customer.AddCustomer(), // Create new customer instance
                vehicle  // vehicle object
            );
            addTransaction(transaction);

            return true;
        } else {
            return false;
        }
    }

    // Method to return a vehicle and finalize rental
    public static boolean returnVehicle(String customerEmail, String vehicleVin) {
        Vehicle vehicle = vehicleFleet.get(vehicleVin);
        if (vehicle != null && !vehicle.isAvailableForRental() && currentRentals.containsKey(customerEmail)) {
            vehicle.setAvailable(true);  // Mark the vehicle as available for rental
            currentRentals.remove(customerEmail);  // Clear the current rental for the customer

            // Update loyalty points (example of awarding points)
            updateLoyaltyPoints(customerEmail, 50);  // Awarding 50 points on return

            return true;
        } else {
            return false;
        }
    }

    // Sample method to display a customer's rental history
    public static void viewCustomerRentalHistory(String customerEmail) {
        ArrayList<String> history = rentalHistory.get(customerEmail);
        if (history != null) {
            System.out.println("Rental history for " + customerEmail + ":");
            for (String vehicleVin : history) {
                System.out.println("Vehicle VIN: " + vehicleVin);
            }
        } else {
            System.out.println("No rental history for this customer.");
        }
    }

    // Sample method to view a customer's current rental
    public static void viewCustomerCurrentRental(String customerEmail) {
        String vehicleVin = currentRentals.get(customerEmail);
        if (vehicleVin != null) {
            System.out.println("Current rental for " + customerEmail + ": " + vehicleVin);
        } else {
            System.out.println("No current rental for this customer.");
        }
    }

}


