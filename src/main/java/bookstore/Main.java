package bookstore;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bookstore Application Started");
        App app = new App();
        try {
            app.run();
        } finally {
            // Guarantees database resources are closed even on failure
            app.shutdown();
        }
    }
}
