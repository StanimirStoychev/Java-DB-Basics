import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Scanner;

public class p02_ContainsEmployee {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        final String[] name = new Scanner(System.in).nextLine().split("\\s+");
        String firstName = name[0];
        String lastName = name[1];

        Long count = entityManager.createQuery
                        ("SELECT COUNT(e) FROM Employee e WHERE e.firstName = :fn AND e.lastName = :ln", Long.class)
                .setParameter("fn", firstName)
                .setParameter("ln", lastName)
                .getSingleResult();

        if (count == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }

        entityManager.close();
    }
}
