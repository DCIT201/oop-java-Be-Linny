import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import users.Customer;
import vehicles.Car;

class CarTest {

    private vehicles.Car car;
    private Customer customer;

    @BeforeEach
    void setUp() {
        // Create a car with a base rate of 50.0 and an additional charge of 15.0 per day
        car = new Car("V001", "Toyota Corolla", 50.0);
        customer = new Customer("C001", "John Doe");
    }

    @Test
    void testRentalCost() {
        // Test for 3 days
        double expectedCost = 50.0 + (15.0 * 3); // Base rate + 3 days of additional charge
        double actualCost = car.calculateRentalCost(3);
        assertEquals(expectedCost, actualCost, "Rental cost should be calculated correctly.");
    }

    @Test
    void testAvailability() {
        // Test the availability of the car
        assertTrue(car.isAvailableForRental(), "Car should be available initially.");

        // Rent the car
        car.rent(customer, 2); // Rent the car for 2 days
        assertFalse(car.isAvailableForRental(), "Car should not be available after renting.");
    }

    @Test
    void testReturnVehicle() {
        // Rent the car first
        car.rent(customer, 2);
        assertFalse(car.isAvailableForRental(), "Car should not be available after renting.");

        // Return the car
        car.returnVehicle();
        assertTrue(car.isAvailableForRental(), "Car should be available after returning.");
    }

    @Test
    void testRentInvalidDays() {
        // Test for invalid rental days (0 or negative)
        assertThrows(IllegalArgumentException.class, () -> car.rent(customer, 0), "Rental days must be positive.");
        assertThrows(IllegalArgumentException.class, () -> car.rent(customer, -1), "Rental days must be positive.");
    }

    @Test
    void testReturnVehicleWhenNotRented() {
        // Try to return the car without renting it first
        assertThrows(IllegalStateException.class, () -> car.returnVehicle(), "Car should not be returned without renting.");
    }
}
