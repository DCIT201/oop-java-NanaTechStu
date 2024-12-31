public class RentalTransaction {
    private String transactionId;
    private String vehicleVin;
    private String customerEmail;
    private String startDate;
    private String endDate;
    private double rentalAmount;
    private int rentalDays; // Number of days the vehicle was rented
    private Customer customer;
    private Vehicle vehicle;

    // Constructor for RentalTransaction
    public RentalTransaction(String transactionId, String vehicleVin, String customerEmail,
                            String startDate, String endDate, double rentalAmount, 
                            int rentalDays, Customer customer, Vehicle vehicle) {
        this.transactionId = transactionId;
        this.vehicleVin = vehicleVin;
        this.customerEmail = customerEmail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentalAmount = rentalAmount;
        this.rentalDays = rentalDays;
        this.customer = customer;
        this.vehicle = vehicle;
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public String getVin() {
        return vehicleVin;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getTotalCost() {
        return rentalAmount;
    }

    public void setTotalCost(double rentalAmount) {
        this.rentalAmount = rentalAmount;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
