import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class AgentFunctions {
    private static DataStore dataStore = new DataStore();
    private static Scanner sc = new Scanner(System.in);
    private static Login login = new Login();
    private static RentalReportGenerator reportGenerator = new RentalReportGenerator();

    // Method to add a new vehicle to the fleet
    public void addVehicle() {
        System.out.println("\n--- Add New Vehicle ---");
        System.out.println("Select vehicle type:");
        System.out.println("1. Car");
        System.out.println("2. Motorcycle");
        System.out.println("3. Truck");
        
        int choice = sc.nextInt();
        sc.nextLine(); // Clear buffer

        System.out.print("Enter Vehicle ID: ");
        String vehicleId = sc.nextLine();

        System.out.print("Enter Model: ");
        String model = sc.nextLine();

        System.out.print("Enter Base Rental Rate: ");
        double baseRentalRate = sc.nextDouble();
        sc.nextLine(); // Clear buffer

        switch (choice) {
            case 1:
                addCar(vehicleId, model, baseRentalRate);
                break;
            case 2:
                addMotorcycle(vehicleId, model, baseRentalRate);
                break;
            case 3:
                addTruck(vehicleId, model, baseRentalRate);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    }

    private void addCar(String vehicleId, String model, double baseRentalRate) {
        System.out.print("Enter Agency Name: ");
        String agency = sc.nextLine();

        System.out.print("Has Air Conditioning? (true/false): ");
        boolean hasAirConditioning = sc.nextBoolean();
        sc.nextLine(); // Clear buffer

        System.out.print("Enter Rating: ");
        String rating = sc.nextLine();

        System.out.print("Enter Rules: ");
        String rules = sc.nextLine();

        Car newCar = new Car(vehicleId, agency, model, baseRentalRate, hasAirConditioning, rating, rules);
        dataStore.vehicleFleet.put(vehicleId, newCar);
        System.out.println("Car added successfully!");
    }

    private void addMotorcycle(String vehicleId, String model, double baseRentalRate) {
        System.out.print("Helmet Required? (true/false): ");
        boolean isHelmetRequired = sc.nextBoolean();

        System.out.print("Has Sidecar? (true/false): ");
        boolean hasSidecar = sc.nextBoolean();
        sc.nextLine(); // Clear buffer

        System.out.print("Enter Rating: ");
        String rating = sc.nextLine();

        System.out.print("Enter Rules: ");
        String rules = sc.nextLine();

        Motorcycle newMotorcycle = new Motorcycle(vehicleId, model, baseRentalRate, hasSidecar, isHelmetRequired, rating, rules);
        dataStore.vehicleFleet.put(vehicleId, newMotorcycle);
        System.out.println("Motorcycle added successfully!");
    }

    private void addTruck(String vehicleId, String model, double baseRentalRate) {
        System.out.print("Enter Load Capacity (tons): ");
        double loadCapacity = sc.nextDouble();
        sc.nextLine(); // Clear buffer

        System.out.print("Enter Rating: ");
        String rating = sc.nextLine();

        System.out.print("Enter Rules: ");
        String rules = sc.nextLine();

        Truck newTruck = new Truck(vehicleId, model, baseRentalRate, loadCapacity, rating, rules);
        dataStore.vehicleFleet.put(vehicleId, newTruck);
        System.out.println("Truck added successfully!");
    }

    // Method to remove a vehicle from the fleet
    public void removeVehicle() {
        System.out.print("Enter Vehicle ID to remove: ");
        String vehicleId = sc.nextLine();

        if (dataStore.vehicleFleet.remove(vehicleId) != null) {
            System.out.println("Vehicle removed successfully!");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    // Method to view all vehicles in the fleet
    public void viewFleet() {
        System.out.println("\n--- Current Fleet ---");
        for (Map.Entry<String, Vehicle> entry : dataStore.vehicleFleet.entrySet()) {
            Vehicle vehicle = entry.getValue();
            System.out.println("\nVehicle ID: " + vehicle.getVehicleId());
            System.out.println("Model: " + vehicle.getModel());
            System.out.println("Base Rate: $" + vehicle.getBaseRentalRate());
            System.out.println("Available: " + vehicle.isAvailable());

            if (vehicle instanceof Car) {
                Car car = (Car) vehicle;
                System.out.println("Type: Car");
                System.out.println("Agency: " + car.getAgency());
                System.out.println("Air Conditioning: " + car.hasAirConditioning());
            } else if (vehicle instanceof Motorcycle) {
                Motorcycle motorcycle = (Motorcycle) vehicle;
                System.out.println("Type: Motorcycle");
                System.out.println("Helmet Required: " + motorcycle.isHelmetRequired());
                System.out.println("Has Sidecar: " + motorcycle.hasSidecar());
            } else if (vehicle instanceof Truck) {
                Truck truck = (Truck) vehicle;
                System.out.println("Type: Truck");
                System.out.println("Load Capacity: " + truck.getLoadCapacity() + " tons");
            }
        }
    }

    // Method to generate reports
    public void generateReports() {
        System.out.println("\n--- Generate Reports ---");
        System.out.println("1. View All Transactions");
        System.out.println("2. View Available Vehicles");
        System.out.println("3. View Rented Vehicles");
        
        int choice = sc.nextInt();
        sc.nextLine(); // Clear buffer

        switch (choice) {
            case 1:
                reportGenerator.generateReport(new ArrayList<>(DataStore.getTransactions()));
                break;
            case 2:
                viewAvailableVehicles();
                break;
            case 3:
                viewRentedVehicles();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void viewAvailableVehicles() {
        System.out.println("\n--- Available Vehicles ---");
        for (Vehicle vehicle : dataStore.vehicleFleet.values()) {
            if (vehicle.isAvailable()) {
                System.out.println("ID: " + vehicle.getVehicleId() +
                                ", Model: " + vehicle.getModel() +
                                ", Rate: $" + vehicle.getBaseRentalRate());
            }
        }
    }

    private void viewRentedVehicles() {
        System.out.println("\n--- Rented Vehicles ---");
        for (Vehicle vehicle : dataStore.vehicleFleet.values()) {
            if (!vehicle.isAvailable()) {
                System.out.println("ID: " + vehicle.getVehicleId() +
                                ", Model: " + vehicle.getModel() +
                                ", Rate: $" + vehicle.getBaseRentalRate());
            }
        }
    }
}