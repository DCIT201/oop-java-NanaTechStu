import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    private Customer.AddCustomer customer;
    private DataStore dataStore;

    @BeforeEach
    void setUp() {
        customer = new Customer.AddCustomer();
        dataStore = new DataStore();
    }

    @Test
    void testCustomerCreation() {
        customer.setDrivingID("D123");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john@example.com");
        customer.setPassword("password123");
        customer.setUserRating("Good");

        assertEquals("D123", customer.getDrivingID());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("john@example.com", customer.getEmail());
        assertEquals("password123", customer.getPassword());
        assertEquals("Good", customer.getUserRating());
    }

    @Test
    void testCustomerEmailValidation() {
        customer.setEmail("  TEST@EMAIL.COM  ");
        assertEquals("test@email.com", customer.getEmail());
    }

    @Test
    void testCustomerLoyaltyPoints() {
        Customer.CustomerLoyalty loyalty = new Customer.CustomerLoyalty();
        double points = loyalty.calculateLoyaltyPoints("john@example.com");
        assertTrue(points >= 0);
    }
}