package insam.dev;

import insam.dev.service.StudentService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("insam.dev");

        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        StudentService studentService = context.getBean(StudentService.class);

        Student student1 = new Student("Vasya", 22);
        Student student2 = new Student("Pasha", 20);

        studentService.saveStudent(student1);
        studentService.saveStudent(student2);

        Profile profile1 = new Profile("My bio", LocalDateTime.now(),student1);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(profile1);
        session.getTransaction().commit();
        session.close();
        
        session = sessionFactory.openSession();
        //profile1 = session.get(Profile.class, 1L);
        student1 = session.get(Student.class, 1L);
        session.beginTransaction();
       // session.remove(profile1);
        session.remove(student1);
        session.getTransaction().commit();
        session.close();

        
    }
}