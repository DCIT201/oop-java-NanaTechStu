public void GenerateReport() {
    System.out.println("Rental Report: ");
    for (RentalTransaction transaction : transactions) {
        System.out.println("Customer: " + transaction.getCustomer().getName() + ", Vehicle: " + transaction.getVehicle().getModel() +
                ", Rental Days: " + transaction.getRentalDays() + ", Total Cost: $" + transaction.getTotalCost());
    }
}
