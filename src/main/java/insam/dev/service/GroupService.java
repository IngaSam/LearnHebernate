package insam.dev.service;

import insam.dev.Group;
import insam.dev.TransactionHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public GroupService(SessionFactory sessionFactory,
                        TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    //сохранение группы
    public Group saveGroup(String number, Long gradYear) {
        return  transactionHelper.executeInTransaction(session -> {
            var group = new Group(number, gradYear);
            session.persist(group);
            return group;
        });
    }
    public List<Group> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT g from Group g", Group.class).list();

        }
    }
}
