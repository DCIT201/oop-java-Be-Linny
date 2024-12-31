import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import users.Customer;

class CustomerTest {
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("CU001", "John Doe");
    }

    @Test
    void testEligibilityWithFewerRentalsAndGoodRating() {
        // Adding 5 rentals
        customer.addRental("V001");
        customer.addRental("V002");
        customer.addRental("V003");
        customer.addRental("V004");
        customer.addRental("V005");

        // Giving the customer a good rating (average rating above 3)
        customer.addRating(4.5);
        customer.addRating(5.0);
        customer.addRating(4.0);

        // Customer should be eligible for rental
        assertTrue(customer.isEligibleForRental());
    }

    @Test
    void testEligibilityWithMoreThanMaxRentals() {
        // Adding 10 rentals (max rentals allowed is 10)
        for (int i = 0; i < 10; i++) {
            customer.addRental("V00" + (i + 1));
        }

        // Customer should not be eligible for rental
        assertFalse(customer.isEligibleForRental());
    }

    @Test
    void testEligibilityWithLowAverageRating() {
        // Adding rentals
        customer.addRental("V001");
        customer.addRental("V002");

        // Giving the customer a low average rating (below 3)
        customer.addRating(2.0);
        customer.addRating(2.5);

        // Customer should not be eligible for rental due to low rating
        assertFalse(customer.isEligibleForRental());
    }

    @Test
    void testEligibilityWithHighRatingAndNoRentals() {
        // No rentals, but adding a high rating
        customer.addRating(4.5);
        customer.addRating(4.0);

        // Customer should be eligible for rental (no rental history but good ratings)
        assertTrue(customer.isEligibleForRental());
    }
}
