import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class p07_GetEmployeeWithProject {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());

        Employee employee = entityManager.find(Employee.class, id);

        System.out.println(employee);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
