public interface Rentable {
    /**
     * Rents a vehicle to a customer for a specified number of days
     * @param customer The customer renting the vehicle
     * @param days Number of rental days
     * @return The rental transaction ID
     */
    String rent(Customer customer, int days);
    
    /**
     * Process the return of a rented vehicle
     * Updates loyalty points and member status
     * @return boolean indicating if the return was successful
     */
    boolean returnVehicle();

    /**
     * Calculate the rental cost for a given number of days
     * @param days Number of rental days
     * @return The total rental cost
     */
    double calculateRentalCost(int days);

    /**
     * Check if the vehicle is currently available for rental
     * @return boolean indicating availability
     */
    boolean isAvailableForRental();
}