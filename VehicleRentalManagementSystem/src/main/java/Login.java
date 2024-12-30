// Agenct Menu

import java.util.Scanner;

public class Login {

    private static Scanner sc = new Scanner(System.in);
    private static DataStore dataStore = new DataStore();
    private static CustomerFunctions customerFunctions = new CustomerFunctions();
    // Variable to store logged-in customer's email
    private static String loggedInCustomerEmail = "";

    // Concrete implementation of Customer
    public static class ConcreteCustomer extends Customer {
        public ConcreteCustomer(String drivingID, String firstName, String lastName, String email, String password, String userRating) {
            super(drivingID, firstName, lastName, email, password, userRating);
        }

        @Override
        public void addUser() {
        }

        @Override
        public void showList() {
            while (true) {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Add a New Rental");
                System.out.println("2. Current and Previous Rentals");
                System.out.println("3. Loyalty Status and Rental Exeptions");
                System.out.println("4. Sign Out");
                System.out.print("Choose an option: ");
    
                int choice = sc.nextInt();
                sc.nextLine();
                System.out.println("\n");
    
                switch (choice) {
                    case 1:
                        customerFunctions.addRental();  // Add a New Rental
                        break;
                    case 2:
                        customerFunctions.viewRentals();  // Current and Previous Rentals
                        break;
                    case 3:
                        customerFunctions.viewLoyaltyStatus();  // viewLoyaltyStatus();
                        break;
                    case 4:
                        System.out.println("Goodbye for now!");//edit
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    // Concrete implementation of RentalAgency
    public static class ConcreteCustomer2 extends RentalAgency {
        public ConcreteCustomer2(String agencyName, String firstName, String lastName, String email, String password, String agencyRating) {
            super(agencyName, firstName, lastName, email, password, agencyRating);
        }

        @Override
        public void addUser() {
        }

        @Override
        public void showList() {
        }
    }

    // Static login method
    public static void loginUser() {
        while (true) {
            System.out.print("Enter your email: ");
            String email = sc.nextLine().trim(); // Trim whitespace for consistency

            // Check if the email is in the customer map
            if (dataStore.customerMap.containsKey(email)) {
                handlePasswordLogin(email, true); // true for customer account
                break;
            }
            // Check if the email is in the agency map
            else if (dataStore.agencyMap.containsKey(email)) {
                handlePasswordLogin(email, false); // false for agency account
                break;
            }
            // Email not found
            else {
                System.out.println("Email not found. Options:");
                System.out.println("1. Retry");
                System.out.println("2. Go back to the menu");

                int choiceLogin = getChoiceLogin();
                if (choiceLogin == 2) return;
            }
        }
    }

    private static void handlePasswordLogin(String email, boolean isCustomer) {
        while (true) {
            System.out.print("Enter your password: ");
            String password = sc.nextLine().trim();

            if ((isCustomer && dataStore.customerMap.get(email).equals(password)) ||
                (!isCustomer && dataStore.agencyMap.get(email).equals(password))) {
                System.out.println("Welcome back, " + (isCustomer ? "customer!" : "agency!"));
                loggedInCustomerEmail = email;  // Store the email of the logged-in customer
                if (isCustomer) {
                    new ConcreteCustomer("", "", "", "", "", "").showList(); // Show customer menu
                } else {
                    new ConcreteAgent("", "", "", "", "", "").showList(); // Show agency menu
                }
                return;
            } else {
                System.out.println("Incorrect password. Options:");
                System.out.println("1. Retry password");
                System.out.println("2. Go back to enter email");

                int choiceLogin = getChoiceLogin();
                if (choiceLogin == 2) return;
            }
        }
    }

    private static int getChoiceLogin() {
        while (true) {
            try {
                int choice = sc.nextInt();
                sc.nextLine(); // Consume the newline left by nextInt
                return choice;
            } catch (Exception e) {
                sc.nextLine(); // Clear invalid input
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Method to get the logged-in customer email
    public static String getLoggedInCustomerEmail() {
        return loggedInCustomerEmail;
    }
}