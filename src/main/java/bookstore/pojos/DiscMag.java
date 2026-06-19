package bookstore.pojos;

import bookstore.entities.DiscMagEntity;

import java.util.Date;
import java.util.Scanner;

public class DiscMag extends Magazine {
    private boolean hasDisc;

    public DiscMag() {
    }

    public DiscMag(boolean hasDisc, int orderQty, Date currentIssue, String title, double price, int copies) {
        super(orderQty, currentIssue, title, price, copies);
        this.hasDisc = hasDisc;
    }

    public void initialize(Scanner input) {
        // Pass scanner up to parent
        super.initialize(input);

        System.out.println("Has Disc? (true/false):");
        this.hasDisc = getInput(input, false);
    }

    @Override
    public void edit(Scanner input) {
        super.edit(input); // Title, Price, Copies, OrderQty, Date

        System.out.println("Edit Has Disc [" + this.hasDisc + "]:");
        this.hasDisc = getInput(input, this.hasDisc);
    }

    @Override
    public void sellItem() {
        System.out.println("Selling Disc Magazine (Disc: " + hasDisc + ")");
        setCopies(getCopies() - 1);
    }

    public boolean isHasDisc() { return hasDisc; }
    public void setHasDisc(boolean h) { this.hasDisc = h; }

    // --- DTO <-> Entity mapping ---
    public DiscMagEntity toEntity() {
        DiscMagEntity entity = new DiscMagEntity();
        entity.setId(this.getDbId());
        entity.setProductId(this.getProductId());
        entity.setTitle(this.getTitle());
        entity.setPrice(this.getPrice());
        entity.setCopies(this.getCopies());
        entity.setOrderQty(this.getOrderQty());
        entity.setCurrentIssue(this.getCurrentIssue());
        entity.setHasDisc(this.hasDisc);
        return entity;
    }

    public static DiscMag fromEntity(DiscMagEntity entity) {
        DiscMag discMag = new DiscMag(
                entity.getHasDisc(),
                entity.getOrderQty(),
                entity.getCurrentIssue(),
                entity.getTitle(),
                entity.getPrice(),
                entity.getCopies()
        );
        discMag.setDbId(entity.getId());
        discMag.setProductId(entity.getProductId());
        return discMag;
    }

    @Override
    public String toString() {
        return "DiscMag{hasDisc=" + hasDisc + ", " + super.toString() + "}";
    }
}
