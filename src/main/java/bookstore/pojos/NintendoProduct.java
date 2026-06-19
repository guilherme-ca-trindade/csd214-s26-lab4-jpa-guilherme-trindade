package bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;

/**
 * Abstract base class representing Nintendo gaming products.
 * Extends Product and adds the common "platform" field shared by all Nintendo items.
 * 
 * Implements Java Bean standards:
 * - Private fields with public getters/setters
 * - No-arg constructor
 * - Loaded constructor with super()
 * - toString(), equals(), hashCode()
 */
public abstract class NintendoProduct extends Product {
    private String platform = "";

    public NintendoProduct() {
    }

    /**
     * @param platform The Nintendo platform (Switch, Wii U, 3DS, Game Boy)
     */
    public NintendoProduct(String platform) {
        super(); // Product's constructor
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public abstract void initialize(Scanner input);

    @Override
    public abstract void edit(Scanner input);

    @Override
    public abstract void sellItem();

    @Override
    public abstract double getPrice();

    @Override
    public String toString() {
        return "NintendoProduct{" +
                "platform='" + platform + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NintendoProduct that)) return false;
        return Objects.equals(platform, that.platform) &&
                Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(platform, getProductId());
    }
}
