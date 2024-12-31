import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AgentTest {
    private Agent.AddAgent agent;
    private DataStore dataStore;

    @BeforeEach
    void setUp() {
        agent = new Agent.AddAgent();
        dataStore = new DataStore();
    }

    @Test
    void testAgentCreation() {
        agent.setAgencyName("Test Agency");
        agent.setFirstName("Jane");
        agent.setLastName("Smith");
        agent.setEmail("agency@test.com");
        agent.setPassword("agency123");
        agent.setAgencyRating("Excellent");

        assertEquals("Test Agency", agent.getAgencyName());
        assertEquals("Jane", agent.getFirstName());
        assertEquals("Smith", agent.getLastName());
        assertEquals("agency@test.com", agent.getEmail());
        assertEquals("agency123", agent.getPassword());
        assertEquals("Excellent", agent.getAgencyRating());
    }

    @Test
    void testAgencyRating() {
        Agent.AgencyRating ratingSystem = new Agent.AgencyRating();
        ratingSystem.updateRating("agency@test.com", "Outstanding");
        String rating = ratingSystem.viewRating("agency@test.com");
        assertNotNull(rating);
    }

    @Test
    void testAgencyReporting() {
        Agent.AgencyReporting reporting = new Agent.AgencyReporting();
        assertDoesNotThrow(() -> {
            reporting.generateDailyReport("agency@test.com");
            reporting.generateMonthlyReport("agency@test.com");
            reporting.viewTransactionHistory("agency@test.com");
        });
    }
}