import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class p04_EmployeesFromDepartment {

    private final static String PRINT_FORMAT = "%s %s from %s - $%.2f%n";

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("SELECT e FROM Employee e WHERE e.department.id = :d " +
                "order by e.salary, e.id", Employee.class)
                .setParameter("d", 6)
                        .getResultList()
                                .forEach(e -> System.out.printf(PRINT_FORMAT,
                                        e.getFirstName(),
                                        e.getLastName(),
                                        e.getDepartment().getName(),
                                        e.getSalary()));

        entityManager.close();
    }
}