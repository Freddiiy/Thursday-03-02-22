import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Facade {
    private static EntityManagerFactory emf;
    private static Facade instance;

    private Facade() {}

    public static Facade getORMMapper(EntityManagerFactory _emf) {
        if (instance == null) {
            instance = new Facade();
            emf = _emf;
        }
        return instance;
    }

    public List<User> getNames() {
        EntityManager entityManager = emf.createEntityManager();
        try {
            TypedQuery<User> query = entityManager.createQuery("select User from User", User.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public User getUserByName(String fname, String lname) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            return entityManager.find(User.class, fname);
        } finally {
            entityManager.close();
        }
    }
}
