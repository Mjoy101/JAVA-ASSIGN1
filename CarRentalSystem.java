import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Car class representing a car entity
class Car {
    private String licensePlate;
    private String model;
    private boolean isRented;

    public Car(String licensePlate, String model) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.isRented = false;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }

    public boolean isRented() {
        return isRented;
    }

    public void rentCar() {
        isRented = true;
    }

    public void returnCar() {
        isRented = false;
    }

    @Override
    public String toString() {
        return "Car[Model: " + model + ", License: " + licensePlate + ", Rented: " + isRented + "]";
    }
}

// Customer class representing a customer entity
class Customer {
    private String name;
    private String customerId;

    public Customer(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }
}

// RentalAgency class handling car rentals
class RentalAgency {
    private List<Car> cars;
    private List<String> transactions;

    public RentalAgency() {
        cars = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void rentCar(String licensePlate, Customer customer) {
        for (Car car : cars) {
            if (car.getLicensePlate().equals(licensePlate) && !car.isRented()) {
                car.rentCar();
                transactions.add("Customer " + customer.getName() + " rented " + car.getModel());
                System.out.println("Car rented successfully.");
                return;
            }
        }
        System.out.println("Car is not available.");
    }

    public void returnCar(String licensePlate) {
        for (Car car : cars) {
            if (car.getLicensePlate().equals(licensePlate) && car.isRented()) {
                car.returnCar();
                System.out.println("Car returned successfully.");
                return;
            }
        }
        System.out.println("Car not found or already returned.");
    }

    public void displayAvailableCars() {
        for (Car car : cars) {
            if (!car.isRented()) {
                System.out.println(car);
            }
        }
    }
}

// Main class to run the system
public class CarRentalSystem {
    public static void main(String[] args) {
        RentalAgency agency = new RentalAgency();
        agency.addCar(new Car("ABC123", "Toyota Corolla"));
        agency.addCar(new Car("XYZ789", "Honda Civic"));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = new Customer(name, customerId);

        System.out.println("Available cars:");
        agency.displayAvailableCars();

        System.out.print("Enter license plate of car to rent: ");
        String license = scanner.nextLine();
        agency.rentCar(license, customer);

        System.out.println("Available cars after rental:");
        agency.displayAvailableCars();

        System.out.print("Enter license plate of car to return: ");
        String returnLicense = scanner.nextLine();
        agency.returnCar(returnLicense);

        System.out.println("Available cars after return:");
        agency.displayAvailableCars();

        scanner.close();
    }
}
