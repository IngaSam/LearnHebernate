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

        Student studentById1 = session.get(Student.class, 1L);
        System.out.println("student1 " + studentById1.toString());

        Student studentById2 = session.createQuery(
                "select s from Student s where s.id = :id", Student.class).
                setParameter("id", 2)
                .getSingleResult();
        System.out.println("student2 " + studentById2.toString());
        session.close();
    }
}