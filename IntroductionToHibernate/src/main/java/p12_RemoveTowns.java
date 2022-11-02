import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class p12_RemoveTowns {

    private final static String PRINT_FORMAT = "%d address%s in %s deleted%n";

    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = factory.createEntityManager();

        final String townName = new Scanner(System.in).nextLine().trim();

        final Town town = entityManager.createQuery("SELECT t FROM Town AS t WHERE t.name = :tName", Town.class)
                .setParameter("tName", townName)
                .getSingleResult();

        final List<Address> addresses = entityManager
                .createQuery("SELECT a FROM Address AS a WHERE a.town.name = :tName", Address.class)
                .setParameter("tName", townName)
                .getResultList();

        entityManager.getTransaction().begin();

        addresses.forEach(address -> {
            for (Employee employee : address.getEmployees()) {
                employee.setAddress(null);
            }
            address.setTown(null);
            entityManager.remove(address);
        });
;
        entityManager.remove(town);

        entityManager.getTransaction().commit();

        System.out.printf(PRINT_FORMAT,
                addresses.size(),
                addresses.size() != 1 ? "es" : "",
                town.getName());

        entityManager.close();
    }
}
