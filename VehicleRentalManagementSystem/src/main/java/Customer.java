import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public abstract class Customer extends User {

    private static final DataStore dataStore = new DataStore(); // Static DataStore instance
    private static final Scanner sc = new Scanner(System.in);  // Single shared Scanner

    private String drivingID;
    private String userRating;

    // Constructor
    public Customer(String drivingID, String firstName, String lastName, String email, String password, String userRating) {
        super(); // Assuming User has these fields
        this.drivingID = drivingID;
        this.userRating = userRating;
    }

    // Getters
    public String getDrivingID() {
        return drivingID;
    }

    public String getUserRating() {
        return userRating;
    }

    // Setters
    public void setDrivingID(String drivingID) {
        this.drivingID = drivingID;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    // Abstract methods
    @Override
    public abstract void addUser();

    public abstract void showList();

    // Static nested class for adding customers
    public static class AddCustomer extends Customer {

        public AddCustomer() {
            super("", "", "", "", "", "Okay");  // Default values for a new customer
        }

        @Override
        public void addUser() {
            try {
                System.out.print("Enter your driver ID: ");
                String drivingID = sc.nextLine();

                System.out.print("Enter your first name: ");
                String firstName = sc.nextLine();

                System.out.print("Enter your last name: ");
                String lastName = sc.nextLine();

                System.out.print("Enter email: ");
                String email = sc.nextLine().trim().toLowerCase();

                if (dataStore.getCustomerMap().containsKey(email)) {
                    System.out.println("Error: A customer with this email already exists.");
                    return;
                }

                System.out.print("Enter password: ");
                String password = sc.nextLine();

                Customer newCustomer = new AddCustomer();
                newCustomer.setDrivingID(drivingID);
                newCustomer.setFirstName(firstName);
                newCustomer.setLastName(lastName);
                newCustomer.setEmail(email);
                newCustomer.setPassword(password);

                // Save the customer details
                dataStore.customerMap.put(email, newCustomer.toString());
                dataStore.updateLoyaltyPoints(email, 0); // Initialize loyalty points
                System.out.println("Customer added successfully!");

            } catch (Exception e) {
                System.err.println("Error during customer creation: " + e.getMessage());
            }
        }

        @Override
        public void showList() {
            System.out.println("Listing all customers:");
            for (Map.Entry<String, String> entry : dataStore.getCustomerMap().entrySet()) {
                System.out.println("Email: " + entry.getKey() + ", Details: " + entry.getValue());
            }
        }
    }

    // Static nested class for loyalty discount
    public static class ApplyLoyaltyDiscount extends Customer {

        public ApplyLoyaltyDiscount() {
            super("", "", "", "", "", "Okay");
        }

        @Override
        public void addUser() {
            // Not applicable for this subclass
        }

        @Override
        public void showList() {
            // Not applicable for this subclass
        }

        public boolean applyDiscount(String customerEmail, RentalTransaction transaction) {
            Map<String, Integer> loyaltyPoints = DataStore.getLoyaltyPoints();
            int points = loyaltyPoints.getOrDefault(customerEmail, 0);

            if (points >= 100) {
                double discount = transaction.getTotalCost() * 0.10;
                transaction.setTotalCost(transaction.getTotalCost() - discount);
                DataStore.updateLoyaltyPoints(customerEmail, -100);
                System.out.println("Discount applied: $" + discount);
                return true;
            } else {
                System.out.println("Insufficient loyalty points for a discount.");
                return false;
            }
        }
    }

    // Static nested class for calculating loyalty points
    public static class CalculateLoyaltyPoints extends Customer {

        public CalculateLoyaltyPoints() {
            super("", "", "", "", "", "Okay");
        }

        @Override
        public void addUser() {
            // Not applicable for this subclass
        }

        @Override
        public void showList() {
            // Not applicable for this subclass
        }

        public int calculatePoints(String customerEmail) {
            return DataStore.getLoyaltyPoints().getOrDefault(customerEmail, 0);
        }

        public void displayPoints(String customerEmail) {
            int points = calculatePoints(customerEmail);
            System.out.println("Customer " + customerEmail + " has " + points + " loyalty points.");
        }
    }

    // Static nested class for loyalty program
    public static class CustomerLoyalty extends Customer {
    private static final DataStore dataStore = new DataStore();

    public CustomerLoyalty() {
        super("", "", "", "", "", "Okay");
    }

    @Override
    public void addUser() {}

    @Override
    public void showList() {}

    public double calculateLoyaltyPoints(String customerEmail) {
        double points = 0;
        ArrayList<String> rentalHistory = DataStore.getRentalHistory().get(customerEmail);
        
        if (rentalHistory != null) {
            points = rentalHistory.size(); // 1 point per rental
        }
        return points;
    }

    public void applyLoyaltyDiscount(String customerEmail, RentalTransaction transaction) {
        double points = calculateLoyaltyPoints(customerEmail);
        if (points >= 10) {
            double discount = transaction.getTotalCost() * 0.05;
            transaction.setTotalCost(transaction.getTotalCost() - discount);
            System.out.printf("Discount applied: $%.2f%n", discount);
            DataStore.updateLoyaltyPoints(customerEmail, -10);
            }
        }
    }

    public ArrayList<RentalTransaction> getRentalHistory() {
        ArrayList<RentalTransaction> history = new ArrayList<>();
        Map<String, ArrayList<String>> rentals = DataStore.getRentalHistory();
        if (rentals.containsKey(this.getEmail())) {
            for (String rental : rentals.get(this.getEmail())) {
                history.add(new RentalTransaction(
                    "TX" + System.currentTimeMillis(),
                    rental,
                    this.getEmail(),
                    java.time.LocalDate.now().toString(),
                    java.time.LocalDate.now().plusDays(10).toString(),
                    100.0,
                    10,
                    this,
                    DataStore.getVehicleFleet().get(rental)
                ));
            }
        }
        return history;
    }
}




