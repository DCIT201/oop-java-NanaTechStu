// Done for now

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
    }
}
