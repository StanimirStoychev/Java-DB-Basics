import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class p09_IncreaseSalaries {

    private static final String PRINT_FORMAT = "%s %s ($%.2f)%n";


    public static void main(String[] args) {

        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        int countOfUpdates = entityManager.createQuery("UPDATE Employee e set e.salary = e.salary * 1.12 WHERE e.department.id in (1, 2, 3, 14)")
                .executeUpdate();

        if (countOfUpdates != 0) {
            entityManager.getTransaction().commit();

            entityManager.createQuery("Select e From Employee as e " +
                    "WHERE e.department.name in ('Information Services', 'Marketing', 'Tool Design', 'Engineering')", Employee.class)
                    .getResultList()
                    .forEach(e -> System.out.printf(PRINT_FORMAT, e.getFirstName(), e.getLastName(), e.getSalary()));
        } else {
            entityManager.getTransaction().rollback();
        }

        entityManager.close();
    }
}
