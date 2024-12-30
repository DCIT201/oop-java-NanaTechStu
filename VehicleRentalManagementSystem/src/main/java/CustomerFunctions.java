import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomerFunctions {

    private static DataStore dataStore = new DataStore(); // Access the static DataStore
    private static Scanner sc = new Scanner(System.in);
    private static Login login = new Login();

    // Add a new rental for a customer
    public void addRental() { // Buy Rental
        if (dataStore.vehicleFleet.isEmpty()) {
            System.out.println("No vehicles available in the fleet.");
            return;
        }
    
        System.out.println("Available Vehicles for Rent:");
    
        // Map index to vehicles
        int index = 1;
        Map<Integer, String> indexToKeyMap = new HashMap<>();
    
        for (Map.Entry<String, Vehicle> entry : dataStore.vehicleFleet.entrySet()) {
            String vehicleKey = entry.getKey();
            Vehicle vehicle = entry.getValue();
    
            // Filter only available vehicles, include Trucks and Motorcycles
            if (vehicle instanceof Car) {
                Car car = (Car) vehicle;
                if (car.isAvailableForRental()) {
                    System.out.println(index + ".  Agency: " + car.getAgency() +
                            ",  Model: " + car.getModel() +
                            ",  Base Rental Rate: " + car.getBaseRentalRate() +
                            ",  Air Conditioning: " + (car.hasAirConditioning() ? "Yes" : "No") +
                            ",  Rating: " + car.getRating());
                    indexToKeyMap.put(index, vehicleKey);
                    index++;
                }
            } else if (vehicle instanceof Truck) {
                Truck truck = (Truck) vehicle;
                if (truck.isAvailableForRental()) {
                    System.out.println(index + ".  Model: " + truck.getModel() +
                            ",  Base Rental Rate: " + truck.getBaseRentalRate() +
                            ",  Load Capacity: " + truck.getLoadCapacity() + " tons" +
                            ",  Rating: " + truck.getRating() +
                            ",  Rules: " + truck.getRules());
                    indexToKeyMap.put(index, vehicleKey);
                    index++;
                }
            } else if (vehicle instanceof Motorcycle) {
                Motorcycle motorcycle = (Motorcycle) vehicle;
                if (motorcycle.isAvailableForRental()) {
                    System.out.println(index + ".  Model: " + motorcycle.getModel() +
                            ",  Base Rental Rate: " + motorcycle.getBaseRentalRate() +
                            ",  Has a Side Car: " + motorcycle.hasSidecar() +
                            ",  Helmet Required: " + (motorcycle.isHelmetRequired() ? "Yes" : "No") +
                            ",  Rating: " + motorcycle.getRating() +
                            ",  Rules: " + motorcycle.getRules());
                    indexToKeyMap.put(index, vehicleKey);
                    index++;
                }
            }
        }
    
        if (indexToKeyMap.isEmpty()) {
            System.out.println("No vehicles are available for rent at the moment.");
            return;
        }
    
        // Allow user to choose a vehicle
        chooseRental(indexToKeyMap);
    }
    

    private void chooseRental(Map<Integer, String> indexToKeyMap) {
        System.out.print("Enter the vehicle you want to rent (you can only pick one rental at a time): ");
        int choice = sc.nextInt();
    
        // Validate the choice
        if (indexToKeyMap.containsKey(choice)) {
            String vehicleKey = indexToKeyMap.get(choice); // Retrieve the key (e.g., "VIN57284")
            Vehicle chosenVehicle = dataStore.vehicleFleet.get(vehicleKey);
    
            // Check which vehicle type it is
            if (chosenVehicle instanceof Car) {
                Car chosenCar = (Car) chosenVehicle;
                System.out.println("\n--- Rules for this Rental, MUST READ!---");
                System.out.println("\n" + chosenCar.getRules()); // rules function
                processRental(chosenVehicle);
            } else if (chosenVehicle instanceof Truck) {
                Truck chosenTruck = (Truck) chosenVehicle;
                System.out.println("\n--- Rules for this Rental, MUST READ!---");
                System.out.println("\n" + chosenTruck.getRules()); // truck-specific rules
                processRental(chosenVehicle);
            } else if (chosenVehicle instanceof Motorcycle) {
                Motorcycle chosenMotorcycle = (Motorcycle) chosenVehicle;
                System.out.println("\n--- Rules for this Rental, MUST READ!---");
                System.out.println("\n" + chosenMotorcycle.getRules()); // motorcycle-specific rules
                processRental(chosenVehicle);
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
    
    private void processRental(Vehicle chosenVehicle) {
        System.out.println("\nDo you agree to these rules?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int select = sc.nextInt();
        sc.nextLine();
    
        switch (select) {
            case 1:
                // Mark the vehicle as rented
                chosenVehicle.setAvailable(false);
    
                // Update current rental
                String customerEmail = login.getLoggedInCustomerEmail();
                dataStore.getCurrentRentals().put(customerEmail, chosenVehicle.getVehicleId());
    
                // Update rental history
                dataStore.getRentalHistory()
                        .computeIfAbsent(customerEmail, k -> new ArrayList<>())
                        .add(chosenVehicle.getVehicleId());
    
                // Display a success message with the key
                System.out.println("You have successfully rented: " + chosenVehicle.getVehicleId() +
                        " (Model: " + chosenVehicle.getModel() + ",  Base Rental Rate: " + chosenVehicle.getBaseRentalRate() + ")");
                break;
            case 2:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }
    }
    

    public void viewRentals() {
        String customerId = login.getLoggedInCustomerEmail();
        System.out.println("--- Rental History ---");
    
        // Display current rental if available
        String currentRentalId = dataStore.getCurrentRentals().getOrDefault(customerId, null);
        if (currentRentalId != null) {
            Vehicle currentVehicle = dataStore.getVehicleFleet().get(currentRentalId);
            System.out.println("\nCurrent Rental: ");
            
            // Check if vehicle and then call all currentCar Values
            if (currentVehicle instanceof Car) {
                Car currentCar = (Car) currentVehicle;
                System.out.println("Agency: " + currentCar.getAgency());
                System.out.println("Model: " + currentVehicle.getModel());
                System.out.println("Air Conditioning: " + (currentCar.hasAirConditioning() ? "Yes" : "No"));
                System.out.println("Base Rental Rate: " + currentVehicle.getBaseRentalRate());
            } else if (currentVehicle instanceof Truck) {
                Truck currentTruck = (Truck) currentVehicle;
                System.out.println("Model: " + currentVehicle.getModel());
                System.out.println("Load Capacity: " + currentTruck.getLoadCapacity() + " tons");
                System.out.println("Base Rental Rate: " + currentVehicle.getBaseRentalRate());
                System.out.println("Rating: " + currentTruck.getRating());
                System.out.println("Rules: " + currentTruck.getRules());
            } else if (currentVehicle instanceof Motorcycle) {
                Motorcycle currentMotorcycle = (Motorcycle) currentVehicle;
                System.out.println("Model: " + currentVehicle.getModel());
                System.out.println("Helmet Required: " + (currentMotorcycle.isHelmetRequired() ? "Yes" : "No"));
                System.out.println("Sidecar: " + (currentMotorcycle.hasSidecar() ? "Yes" : "No"));
                System.out.println("Base Rental Rate: " + currentVehicle.getBaseRentalRate());
                System.out.println("Rating: " + currentMotorcycle.getRating());
                System.out.println("Rules: " + currentMotorcycle.getRules());
            }
            
        } else {
            System.out.println("\nCurrent Rental: No active rental.");
        }
    
        // Display previous rentals if available
        ArrayList<String> history = dataStore.getRentalHistory().getOrDefault(customerId, new ArrayList<>());
        if (history.isEmpty()) {
            System.out.println("No previous rentals found for customer.");
        } else {
            System.out.println("\nPrevious Rentals:");
            for (String rentalId : history) {
                Vehicle rentalVehicle = dataStore.getVehicleFleet().get(rentalId);
                System.out.println("- ");
                
                // Check if the rental vehicle is a Car and then call getAgency()
                if (rentalVehicle instanceof Car) {
                    Car rentalCar = (Car) rentalVehicle;
                    System.out.println("Agency: " + rentalCar.getAgency());
                    System.out.println("Model: " + rentalCar.getModel());
                    System.out.println("Air Conditioning: " + (rentalCar.hasAirConditioning() ? "Yes" : "No"));
                    System.out.println("Base Rental Rate: " + rentalCar.getBaseRentalRate());
                    System.out.println("Rating: " + rentalCar.getRating());
                } else if (rentalVehicle instanceof Truck) {
                    Truck rentalTruck = (Truck) rentalVehicle;
                    System.out.println("Model: " + rentalTruck.getModel());
                    System.out.println("Load Capacity: " + rentalTruck.getLoadCapacity() + " tons");
                    System.out.println("Base Rental Rate: " + rentalTruck.getBaseRentalRate());
                    System.out.println("Rating: " + rentalTruck.getRating());
                    System.out.println("Rules: " + rentalTruck.getRules());
                } else if (rentalVehicle instanceof Motorcycle) {
                    Motorcycle rentalMotorcycle = (Motorcycle) rentalVehicle;
                    System.out.println("Model: " + rentalMotorcycle.getModel());
                    System.out.println("Helmet Required: " + (rentalMotorcycle.isHelmetRequired() ? "Yes" : "No"));
                    System.out.println("Sidecar: " + (rentalMotorcycle.hasSidecar() ? "Yes" : "No"));
                    System.out.println("Base Rental Rate: " + rentalMotorcycle.getBaseRentalRate());
                    System.out.println("Rating: " + rentalMotorcycle.getRating());
                    System.out.println("Rules: " + rentalMotorcycle.getRules());
                }
                
            }
        }
    }
    

    public void viewRenta() {
        String customerEmail = login.getLoggedInCustomerEmail();
        System.out.println("--- Rental History ---");
    
        // Display current rental if available
        
        String currentRentalId = dataStore.getCurrentRentals().getOrDefault(customerEmail, null);
        if (currentRentalId != null) {
            Vehicle currentVehicle = dataStore.getVehicleFleet().get(currentRentalId);
            if (currentVehicle != null) {
                System.out.println("\nCurrent Rental: ");
                // System.out.println("Agency: " + currentVehicle.getAgency());
                System.out.println("Model: " + currentVehicle.getModel());
                System.out.println("Base Rental Rate: " + currentVehicle.getBaseRentalRate());
            }
        } else {
            System.out.println("\nCurrent Rental: No active rental.");
        }
    
        // Display previous rentals if available
        ArrayList<String> history = dataStore.getRentalHistory().getOrDefault(customerEmail, new ArrayList<>());
        if (history.isEmpty()) {
            System.out.println("No previous rentals found for customer.");
        } else {
            System.out.println("\nPrevious Rentals:");
            for (String rentalId : history) {
                Vehicle rentalVehicle = dataStore.getVehicleFleet().get(rentalId);
                if (rentalVehicle != null) {
                    // System.out.println("  Agency: " + rentalVehicle.getAgency());
                    System.out.println("  Model: " + rentalVehicle.getModel());
                    System.out.println("  Base Rental Rate: " + rentalVehicle.getBaseRentalRate());
                    System.out.println("  -------------------------------");
                }
            }
        }
    }

    // Rate a rental vehicle
    public void rateRental(String vehicleId, int rating) {
        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5.");
            return;
        }

        dataStore.getVehicleRatings().put(vehicleId, (dataStore.getVehicleRatings().getOrDefault(vehicleId, 0.0) + rating) / 2);
        System.out.println("Thank you for rating. Updated average rating: " + dataStore.getVehicleRatings().get(vehicleId));
    }

    // View loyalty status for a customer
    public void viewLoyaltyStatus(String customerId) {
        int points = dataStore.getLoyaltyPoints().getOrDefault(customerId, 0);
        System.out.println("Loyalty Points: " + points);
        if (points >= 100) {
            System.out.println("Status: Gold Member");
        } else if (points >= 50) {
            System.out.println("Status: Silver Member");
        } else {
            System.out.println("Status: Bronze Member");
        }
    }

    // Custom exception for rental scenarios
    public static class RentalException extends Exception {
        public RentalException(String message) {
            super(message);
        }
    }

    // Interface for loyalty program
    public interface LoyaltyProgram {
        void applyDiscount(String customerId);
    }

    // Implementation of loyalty program
    public class LoyaltyProgramImpl implements LoyaltyProgram {
        @Override
        public void applyDiscount(String customerId) {
            int points = dataStore.getLoyaltyPoints().getOrDefault(customerId, 0);
            if (points >= 100) {
                System.out.println("Gold Member Discount Applied!");
            } else if (points >= 50) {
                System.out.println("Silver Member Discount Applied!");
            } else {
                System.out.println("No discount available. Earn more loyalty points!");
            }
        }
    }
}
