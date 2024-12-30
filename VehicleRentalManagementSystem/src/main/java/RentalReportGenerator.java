import java.util.List;

public class RentalReportGenerator {
    public void generateReport(List<RentalTransaction> transactions) {
        System.out.println("Rental Report: ");
        for (RentalTransaction transaction : transactions) {
            System.out.println("Customer: " + transaction.getCustomer().getFirstName() +
                    ", Vehicle: " + transaction.getVehicle().getModel() +
                    ", Rental Days: " + transaction.getRentalDays() +
                    ", Total Cost: $" + transaction.getTotalCost());
        }
    }
}