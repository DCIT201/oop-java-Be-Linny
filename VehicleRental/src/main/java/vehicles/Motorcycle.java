package vehicles;

import interfaces.Rentable;
import users.Customer;

public class Motorcycle extends Vehicle implements Rentable{
    private final double additionalChargePerDay;
    private Customer currentRenter;

    public Motorcycle(String vehicleId, String model, double baseRentalRate) {
        super(vehicleId, model, baseRentalRate);
        this.additionalChargePerDay = 10.0;
    }


    public void rent(Customer customer, int days) {
        if (!isAvailable()) {
            throw new IllegalStateException("Motorcycle is not available for rental");
        }
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be positive");
        }
        setIsAvailable(false);
        this.currentRenter = customer;
        System.out.println("Motorcycle rented by " + customer.getName() + " for " + days + " days.");
    }

    public void returnVehicle() {
        if(isAvailable()) {
            throw new IllegalStateException("Motorcycle is currently not rented");
        }
        setIsAvailable(true);
        System.out.println("Motorcycle returned by " + currentRenter.getName());
        this.currentRenter = null;
    }

    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Rental days must be greater than zero.");
        }
        return getBaseRentalRate() + (additionalChargePerDay * days);
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }

    public String toString() {
        return super.toString() + String.format(", Type=Motorcycle, ExtraCharge=%.2f", additionalChargePerDay);
    }
}
