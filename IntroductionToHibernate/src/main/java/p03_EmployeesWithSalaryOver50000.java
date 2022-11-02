import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class p03_EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        List<String> resultList = entityManager.createQuery("SELECT e.firstName FROM Employee e WHERE e.salary > 50000").getResultList();

        //for (String firstName : resultList) {
        //    System.out.println(firstName);
        //}

        resultList.forEach(System.out::println);

        entityManager.close();
    }
}
