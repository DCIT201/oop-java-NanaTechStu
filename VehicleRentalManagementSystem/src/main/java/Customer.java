// Done for now

import java.util.Scanner;

public abstract class Customer extends User {

    private static DataStore dataStore = new DataStore(); // Access the static DataStore
    private static Scanner sc = new Scanner(System.in);

    private String drivingID;
    private String userRating;

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

    // Constructor for Customer
    public Customer(String drivingID, String firstName, String lastName, String email, String password, String userRating) {
        super();
        this.drivingID = drivingID;
        this.userRating = userRating;
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
        
                
                System.out.print("Enter password: ");
                String password = sc.nextLine();
        
                String userRating = "Okay";
        
                // Create a new Customer object with the provided details
                Customer newCustomer = new AddCustomer();
                newCustomer.setDrivingID(drivingID);
                newCustomer.setFirstName(firstName);
                newCustomer.setLastName(lastName);
                newCustomer.setEmail(email);
                newCustomer.setPassword(password);
                newCustomer.setUserRating(userRating);
        
                // Store in HashMap using email as key
                dataStore.customerMap.put(email, newCustomer.toString());
                
                System.out.println("Your details were added successfully!");
        
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
                System.err.println("Please restart the program and try again.");
            }
        }

    public static class AddCustomer extends Customer {

        private static Scanner sc = new Scanner(System.in);

        public AddCustomer() {
            super("", "", "", "", "", "");  // Call to the super constructor for User
        }

        @Override
        public void addUser() {
        }

        @Override
        public void showList() {
        }
    }

    public static class ApplyLoyaltyDiscount extends Customer {

        public ApplyLoyaltyDiscount() {
            super("", "", "", "", "", "");  // Call to the super constructor for User
        }

        @Override
        public void addUser() {
        }

        @Override
        public void showList() {
        }
    }

    public static class CalculateLoyaltyPoints extends Customer {

        public CalculateLoyaltyPoints() {
            super("", "", "", "", "", "");  // Call to the super constructor for User
        }

        @Override
        public void addUser() {
        }

        @Override
        public void showList() {
        }
    }

}
