import java.util.Scanner;

public class ConcreteCustomer extends Customer {

    private static Scanner sc = new Scanner(System.in);
    private static DataStore dataStore = new DataStore(); // Access the static DataStore

    public ConcreteCustomer(String drivingID, String firstName, String lastName, String email, String password, String userRating) {
        super(drivingID, firstName, lastName, email, password, userRating);
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

            // Store the email and password in the dataStore
            dataStore.customerMap.put(email, password);  // Store email and password for login
            
            System.out.println("Your details were added successfully!");

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Please restart the program and try again.");
        }
    }

    @Override
    public void showList() {
        // Your existing code for showing the list...
    }

    // @Override
    // public void calculateLoyaltyPoints() {
    //     // Your existing code for showing the list...
    // }
}