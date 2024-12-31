package vehicles;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public abstract class Vehicle {
    private final String vehicleId;
    private final String model;
    private final double baseRentalRate;
    private boolean isAvailable;
    private final List<Double> ratings = new ArrayList<>();

    public Vehicle (String vehicleId, String model, double baseRentalRate) {
        if (vehicleId == null || vehicleId.isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID cannot be null or empty.");
        }
        if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty.");
        }
        if (baseRentalRate <= 0) {
            throw new IllegalArgumentException("Base rental rate must be greater than zero.");
        }
        this.vehicleId = vehicleId;
        this.model = model;
        this.baseRentalRate = baseRentalRate;
        this.isAvailable = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public double getBaseRentalRate() {
        return baseRentalRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }

    public abstract double calculateRentalCost(int days);
    public abstract boolean isAvailableForRental();

    public void addRating(double rating) {
        if (rating >= 1.0 && rating <= 5.0) {
            ratings.add(rating);
        } else {
            throw new IllegalArgumentException("Rating must be between 1.0 and 5.0");
        }
    }

    public double getAverageRating() {
        return ratings.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public String toString() {
        return String.format("Vehicle ID: %s, Model: %s, Base Rate: %.2f, Avg. Rating: %.2f",
                vehicleId, model, baseRentalRate, getAverageRating());
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehicle vehicle = (Vehicle) obj;
        return vehicleId.equals(vehicle.vehicleId);
    }

    public int hashCode() {
        return Objects.hash(vehicleId);
    }
}
