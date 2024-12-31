import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import vehicles.car;
import vehicles.Motorcycle;
import users.Customer;
import agency.RentalAgency;
import exceptions.VehicleNotAvailableException;
import exceptions.InvalidRentalDurationException;

class RentalAgencyTest {

    private RentalAgency agency;
    private Customer customer;
    private vehicles.car car;
    private Motorcycle motorcycle;

    @BeforeEach
    void setUp() {
        agency = new RentalAgency("City Rental Agency");
        customer = new Customer("CU001", "John Doe");
        car = new car("C001", "Toyota Camry", 50.0);
        motorcycle = new Motorcycle("M001", "Harley Davidson", 30.0);

        // Add vehicles to the agency
        agency.addVehicle(car);
        agency.addVehicle(motorcycle);
    }

    @Test
    void testRentVehicleSuccessfully() {
        try {
            agency.rentVehicle(customer, car, 3);  // Rent car for 3 days
            assertFalse(car.isAvailableForRental());  // Car should no longer be available
        } catch (VehicleNotAvailableException | InvalidRentalDurationException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void testRentVehicleNotAvailable() {
        try {
            agency.rentVehicle(customer, car, 3);  // Rent car for 3 days
            agency.rentVehicle(customer, car, 2);  // Try renting it again
            fail("Expected VehicleNotAvailableException");
        } catch (VehicleNotAvailableException e) {
            assertEquals("Vehicle is not available for rental.", e.getMessage());
        } catch (InvalidRentalDurationException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testRentVehicleWithInvalidDuration() {
        try {
            agency.rentVehicle(customer, car, 0);  // Invalid rental duration (0 days)
            fail("Expected InvalidRentalDurationException");
        } catch (InvalidRentalDurationException e) {
            assertEquals("Rental days must be greater than zero.", e.getMessage());
        } catch (VehicleNotAvailableException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testReturnVehicleSuccessfully() {
        try {
            agency.rentVehicle(customer, car, 3);  // Rent car for 3 days
            agency.returnVehicle(customer, car);   // Return the car
            assertTrue(car.isAvailableForRental());  // Car should now be available again
        } catch (VehicleNotAvailableException | InvalidRentalDurationException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void testReturnVehicleNotRented() {
        try {
            agency.returnVehicle(customer, car);  // Try to return a car that wasn't rented
            fail("Expected VehicleNotAvailableException");
        } catch (VehicleNotAvailableException e) {
            assertEquals("Vehicle not found in fleet.", e.getMessage());
        }
    }

    @Test
    void testGenerateCustomerReport() {
        try {
            agency.rentVehicle(customer, car, 3);  // Rent car for 3 days
            agency.generateCustomerReport(customer);  // Generate report
        } catch (VehicleNotAvailableException | InvalidRentalDurationException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void testGenerateTotalIncomeReport() {
        try {
            agency.rentVehicle(customer, car, 3);  // Rent car for 3 days
            agency.rentVehicle(customer, motorcycle, 2);  // Rent motorcycle for 2 days
            agency.generateTotalIncomeReport();  // Generate total income report
        } catch (VehicleNotAvailableException | InvalidRentalDurationException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }
}
