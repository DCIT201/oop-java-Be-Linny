package agency;

import interfaces.Rentable;
import vehicles.*;
import exceptions.*;
import users.Customer;
import java.util.List;
import java.util.ArrayList;

public class RentalAgency {
    private String agencyName;
    private List<Vehicle> fleet;
    private List<RentalTransactions> transactions;

    public RentalAgency(String agencyName) {
        this.agencyName = agencyName;
        this.fleet = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        if (!(vehicle instanceof Rentable)) {
            throw new IllegalArgumentException("Vehicle must implement Rentable.");
        }
        fleet.add(vehicle);
    }

    public void rentVehicle(Customer customer, Vehicle vehicle, int days) throws VehicleNotAvailableException, InvalidRentalDurationException {
        if (!vehicle.isAvailableForRental()) {
            throw new VehicleNotAvailableException("Vehicle is not available for rental.");
        }
        if (days <= 0) {
            throw new InvalidRentalDurationException("Rental days must be greater than zero.");
        }
        if (vehicle instanceof Rentable) {
            ((Rentable) vehicle).rent(customer, days);
        }

        RentalTransactions transaction = new RentalTransactions(customer, vehicle, days);
        transactions.add(transaction);
        System.out.println("Vehicle rented successfully.");
    }

    public void returnVehicle(Customer customer, Vehicle vehicle) throws VehicleNotAvailableException {
        if (!fleet.contains(vehicle)) {
            throw new VehicleNotAvailableException("Vehicle not found in fleet.");
        }
        if (vehicle instanceof Rentable) {
            ((Rentable) vehicle).returnVehicle();
            System.out.println("Vehicle returned successfully.");
        }
    }

    public void generateCustomerReport(Customer customer) {
        System.out.println("Rental history for customer: " + customer.getName());
        List<String> rentals = customer.getRentalHistory();
        if (rentals.isEmpty()) {
            System.out.println("No rentals found for this customer.");
        } else {
            for (String vehicleId : rentals) {
                System.out.println("Rented vehicle: " + vehicleId);
            }
        }
    }

    public void generateTotalIncomeReport() {
        double totalIncome = 0;
        for (RentalTransactions transaction : transactions) {
            totalIncome += transaction.calculateTotalCost();
        }
        System.out.println("Total income from rentals: $" + totalIncome);
    }
}
