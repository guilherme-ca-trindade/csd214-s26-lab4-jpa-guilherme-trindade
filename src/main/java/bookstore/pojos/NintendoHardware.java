package bookstore.pojos;

import bookstore.entities.NintendoHardwareEntity;

import java.util.Objects;
import java.util.Scanner;

/**
 * Concrete class representing Nintendo gaming hardware.
 * Extends NintendoProduct and adds the "hardwareType" field unique to hardware items.
 * Follows Java Bean standards:
 * - Private fields with public getters/setters
 * - No-arg constructor
 * - Loaded constructor with proper supper()
 * - toString(), equals(), hashCode()
 */
public class NintendoHardware extends NintendoProduct {
    private String name = "";
    private double price = 0.0;
    private int copies = 0;
    private String hardwareType = "";
    private String color = "";

    public NintendoHardware() {
    }

    /**
     * @param name          The hardware product name
     * @param price         The hardware price
     * @param copies        Number of copies in stock
     * @param platform      The Nintendo platform this hardware supports
     * @param hardwareType  The type of hardware (Console, Joy-Con, Dock, Case, Charger)
     * @param color         The color variant (Red, Blue, White, Black)
     */
    public NintendoHardware(String name, double price, int copies, String platform, String hardwareType, String color) {
        super(platform);
        this.name = name;
        this.price = price;
        this.copies = copies;
        this.hardwareType = hardwareType;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getHardwareType() {
        return hardwareType;
    }

    public void setHardwareType(String hardwareType) {
        this.hardwareType = hardwareType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // --- DTO <-> Entity mapping ---
    public NintendoHardwareEntity toEntity() {
        NintendoHardwareEntity entity = new NintendoHardwareEntity();
        entity.setId(this.getDbId());             // Inherited from Product DTO
        entity.setProductId(this.getProductId()); // Inherited from Product DTO
        entity.setPlatform(this.getPlatform());   // Inherited from NintendoProduct DTO
        entity.setName(this.name);
        entity.setPrice(this.price);
        entity.setCopies(this.copies);
        entity.setHardwareType(this.hardwareType);
        entity.setColor(this.color);
        return entity;
    }

    public static NintendoHardware fromEntity(NintendoHardwareEntity entity) {
        NintendoHardware hardware = new NintendoHardware(
                entity.getName(),
                entity.getPrice(),
                entity.getCopies(),
                entity.getPlatform(),
                entity.getHardwareType(),
                entity.getColor()
        );
        hardware.setDbId(entity.getId());
        hardware.setProductId(entity.getProductId());
        return hardware;
    }

    @Override
    public void initialize(Scanner input) {
        System.out.println("Enter Platform (Switch 2/Switch/Wii U/3DS):");
        this.setPlatform(getInput(input, "Switch"));

        System.out.println("Enter Hardware Name:");
        this.name = getInput(input, "Unknown Hardware");

        System.out.println("Enter Hardware Type (Console/Joy-Con/Dock/Case/Charger):");
        this.hardwareType = getInput(input, "Accessory");

        System.out.println("Enter Color:");
        this.color = getInput(input, "Black");

        System.out.println("Enter Price:");
        this.price = getInput(input, 0.0);

        System.out.println("Enter Copies:");
        this.copies = getInput(input, 0);
    }

    @Override
    public void edit(Scanner input) {
        System.out.println("Edit Platform [" + this.getPlatform() + "]:");
        this.setPlatform(getInput(input, this.getPlatform()));

        System.out.println("Edit Name [" + this.name + "]:");
        this.name = getInput(input, this.name);

        System.out.println("Edit Hardware Type [" + this.hardwareType + "]:");
        this.hardwareType = getInput(input, this.hardwareType);

        System.out.println("Edit Color [" + this.color + "]:");
        this.color = getInput(input, this.color);

        System.out.println("Edit Price [" + this.price + "]:");
        this.price = getInput(input, this.price);

        System.out.println("Edit Copies [" + this.copies + "]:");
        this.copies = getInput(input, this.copies);
    }

    @Override
    public void sellItem() {
        if (this.copies > 0) {
            this.copies--;
            System.out.println("Processing sale for " + this.hardwareType + ": '" + this.name + "' (" + this.color + ") for " + this.getPlatform() + "...");
            System.out.println("Price: $" + this.price + " | Remaining stock: " + this.copies);
        } else {
            System.out.println("⚠️  Out of stock: " + this.name);
        }
    }

    @Override
    public String toString() {
        return "NintendoHardware{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", copies=" + copies +
                ", hardwareType='" + hardwareType + '\'' +
                ", color='" + color + '\'' +
                ", platform='" + getPlatform() + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NintendoHardware that)) return false;
        return Double.compare(that.price, price) == 0 &&
                copies == that.copies &&
                Objects.equals(name, that.name) &&
                Objects.equals(hardwareType, that.hardwareType) &&
                Objects.equals(color, that.color) &&
                Objects.equals(getPlatform(), that.getPlatform());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, copies, hardwareType, color, getPlatform());
    }
}
