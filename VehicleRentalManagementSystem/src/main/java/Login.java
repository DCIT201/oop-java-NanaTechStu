import java.util.Scanner;

public class Login {
    private static Scanner sc = new Scanner(System.in);
    private static DataStore dataStore = new DataStore();
    private static CustomerFunctions customerFunctions = new CustomerFunctions();
    private static String loggedInCustomerEmail = "";

    public static class ConcreteCustomer extends Customer {
        public ConcreteCustomer(String drivingID, String firstName, String lastName, String email, 
                String password, String userRating) {
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
                        customerFunctions.addRental();
                        break;
                    case 2:
                        customerFunctions.viewRentals();
                        break;
                    case 3:
                        customerFunctions.viewLoyaltyStatus();
                        break;
                    case 4:
                        System.out.println("Goodbye for now!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    // Note: Changed RentalAgency to ConcreteRentalAgency to avoid naming confusion
    // Replace ConcreteCustomer2 in Login.java with this:
    public static class ConcreteRentalAgency extends RentalAgency {
        public ConcreteRentalAgency(String agencyName, String firstName, String lastName, String email, String password, String agencyRating) {
            super(agencyName, firstName, lastName, email, password, agencyRating);
        }

        @Override
        public void addUser() {
            // Implementation for adding users
        }

        @Override
        public void showList() {
            // Implementation for showing list
        }

        @Override
        public void addVehicle(Vehicle vehicle) {
            if (vehicle == null) {
                throw new IllegalArgumentException("Vehicle cannot be null");
            }
            DataStore.getVehicleFleet().put(vehicle.getVehicleId(), vehicle);
        }

        @Override
        public boolean removeVehicle(String vehicleId) {
            if (vehicleId == null || vehicleId.trim().isEmpty()) {
                throw new IllegalArgumentException("Vehicle ID cannot be null or empty");
            }
            return DataStore.getVehicleFleet().remove(vehicleId) != null;
        }

        @Override
        public void listTransactions() {
            System.out.println("\n=== Agency Transactions ===");
            for (RentalTransaction transaction : DataStore.getTransactions()) {
                System.out.println("Transaction ID: " + transaction.getTransactionId());
                System.out.println("Customer: " + transaction.getCustomerEmail());
                System.out.println("Vehicle: " + transaction.getVin());
                System.out.println("Duration: " + transaction.getRentalDays() + " days");
                System.out.println("Total Cost: $" + transaction.getTotalCost());
                System.out.println("------------------------");
            }
        }
    }

    public static void loginUser() {
        while (true) {
            System.out.print("Enter your email: ");
            String email = sc.nextLine().trim();

            if (dataStore.customerMap.containsKey(email)) {
                handlePasswordLogin(email, true);
                break;
            }
            else if (dataStore.agencyMap.containsKey(email)) {
                handlePasswordLogin(email, false);
                break;
            }
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
                loggedInCustomerEmail = email;
                if (isCustomer) {
                    new ConcreteCustomer("", "", "", "", "", "").showList();
                } else {
                    new ConcreteAgent("", "", "", "", "", "").showList();
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
                sc.nextLine();
                return choice;
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static String getLoggedInCustomerEmail() {
        return loggedInCustomerEmail;
    }
}