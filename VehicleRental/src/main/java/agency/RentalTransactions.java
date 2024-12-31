package agency;

import vehicles.Vehicle;
import users.Customer;

public class RentalTransactions {
    private Customer customer;
    private Vehicle vehicle;
    private int rentalDays;

    public RentalTransactions(Customer customer, Vehicle vehicle, int rentalDays) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
    }

    public double calculateTotalCost() {
        return vehicle.calculateRentalCost(rentalDays);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getRentalDays() {
        return rentalDays;
    }
}
