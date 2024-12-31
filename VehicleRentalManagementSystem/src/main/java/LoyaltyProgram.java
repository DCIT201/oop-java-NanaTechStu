public interface LoyaltyProgram {
    double calculateLoyaltyPoints(Customer customer);
    void applyLoyaltyDiscount(Customer customer, RentalTransaction transaction);
}
