import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataStore {

    // Maps to store customer and agency data
    public static final Map<String, String> customerMap = new HashMap<>();
    public static final Map<String, String> agencyMap = new HashMap<>();
    public static final Map<String, String> agentMap = new HashMap<>(); // Changed from agentAgency

    // Other data structures for rental management
    private static final Map<String, Integer> loyaltyPoints = new HashMap<>();
    public static final Map<String, Vehicle> vehicleFleet = new HashMap<>();
    private static final ArrayList<RentalTransaction> transactions = new ArrayList<>();
    private static final Map<String, ArrayList<String>> rentalHistory = new HashMap<>();
    private static final Map<String, String> currentRentals = new HashMap<>();
    private static final Map<String, Double> vehicleRatings = new HashMap<>();
    private static final Map<String, ArrayList<Integer>> customerRatings = new HashMap<>();
    private static final Map<String, ArrayList<RentalTransaction>> agentTransactions = new HashMap<>(); // Added for agent transactions

    // Static initializer block to populate the maps with some sample data
    static {
        // Sample customer data
        insertCustomerSample("customer1@example.com", "password123", 100,
                "rentalHistory1", "currentRental1", "5, 4, 3");
        // Sample agent data
        insertAgentSample("agent1@example.com", "agentPassword123", "Agency1",
        "4.5, 3.8, 4.2", "T001, T002, T003", "V001, V002, V003");

        // Sample vehicle fleet using a concrete subclass of Vehicle
        String agent1 = "BMW";
        String agent2 = "Tesla";
        vehicleFleet.put("VIN57283", new Car("VIN57283", agent1, "X5", 3.4, true, "Excellent", "You pay for everything that goes wrong and the request of the next customer"));
        vehicleFleet.put("VIN57284", new Car("VIN57284", agent1, "X3", 2.5, false, "Good", "Do not mess up the car"));
        vehicleFleet.put("VIN57288", new Car("VIN57288", agent2, "XRP", 5.0, true, "Okay", "Have it back by 5"));
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

    // Static method to insert sample agent data
    private static void insertAgentSample(String email, String password, String agency,
            String vehicleRatings, String transactions, String vehicleFleet) {
        agentMap.put(email, password);
        agentMap.put(email, agency); // Using the new map name

        // Store vehicle ratings as Double values
        String[] ratings = vehicleRatings.split(",");
        for (String rating : ratings) {
            DataStore.vehicleRatings.put(email, Double.parseDouble(rating.trim()));
        }

        // Store transactions in the agent's transaction list
        ArrayList<RentalTransaction> transactionList = new ArrayList<>();
        for (String transactionId : transactions.split(",")) {
            // Create a dummy transaction for the sample data
            transactionList.add(new RentalTransaction(
                transactionId.trim(),
                "SAMPLE-VIN",
                "sample@customer.com",
                "2024-01-01",
                "2024-01-10",
                100.0,
                10,
                new Customer.AddCustomer(),
                new Car("SAMPLE-VIN", agency, "Sample Model", 50.0, true, "Good", "Sample Rules")
            ));
        }
        agentTransactions.put(email, transactionList);
    }

    // Rest of the DataStore class methods remain the same...
    
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

    // All other methods remain the same...
}