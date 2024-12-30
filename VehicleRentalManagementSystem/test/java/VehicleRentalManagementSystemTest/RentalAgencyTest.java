package VehicleRentalManagementSystemTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalAgencyTest {

    private RentalAgency rentalAgency;

    @BeforeEach
    void setUp() {
        rentalAgency = new RentalAgency("Test Rentals");
    }

    @Test
    void addVehicleTest() {
        Vehicle car = new Car("V001", "Toyota Corolla", 50, true);
        rentalAgency.addVehicle(car);
        assertNotNull(rentalAgency.getVehicle("V001"), "Vehicle should be added to the fleet");
    }

    @Test
    void rentVehicleTest() {
        Vehicle car = new Car("V001", "Toyota Corolla", 50, true);
        rentalAgency.addVehicle(car);

        Customer customer = new Customer("C001", "Jane", "Doe", "jane.doe@example.com", "123456789");
        rentalAgency.addCustomer(customer);

        rentalAgency.rentVehicle("V001", "C001", 3);

        assertFalse(car.isAvailable(), "Car should not be available after renting");
    }

    @Test
    void returnVehicleTest() {
        Vehicle car = new Car("V001", "Toyota Corolla", 50, true);
        rentalAgency.addVehicle(car);

        rentalAgency.returnVehicle("V001");

        assertTrue(car.isAvailable(), "Car should be available after return");
    }
}
