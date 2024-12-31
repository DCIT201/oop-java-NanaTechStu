import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {
    private Admin admin;
    private RentalAgency agency;
    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        admin = new Admin();
        agency = new ConcreteAgent("", "", "", "", "", "");
        vehicle = new Car("TEST123", "TestAgency", "TestModel", 100.0, true, "Good", "Test Rules");
    }

    @Test
    void testAddVehicle() {
        assertDoesNotThrow(() -> admin.addVehicle(agency, vehicle));
    }

    @Test
    void testRemoveVehicle() {
        admin.addVehicle(agency, vehicle);
        assertDoesNotThrow(() -> admin.removeVehicle(agency, vehicle.getVehicleId()));
    }

    @Test
    void testDiscountManager() {
        Admin.DiscountManager discountManager = admin.new DiscountManager(10.0);
        double originalCost = 100.0;
        double discountedCost = discountManager.applyDiscount(originalCost);
        assertEquals(90.0, discountedCost);
    }
}