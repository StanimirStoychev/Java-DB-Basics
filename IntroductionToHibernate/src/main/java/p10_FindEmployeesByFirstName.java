import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class p10_FindEmployeesByFirstName {

    private static final String PRINT_FORMAT = "%s %s - %s - ($%.2f)%n";

    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        final String firstName = new Scanner(System.in).nextLine();

        entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.firstName LIKE :fn", Employee.class)
                .setParameter("fn", firstName + "%")
                .getResultList()
                .forEach(employee -> System.out.printf(PRINT_FORMAT,
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getJobTitle(),
                        employee.getSalary()));

        entityManager.close();
    }
}
