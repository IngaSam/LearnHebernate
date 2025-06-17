package insam.dev;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("insam.dev");

        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        Session session = sessionFactory.openSession();

        Student student1 = new Student("Vasya", 22);
        Student student2 = new Student("Pasha", 20);

        session.beginTransaction(); //стартует транзакция
        session.persist(student1);//сохраняем студента в БД
        session.persist(student2);
        session.getTransaction().commit();//коммит в танзакции

        session.close();
    }
}