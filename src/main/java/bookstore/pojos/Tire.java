package bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;


public class Tire extends Product {
    private String manufacturer = "";
    private double price = 0.0;
    private double diameter = 0.0;

    public Tire() {
    }

    /**
     * @param manufacturer The tire manufacturer (e.g., Michelin, Goodyear)
     * @param price The tire price
     * @param diameter The tire diameter in inches
     */
    public Tire(String manufacturer, double price, double diameter) {
        super();
        this.manufacturer = manufacturer;
        this.price = price;
        this.diameter = diameter;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    @Override
    public void initialize(Scanner input) {
        System.out.println("Enter Manufacturer:");
        this.manufacturer = getInput(input, "Unknown Manufacturer");

        System.out.println("Enter Price:");
        this.price = getInput(input, 0.0);

        System.out.println("Enter Diameter:");
        this.diameter = getInput(input, 0.0);
    }

    @Override
    public void edit(Scanner input) {
        System.out.println("Edit Manufacturer [" + this.manufacturer + "]:");
        this.manufacturer = getInput(input, this.manufacturer);

        System.out.println("Edit Price [" + this.price + "]:");
        this.price = getInput(input, this.price);

        System.out.println("Edit Diameter [" + this.diameter + "]:");
        this.diameter = getInput(input, this.diameter);
    }

    @Override
    public void sellItem() {
        System.out.println("Selling tire: " + this.manufacturer + " (Diameter: " + this.diameter + "\") at $" + this.price);
    }

    @Override
    public String toString() {
        return "Tire{" +
                "manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", diameter=" + diameter +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tire tire)) return false;
        return Double.compare(tire.price, price) == 0 &&
                Double.compare(tire.diameter, diameter) == 0 &&
                Objects.equals(manufacturer, tire.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, price, diameter);
    }
}
