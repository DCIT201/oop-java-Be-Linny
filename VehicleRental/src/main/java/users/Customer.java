package users;

import java.util.ArrayList;
import java.util.List;
import interfaces.LoyaltyProgram;

public class Customer implements LoyaltyProgram {
    private final String customerId;
    private final String name;
    private final List<String> rentalHistory; // Keeps track of rentals
    private List<Double> ratings = new ArrayList<>();
    private int loyalPoints;

    public Customer(String customerId, String name) {
        if (customerId == null || customerId.isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty.");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty.");
        }
        this.customerId = customerId;
        this.name = name;
        this.rentalHistory = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void addRental(String vehicleId) {
        rentalHistory.add(vehicleId);
    }

    public List<String> getRentalHistory() {
        return rentalHistory;
    }

    public boolean hasRented(String vehicleId) {
        return rentalHistory.contains(vehicleId);
    }

    public int getLoyaltyPoints() {
        return loyalPoints;
    }

    public void addLoyaltyPoints(int points) {
        loyalPoints += points;
    }

    public void addRating(double rating) {
        if (rating >= 1.0 && rating <= 5.0) {
            ratings.add(rating);
        }
    }

    public double getAverageRating() {
        return ratings.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public boolean isEligibleForRental() {
        // Define maximum rentals allowed
        final int MAX_RENTALS = 10;

        // Eligibility based on rental count
        if (rentalHistory.size() >= MAX_RENTALS) {
            System.out.println("Customer has reached the maximum number of concurrent rentals.");
            return false;
        }

        // Average rating check (if ratings are used for eligibility)
        if (!ratings.isEmpty() && getAverageRating() < 3.0) {
            System.out.println("Customer's average rating is too low for rental.");
            return false;
        }

        // Customer is eligible
        return true;
    }


    @Override
    public String toString() {
        return String.format("Customer[ID=%s, Name=%s, Rentals=%s]", customerId, name, rentalHistory);
    }
}
