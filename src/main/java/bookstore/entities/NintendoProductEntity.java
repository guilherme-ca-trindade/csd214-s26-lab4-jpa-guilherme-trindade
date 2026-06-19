package bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

/**
 * Abstract-style base entity for the Nintendo niche.
 * <p>
 * Mirrors the {@code NintendoProduct} DTO and sits inside the SINGLE_TABLE
 * inheritance hierarchy rooted at {@link ProductEntity}. The fields shared by
 * every Nintendo item (platform, price, copies) live here so that the concrete
 * {@link NintendoGameEntity} and {@link NintendoHardwareEntity} subclasses only
 * declare the columns unique to them.
 */
@Entity
public class NintendoProductEntity extends ProductEntity {
    @Column(name = "platform")
    private String platform;

    @Column(name = "price")
    private double price;

    @Column(name = "copies")
    private int copies;

    public NintendoProductEntity() {
    }

    public NintendoProductEntity(String platform, double price, int copies) {
        this.platform = platform;
        this.price = price;
        this.copies = copies;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NintendoProductEntity that = (NintendoProductEntity) o;
        return Double.compare(price, that.price) == 0 &&
                copies == that.copies &&
                Objects.equals(platform, that.platform);
    }

    @Override
    public int hashCode() {
        return Objects.hash(platform, price, copies);
    }

    @Override
    public String toString() {
        return "NintendoProductEntity{" +
                "platform='" + platform + '\'' +
                ", price=" + price +
                ", copies=" + copies +
                "} " + super.toString();
    }
}
