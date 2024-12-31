import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import vehicles.car;
import users.Customer;
import agency.RentalTransactions;

class RentalTransactionsTest {
    private RentalTransactions rentalTransaction;
    private Customer customer;
    private vehicles.car car;

    @BeforeEach
    void setUp() {
        // Creating a customer and vehicle to be used in the rental transaction
        customer = new Customer("CU001", "John Doe");
        car = new car("C001", "Toyota Camry", 50.0);

        // Create a rental transaction for 3 days
        rentalTransaction = new RentalTransactions(customer, car, 3);
    }

    @Test
    void testCalculateTotalCost() {
        // The expected rental cost for 3 days (50 base rate + 15 extra charge)
        double expectedCost = 95.0;

        // Test the total rental cost
        assertEquals(expectedCost, rentalTransaction.calculateTotalCost(), "The rental cost should be correct.");
    }

    @Test
    void testGetCustomer() {
        // Verify that the customer information is correctly retrieved
        assertEquals(customer, rentalTransaction.getCustomer(), "The customer should be correctly retrieved.");
    }

    @Test
    void testGetVehicle() {
        // Verify that the vehicle information is correctly retrieved
        assertEquals(car, rentalTransaction.getVehicle(), "The vehicle should be correctly retrieved.");
    }

    @Test
    void testGetRentalDays() {
        // Verify that the rental days are correctly retrieved
        assertEquals(3, rentalTransaction.getRentalDays(), "The rental days should be correct.");
    }
}
