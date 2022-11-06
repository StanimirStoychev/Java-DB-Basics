package Hospital;

import javax.persistence.Persistence;
import java.time.Period;

public class Main {
    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("soft_uni").createEntityManager();
    }
}
