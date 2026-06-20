package bookstore.jpa;

import bookstore.entities.NintendoGameEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.UUID;

/**
 * Standalone CRUD lifecycle demonstration for the Nintendo niche (Lab 4, Part C).
 * <p>
 * Proves that the JPA mappings and entity lifecycle transactions work end to end:
 * Create (em.persist) -> Read (JPQL) -> Update (em.find by PK + Hibernate Dirty
 * Checking) -> Delete (em.remove). Every database mutation is wrapped in a
 * transaction, rolled back on failure, and connections are released in a finally
 * block to prevent leaks.
 */
public class JpaNintendoApp {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore-pu");
        EntityManager em = emf.createEntityManager();

        try {
            // --- CREATE ---
            System.out.println("\n[Step 1] Persisting a new Nintendo Game...");
            em.getTransaction().begin();
            NintendoGameEntity game = new NintendoGameEntity(
                    "Super Mario Odyssey", 59.99, 12, "Switch", "Adventure");
            game.setProductId(UUID.randomUUID().toString()); // business key
            em.persist(game); // Hibernate generates the INSERT
            em.getTransaction().commit();
            System.out.println("Game saved with Database Primary Key ID: " + game.getId()
                    + " | productId: " + game.getProductId());

            // --- READ (Polymorphic JPQL: query the Class, not the table) ---
            listGames(em, "[Step 2] Database Nintendo Games:");

            // --- UPDATE (Managed entity + Dirty Checking) ---
            System.out.println("\n[Step 3] Editing Game Price (Dirty Checking)...");
            em.getTransaction().begin();
            NintendoGameEntity managed = em.find(NintendoGameEntity.class, game.getId());
            if (managed != null) {
                managed.setPrice(49.99); // Changing the Java field triggers UPDATE on commit
            }
            em.getTransaction().commit();
            listGames(em, "[Step 4] After Price Update:");

            // --- DELETE ---
            System.out.println("\n[Step 5] Deleting the Game...");
            em.getTransaction().begin();
            NintendoGameEntity target = em.find(NintendoGameEntity.class, game.getId());
            if (target != null) {
                em.remove(target); // Hibernate generates the DELETE
            }
            em.getTransaction().commit();
            listGames(em, "[Step 6] After Deletion:");

        } catch (Exception e) {
            // Transaction integrity: roll back any half-applied transaction.
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Transaction failed, rolled back: " + e.getMessage());
        } finally {
            // Always release scarce database resources.
            em.close();
            emf.close();
        }
    }

    private static void listGames(EntityManager em, String header) {
        System.out.println("\n" + header);
        List<NintendoGameEntity> games = em.createQuery(
                "SELECT n FROM NintendoGameEntity n", NintendoGameEntity.class).getResultList();
        if (games.isEmpty()) {
            System.out.println("No games found.");
        } else {
            games.forEach(g -> System.out.println(
                    " > " + g.getTitle() + " (" + g.getGenre() + ") on " + g.getPlatform()
                            + " | $" + g.getPrice()));
        }
    }
}
