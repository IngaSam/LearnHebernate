package insam.dev.service;

import insam.dev.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final SessionFactory sessionFactory;

    public ProfileService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Profile saveProfile(Profile profile) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(profile);
        session.getTransaction().commit();
        session.close();
        return profile;
    }
}
