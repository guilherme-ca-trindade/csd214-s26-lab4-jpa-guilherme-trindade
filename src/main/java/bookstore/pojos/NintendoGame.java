package bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;

/**
 * Concrete class representing a Nintendo game title.
 * Extends NintendoProduct and adds the "genre" field unique to games.
 * Follows Java Bean standards:
 * - Private fields with public getters/setters
 * - No-arg constructor
 * - Loaded constructor with proper supper()
 * - toString(), equals(), hashCode()
 */
public class NintendoGame extends NintendoProduct {
    private String title = "";
    private double price = 0.0;
    private int copies = 0;
    private String genre = "";

    public NintendoGame() {
    }

    /**
     * @param title      The game title
     * @param price      The game price
     * @param copies     Number of copies in stock
     * @param platform   The Nintendo platform this game runs on
     * @param genre      The game genre (Action, RPG, Sports)
     */
    public NintendoGame(String title, double price, int copies, String platform, String genre) {
        super(platform);
        this.title = title;
        this.price = price;
        this.copies = copies;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void initialize(Scanner input) {
        System.out.println("Enter Game Title:");
        this.title = getInput(input, "Unknown Title");

        System.out.println("Enter Price:");
        this.price = getInput(input, 0.0);

        System.out.println("Enter Copies:");
        this.copies = getInput(input, 0);

        System.out.println("Enter Platform (Switch 2/ Switch/Wii U/3DS):");
        this.setPlatform(getInput(input, "Unknown Platform"));

        System.out.println("Enter Genre:");
        this.genre = getInput(input, "Unknown Genre");

    }

    @Override
    public void edit(Scanner input) {
        System.out.println("Edit Platform [" + this.getPlatform() + "]:");
        this.setPlatform(getInput(input, this.getPlatform()));

        System.out.println("Edit Title [" + this.title + "]:");
        this.title = getInput(input, this.title);

        System.out.println("Edit Genre [" + this.genre + "]:");
        this.genre = getInput(input, this.genre);

        System.out.println("Edit Price [" + this.price + "]:");
        this.price = getInput(input, this.price);

        System.out.println("Edit Copies [" + this.copies + "]:");
        this.copies = getInput(input, this.copies);
    }

    @Override
    public void sellItem() {
        if (this.copies > 0) {
            this.copies--;
            System.out.println("Processing sale for '" + this.title + "' (" + this.genre + ") on " + this.getPlatform() + "...");
            System.out.println("   Price: $" + this.price + " | Remaining copies: " + this.copies);
        } else {
            System.out.println("Out of stock: " + this.title);
        }
    }

    @Override
    public String toString() {
        return "NintendoGame{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", copies=" + copies +
                ", genre='" + genre + '\'' +
                ", platform='" + getPlatform() + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NintendoGame that)) return false;
        return Double.compare(that.price, price) == 0 &&
                copies == that.copies &&
                Objects.equals(title, that.title) &&
                Objects.equals(genre, that.genre) &&
                Objects.equals(getPlatform(), that.getPlatform());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, copies, genre, getPlatform());
    }
}

