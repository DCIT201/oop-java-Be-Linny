package app;

import agency.RentalAgency;
import vehicles.car;
import vehicles.Motorcycle;
import vehicles.Truck;
import users.Customer;
import exceptions.CustomerNotEligibleException;
import exceptions.VehicleNotAvailableException;
import exceptions.InvalidRentalDurationException;

public class VehicleRentalApp {

    public static void main(String[] args) {
        // Create some vehicles
        car car1 = new car("C001", "Toyota Camry", 50.0);
        Motorcycle motorcycle1 = new Motorcycle("M001", "Harley Davidson", 30.0);
        Truck truck1 = new Truck("T001", "Ford F-150", 20.0);

        // Create a rental agency
        RentalAgency agency = new RentalAgency("City Rental Agency");

        // Add vehicles to the agency
        agency.addVehicle(car1);
        agency.addVehicle(motorcycle1);
        agency.addVehicle(truck1);

        // Create a customer
        Customer customer1 = new Customer("CU001", "John Doe");

        // Simulate customer renting a vehicle
        try {
            if (customer1.isEligibleForRental()) {
                try {
                    agency.rentVehicle(customer1, car1, 3);  // Rent car1 for 3 days
                    customer1.addRental(car1.getVehicleId());
                } catch (VehicleNotAvailableException | InvalidRentalDurationException e) {
                    System.out.println("Error renting vehicle: " + e.getMessage());
                }
            } else {
                throw new CustomerNotEligibleException("Customer is not eligible for rental.");
            }
        } catch (CustomerNotEligibleException e) {
            System.out.println(e.getMessage());
        }

        // Return the vehicle
        try {
            agency.returnVehicle(customer1, car1);
        } catch (VehicleNotAvailableException e) {
            System.out.println("Error returning vehicle: " + e.getMessage());
        }

        // Generate customer report
        agency.generateCustomerReport(customer1);

        // Generate total income report
        agency.generateTotalIncomeReport();
    }
}
