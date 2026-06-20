package bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * Concrete entity representing a Nintendo game title.
 * Extends {@link NintendoProductEntity} and adds the {@code genre} and
 * {@code title} columns that are unique to games inside the single product table.
 */
@Entity
public class NintendoGameEntity extends NintendoProductEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    public NintendoGameEntity() {
    }

    public NintendoGameEntity(String title, double price, int copies, String platform, String genre) {
        super(platform, price, copies);
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // equals()/hashCode() are inherited from NintendoProductEntity and use the
    // productId business key, per the lab's identity-stability requirement.

    @Override
    public String toString() {
        return "NintendoGameEntity{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                "} " + super.toString();
    }
}
