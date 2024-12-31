import java.util.Scanner;

public abstract class RentalAgency extends User {

    private String agencyName;
    private String agencyRating;

    // Getters
    public String getAgencyName() {
        return agencyName;
    }
    public String getAgencyRating() {
        return agencyRating;
    }

    // Setters
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    public void setAgencyRating(String agencyRating) {
        this.agencyRating = agencyRating;
    }

    // Constructor for Customer
    public RentalAgency(String agencyName, String firstName, String lastName, String email, String password, String agencyRating) {
        super();
        this.agencyName = agencyName;
        this.agencyRating = agencyRating;
    }

    // Abstract methods for Admin operations
    public abstract void addVehicle(Vehicle vehicle);
    public abstract boolean removeVehicle(String vehicleId);
    public abstract void listTransactions();

    public static class AddAgent extends RentalAgency {

        private static Scanner sc = new Scanner(System.in);

        public AddAgent() {
            super("", "", "", "", "", "");  // Call to the super constructor for User
        }

        @Override
        public void addUser() {
        }

        @Override
        public void showList() {
        }

        @Override
        public void listTransactions() {
        }

        @Override
        public boolean removeVehicle(String vehicleId) {
            return false;
        }

        @Override
        public void addVehicle(Vehicle vehicle) {
        }
    }
}