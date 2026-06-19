package bookstore.pojos;

import java.io.Serializable;
import java.util.UUID;

// Marked abstract because it implements SaleableItem but doesn't implement getPrice()
// (Price is defined in children: Ticket and Publication)
public abstract class Product extends Editable implements SaleableItem, Serializable {
    private String productId;
    // Mirrors the database primary key (ProductEntity.id). Kept on the DTO so that
    // toEntity()/em.merge() update the existing row instead of inserting a duplicate.
    private Long dbId;

    public Product() {
        // generate a default productID
        setProductId(UUID.randomUUID().toString());
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                "} ";
    }
}
