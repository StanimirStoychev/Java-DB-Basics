import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

    public class p01_ChangeCasing {
        public static void main(String[] args) {
            EntityManagerFactory factory =
                    Persistence.createEntityManagerFactory("soft_uni");
            EntityManager entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("FROM Town t", Town.class);
            List<Town> resultList = query.getResultList();

            resultList.stream().
                    filter(t -> t.getName().length() <= 5).
                    forEach(t -> {
                        t.setName(t.getName().toUpperCase());
                        entityManager.persist(t);
                    });

            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }