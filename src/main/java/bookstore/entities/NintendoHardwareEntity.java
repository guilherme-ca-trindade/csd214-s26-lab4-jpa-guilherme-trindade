package bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

/**
 * Concrete entity representing Nintendo gaming hardware (consoles, Joy-Cons, etc.).
 * Extends {@link NintendoProductEntity} and adds the {@code name},
 * {@code hardware_type} and {@code color} columns unique to hardware items.
 */
@Entity
public class NintendoHardwareEntity extends NintendoProductEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "hardware_type")
    private String hardwareType;

    @Column(name = "color")
    private String color;

    public NintendoHardwareEntity() {
    }

    public NintendoHardwareEntity(String name, double price, int copies, String platform, String hardwareType, String color) {
        super(platform, price, copies);
        this.name = name;
        this.hardwareType = hardwareType;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NintendoHardwareEntity that = (NintendoHardwareEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(hardwareType, that.hardwareType) &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, hardwareType, color);
    }

    @Override
    public String toString() {
        return "NintendoHardwareEntity{" +
                "name='" + name + '\'' +
                ", hardwareType='" + hardwareType + '\'' +
                ", color='" + color + '\'' +
                "} " + super.toString();
    }
}
