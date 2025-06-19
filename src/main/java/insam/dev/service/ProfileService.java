package insam.dev.service;

import insam.dev.Profile;
import insam.dev.TransactionHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public ProfileService(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }
    public Profile saveProfile(Profile profile) {
        return transactionHelper.executeInTransaction(session -> {
            session.persist(profile);
            return profile;
        });
    }
}
