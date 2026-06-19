package bookstore.jdbc;

import bookstore.pojos.NintendoGame;

import java.sql.*;

public class JdbcNintendoGameApp {
    private static final String URL = "jdbc:mysql://localhost:3333/bookstore";
    private static final String USER = "csd214";
    private static final String PASSWORD = "itstudies12345";

    public static void main(String[] args) {
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Create table
            createTable();
            
            // Insert a sample Nintendo Game
            NintendoGame game1 = new NintendoGame(
                "The Legend of Zelda: Breath of the Wild",
                59.99,
                10,
                "Switch",
                "Adventure"
            );
            NintendoGame game2 = new NintendoGame(
                "Mario Kart 8 Deluxe",
                49.99,
                15,
                "Switch",
                "Racing"
            );
            
            insertGame(game1);
            insertGame(game2);
            
            // List all games
            listAllGames();
            
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database error!");
            e.printStackTrace();
        }
    }

    private static void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS nintendo_games (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "product_id VARCHAR(255) UNIQUE NOT NULL, " +
                "title VARCHAR(255) NOT NULL, " +
                "price DOUBLE NOT NULL, " +
                "copies INT NOT NULL, " +
                "platform VARCHAR(255) NOT NULL, " +
                "genre VARCHAR(255) NOT NULL)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Nintendo Games table created successfully!");
        }
    }

    private static void insertGame(NintendoGame game) throws SQLException {
        String sql = "INSERT INTO nintendo_games (product_id, title, price, copies, platform, genre) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, game.getProductId());
            ps.setString(2, game.getTitle());
            ps.setDouble(3, game.getPrice());
            ps.setInt(4, game.getCopies());
            ps.setString(5, game.getPlatform());
            ps.setString(6, game.getGenre());
            
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Game inserted: " + game.getTitle() +
                        " (Product ID: " + game.getProductId() + ")");
            }
        }
    }

    private static void listAllGames() throws SQLException {
        String sql = "SELECT * FROM nintendo_games";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\nAll Nintendo Games in Database:");
            System.out.println("===================================");
            
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("  Product ID: " + rs.getString("product_id"));
                System.out.println("  Title: " + rs.getString("title"));
                System.out.println("  Platform: " + rs.getString("platform"));
                System.out.println("  Genre: " + rs.getString("genre"));
                System.out.println("  Price: $" + rs.getDouble("price"));
                System.out.println("  Copies: " + rs.getInt("copies"));
                System.out.println("-----------------------------------");
            }
            
            if (!hasData) {
                System.out.println("No games found with this title!");
            }
        }
    }
}
