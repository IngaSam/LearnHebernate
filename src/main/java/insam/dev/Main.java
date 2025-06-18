package insam.dev;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

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

        session= sessionFactory.openSession();
        student1 = session.merge(student1);

        session.beginTransaction();
        student1.setName("Dima");
        session.detach(student1); //ссесия перестала следить за сущностью
        student1.setAge(30);

        session.getTransaction().commit();

        session.close();

/*        //Запрос в БД
        Student studentById1 = session.get(Student.class, 1L);
        System.out.println("student1 " + studentById1.toString());

        Student studentById2 = session.createQuery(
                "select s from Student s where s.id = :id", Student.class).
                setParameter("id", 2)
                .getSingleResult();
        System.out.println("student2 " + studentById2.toString());

        //Обновление БД
        session.beginTransaction();
        Student studentForUpdate = session.get(Student.class, 1L);
        studentForUpdate.setName("Dima");
        studentForUpdate.setAge(30);
        session.getTransaction().commit();

        //Удаление данных в БД
        session.beginTransaction();
*//*        Student studentForDelete = session.get(Student.class, 2L);
        session.remove(studentForDelete);*//*
        //С помощью JPQL
        *//*session.createQuery("delete from Student s where s.id = 2")
                        .executeUpdate();*//*

      //С помощью SQL запроса
*//*        session.createNativeQuery("delete from students s where s.id=2")
                .executeUpdate();*//*

        session.getTransaction().commit();

        //Вывод всех студентов
        List<Student> allStudents = session
                .createQuery("select  s from Student s", Student.class)
                .list();

        //Запрос по имени
        Student studentByName = session.createQuery(
                        "select s from Student s where s.name = :name", Student.class).
                setParameter("name", "Pasha")
                .getSingleResult();
        System.out.println("Student by name: "+ studentByName.toString());

        //Для проверки на уникальность
*//*        session.beginTransaction();
        Student student3 = new Student("Pasha", 21);
        session.persist(student3);
        session.getTransaction().commit();*//*

        session.close();*/
    }
}