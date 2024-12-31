package interfaces;

import users.Customer;

public interface Rentable {
    void rent(Customer customer, int days);
    void returnVehicle();
}
