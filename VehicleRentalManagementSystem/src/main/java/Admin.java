// Add agency, uodate numbers, etc

public class Admin {
    public void addVehicle(RentalAgency agency, Vehicle vehicle) {
        agency.addVehicle(vehicle);
        System.out.println("Vehicle added: " + vehicle.getVehicleId());
    }

    public void removeVehicle(RentalAgency agency, String vehicleId) {
        if (agency.removeVehicle(vehicleId)) {
            System.out.println("Vehicle removed: " + vehicleId);
        } else {
            System.out.println("Vehicle not found: " + vehicleId);
        }
    }

    public void generateReport(RentalAgency agency) {
        agency.listTransactions();
    }

    public class DiscountManager extends Admin{
        private double percentageDiscount;
    
        public DiscountManager(double percentageDiscount) {
            this.percentageDiscount = percentageDiscount;
        }
    
        public double applyDiscount(double totalCost) {
            return totalCost - (totalCost * percentageDiscount / 100);
        }
    
        public void setPercentageDiscount(double percentageDiscount) {
            this.percentageDiscount = percentageDiscount;
        }
    
        public double getPercentageDiscount() {
            return percentageDiscount;
        }
    }
    
    public class DiscountManager extends Admin{
        private double percentageDiscount;
    
        public DiscountManager(double percentageDiscount) {
            this.percentageDiscount = percentageDiscount;
        }
    
        public double applyDiscount(double totalCost) {
            return totalCost - (totalCost * percentageDiscount / 100);
        }
    
        public void setPercentageDiscount(double percentageDiscount) {
            this.percentageDiscount = percentageDiscount;
        }
    
        public double getPercentageDiscount() {
            return percentageDiscount;
        }
    }
}
