public class CustomerLoyaltyProgram implements LoyaltyProgram {

    @Override
    public double calculateLoyaltyPoints(Customer customer) {
        // Simple example: 1 point per rental day
        double points = 0;
        for (RentalTransaction transaction : customer.getRentalHistory()) {
            points += transaction.getRentalDays();
        }
        return points;
    }

    @Override
    public void applyLoyaltyDiscount(Customer customer, RentalTransaction transaction) {
        double points = calculateLoyaltyPoints(customer);
        if (points >= 10) {  // For example, every 10 points equals a 5% discount
            double discount = transaction.getTotalCost() * 0.05;
            transaction.setTotalCost(transaction.getTotalCost() - discount);
        }
    }
}
