import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class p05_AddingNewAddressAndUpdateEmployee {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        final Scanner scanner = new Scanner(System.in);

        Address address = new Address();
        address.setText("Vitoshka 15");
        entityManager.persist(address);

        final String lastName = scanner.nextLine();

        entityManager.createQuery("UPDATE Employee e SET e.address = :a WHERE e.lastName = :ln")
                        .setParameter("a", address)
                                .setParameter("ln", lastName)
                                        .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
