package VehicleRentalManagementSystemTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void customerDetailsTest() {
        Customer customer = new Customer("C001", "Jane", "Doe", "jane.doe@example.com", "123456789");

        assertEquals("Jane", customer.getFirstName(), "First name should match");
        assertEquals("Doe", customer.getLastName(), "Last name should match");
        assertEquals("jane.doe@example.com", customer.getEmail(), "Email should match");
    }
}
