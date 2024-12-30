public class RentalTransaction {
    private String transactionId;
    private String vehicleVin;
    private String customerEmail;
    private String startDate;
    private String endDate;
    private double rentalAmount;

    // Constructor for RentalTransaction
    public RentalTransaction(String transactionId, String vehicleVin, String customerEmail,
                            String startDate, String endDate, double rentalAmount) {
        this.transactionId = transactionId;
        this.vehicleVin = vehicleVin;
        this.customerEmail = customerEmail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentalAmount = rentalAmount;
    }

    // Getters and Setters (Optional)
    public String getTransactionId() {
        return transactionId;
    }

    public String getVin() {
        return vehicleVin;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    // Other getters and setters for the fields
    // ...
}
