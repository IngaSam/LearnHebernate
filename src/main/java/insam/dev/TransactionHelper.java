package insam.dev;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class TransactionHelper {

    private final SessionFactory sessionFactory;
    public TransactionHelper(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void executeInTransaction(Consumer<Session> action) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.getTransaction();
            transaction.begin();

            action.accept(session);

            session.getTransaction().commit();
        }catch(Exception e){
            if (transaction != null ) {
                transaction.rollback();
            } throw e;
        }
    }

    public<T> T executeInTransaction(Function<Session, T> action) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.getTransaction();
            transaction.begin();

            var result = action.apply(session);

            session.getTransaction().commit();
            return result;

        }catch(Exception e){
            if (transaction != null ) {
                transaction.rollback();
            } throw e;
        }
    }

}
