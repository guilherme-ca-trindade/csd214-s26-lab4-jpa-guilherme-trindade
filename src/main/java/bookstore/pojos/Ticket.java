package bookstore.pojos;

import bookstore.entities.TicketEntity;

import java.util.Scanner;

public class Ticket extends Product {
    public String description = "";
    public double price = 0.0;

    @Override
    public void sellItem() {
        System.out.println("Selling Ticket: " + description + " for " + price);
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void initialize(Scanner input) {
        System.out.println("Enter Description:");
        this.description = getInput(input, "Ticket");

        System.out.println("Enter Price:");
        this.price = getInput(input, 0.0);
    }

    @Override
    public void edit(Scanner input) {
        System.out.println("Edit Description [" + this.description + "]:");
        this.description = getInput(input, this.description);

        System.out.println("Edit Price [" + this.price + "]:");
        this.price = getInput(input, this.price);
    }

    @Override
    public String toString() {
        return "Ticket{desc='" + description + "', price=" + price + "}";
    }

    // --- DTO <-> Entity mapping ---
    public TicketEntity toEntity() {
        TicketEntity entity = new TicketEntity();
        entity.setId(this.getDbId());
        entity.setProductId(this.getProductId());
        entity.setDescription(this.description);
        entity.setPrice(this.price);
        return entity;
    }

    public static Ticket fromEntity(TicketEntity entity) {
        Ticket ticket = new Ticket();
        ticket.description = entity.getDescription();
        ticket.price = entity.getPrice();
        ticket.setDbId(entity.getId());
        ticket.setProductId(entity.getProductId());
        return ticket;
    }
}
