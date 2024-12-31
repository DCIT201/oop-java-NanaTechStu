import java.util.Scanner;

public abstract class Agent extends User {
    
    private static Scanner sc = new Scanner(System.in);
    private static DataStore dataStore = new DataStore();

    private String agencyName;
    private String agencyRating;

    // Constructor
    public Agent(String agencyName, String firstName, String lastName, String email, String password, String agencyRating) {
        super();
        this.agencyName = agencyName;
        this.agencyRating = agencyRating;
    }

    // Getters and Setters
    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyRating() {
        return agencyRating;
    }

    public void setAgencyRating(String agencyRating) {
        this.agencyRating = agencyRating;
    }

    @Override
    public abstract void addUser();

    @Override
    public abstract void showList();

    // Static nested class for adding agents
    public static class AddAgent extends Agent {
        public AddAgent() {
            super("", "", "", "", "", "Okay");  // Default values for new agent
        }

        @Override
        public void addUser() {
            try {
                System.out.print("Enter your agency name: ");
                String agencyName = sc.nextLine();

                System.out.print("Enter your first name: ");
                String firstName = sc.nextLine();

                System.out.print("Enter your last name: ");
                String lastName = sc.nextLine();

                System.out.print("Enter email: ");
                String email = sc.nextLine().trim().toLowerCase();

                // Check if email already exists
                if (dataStore.agencyMap.containsKey(email)) {
                    System.out.println("Error: An agency with this email already exists.");
                    return;
                }

                System.out.print("Enter password: ");
                String password = sc.nextLine();

                String agencyRating = "Okay";  // Default rating for new agencies

                // Create a new Agent object
                Agent newAgent = new AddAgent();
                newAgent.setAgencyName(agencyName);
                newAgent.setFirstName(firstName);
                newAgent.setLastName(lastName);
                newAgent.setEmail(email);
                newAgent.setPassword(password);
                newAgent.setAgencyRating(agencyRating);

                // Store the email and password in the dataStore
                dataStore.agencyMap.put(email, password);
                System.out.println("Your agency account was created successfully!");

            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
                System.err.println("Please restart the program and try again.");
            }
        }

        @Override
        public void showList() {
            while (true) {
                System.out.println("\n--- Agency Menu ---");
                System.out.println("1. Add New Vehicle");
                System.out.println("2. Remove Vehicle");
                System.out.println("3. View Fleet");
                System.out.println("4. Generate Reports");
                System.out.println("5. Sign Out");
                System.out.print("Choose an option: ");

                int choice = sc.nextInt();
                sc.nextLine();
                System.out.println("\n");
                switch (choice) {
                    case 1:
                        new AgentFunctions().addVehicle();
                        break;
                    case 2:
                        new AgentFunctions().removeVehicle();
                        break;
                    case 3:
                        new AgentFunctions().viewFleet();
                        break;
                    case 4:
                        new AgentFunctions().generateReports();
                        break;
                    case 5:
                        System.out.println("Signed out successfully!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    // Static nested class for agency ratings
    public static class AgencyRating extends Agent {
        public AgencyRating() {
            super("", "", "", "", "", "");
        }

        @Override
        public void addUser() {
            // Not applicable for this subclass
        }

        @Override
        public void showList() {
            // Not applicable for this subclass
        }

        public void updateRating(String agencyEmail, String newRating) {
            Agent agent = new AddAgent(); // Create temporary agent to update rating
            agent.setEmail(agencyEmail);
            agent.setAgencyRating(newRating);
            System.out.println("Agency rating updated successfully!");
        }

        public String viewRating(String agencyEmail) {
            // In a real implementation, this would fetch from a database
            return "Current rating not available";
        }
    }

    // Static nested class for agency reports
    public static class AgencyReporting extends Agent {
        public AgencyReporting() {
            super("", "", "", "", "", "");
        }

        @Override
        public void addUser() {
            // Not applicable for this subclass
        }

        @Override
        public void showList() {
            // Not applicable for this subclass
        }

        public void generateDailyReport(String agencyEmail) {
            System.out.println("Daily Report for " + agencyEmail);
            // Implementation for generating daily reports
        }

        public void generateMonthlyReport(String agencyEmail) {
            System.out.println("Monthly Report for " + agencyEmail);
            // Implementation for generating monthly reports
        }

        public void viewTransactionHistory(String agencyEmail) {
            System.out.println("Transaction History for " + agencyEmail);
            // Implementation for viewing transaction history
        }
    }
}