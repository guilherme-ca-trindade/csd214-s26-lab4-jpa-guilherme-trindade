package bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NintendoGameEntity that = (NintendoGameEntity) o;
        return Objects.equals(title, that.title) && Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, genre);
    }

    @Override
    public String toString() {
        return "NintendoGameEntity{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                "} " + super.toString();
    }
}
