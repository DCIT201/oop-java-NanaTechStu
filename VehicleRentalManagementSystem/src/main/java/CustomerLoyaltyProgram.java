public class CustomerLoyaltyProgram implements LoyaltyProgram {

    public CustomerLoyaltyProgram() {
        super(); // Calls the constructor of LoyaltyProgramBase
    }

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
            System.out.println("A discount of $" + discount + " has been applied for customer: " +
                    customer.getFirstName() + ". Remaining cost: $" + transaction.getTotalCost());
        } else {
            System.out.println("Insufficient loyalty points for a discount.");
        }
    }
}
