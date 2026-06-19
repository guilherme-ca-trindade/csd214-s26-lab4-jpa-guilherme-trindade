package bookstore.jdbc;

import bookstore.pojos.Tire;

import java.sql.*;

public class JdbcTireApp {
    private static final String URL = "jdbc:mysql://localhost:3333/bookstore";
    private static final String USER = "csd214";
    private static final String PASSWORD = "itstudies12345";

    public static void main(String[] args) {
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Create table
            createTable();
            
            // Insert sample tires
            Tire tire1 = new Tire("Michelin", 89.99, 18.0);
            Tire tire2 = new Tire("Goodyear", 79.99, 17.5);
            
            insertTire(tire1);
            insertTire(tire2);
            
            // List all tires
            listAllTires();
            
            // Update a tire price
            updatePrice("Michelin", 99.99);
            
            // List after update
            System.out.println("\nAfter Price Update:");
            listAllTires();
            
            // Delete a tire
            deleteTire("Goodyear");
            
            // List after delete
            System.out.println("\nAfter Deletion:");
            listAllTires();
            
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database error!");
            e.printStackTrace();
        }
    }

    private static void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS tires (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "product_id VARCHAR(255) UNIQUE NOT NULL, " +
                "manufacturer VARCHAR(255) NOT NULL, " +
                "price DOUBLE NOT NULL, " +
                "diameter DOUBLE NOT NULL)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tires table created successfully!");
        }
    }

    private static void insertTire(Tire tire) throws SQLException {
        String sql = "INSERT INTO tires (product_id, manufacturer, price, diameter) " +
                "VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, tire.getProductId());
            ps.setString(2, tire.getManufacturer());
            ps.setDouble(3, tire.getPrice());
            ps.setDouble(4, tire.getDiameter());
            
            try {
                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Tire inserted: " + tire.getManufacturer() +
                            " (Product ID: " + tire.getProductId() + ")");
                }
            } catch (SQLException e) {
                if (e.getErrorCode() == 1062) {
                    System.out.println("Tire already exists (duplicate product ID): " + tire.getProductId());
                } else {
                    throw e;
                }
            }
        }
    }

    private static void listAllTires() throws SQLException {
        String sql = "SELECT * FROM tires";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\nAll Tires in Database:");
            System.out.println("===================================");
            
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("  Product ID: " + rs.getString("product_id"));
                System.out.println("  Manufacturer: " + rs.getString("manufacturer"));
                System.out.println("  Price: $" + rs.getDouble("price"));
                System.out.println("  Diameter: " + rs.getDouble("diameter") + "\"");
                System.out.println("-----------------------------------");
            }
            
            if (!hasData) {
                System.out.println("No tires found in database.");
            }
        }
    }

    private static void updatePrice(String manufacturer, double newPrice) throws SQLException {
        String sql = "UPDATE tires SET price = ? WHERE manufacturer = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setDouble(1, newPrice);
            ps.setString(2, manufacturer);
            
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Updated " + rowsUpdated + " tire(s): " +
                        manufacturer + " new price: $" + newPrice);
            } else {
                System.out.println("No tires found with manufacturer: " + manufacturer);
            }
        }
    }

    private static void deleteTire(String manufacturer) throws SQLException {
        String sql = "DELETE FROM tires WHERE manufacturer = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, manufacturer);
            
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Deleted " + rowsDeleted + " tire(s) from manufacturer: " + manufacturer);
            } else {
                System.out.println("No tires found with manufacturer: " + manufacturer);
            }
        }
    }
}
