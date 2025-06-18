package insam.dev.service;

import insam.dev.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final SessionFactory sessionFactory;

    public StudentService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Student saveStudent(Student student) {
        Session session = sessionFactory.openSession();

        session.beginTransaction(); //стартует транзакция
        session.persist(student);//сохраняем студента в БД
        session.getTransaction().commit();
        session.close();
        return student;
    }
    public void deleteStudent(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Student studentForDelete = session.get(Student.class, 2L);
        session.remove(studentForDelete);
        session.close();
    }
    public Student getById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Student student = session.get(Student.class, id);
        session.close();
        return student;
    }
    public List<Student> findAll() {
        Session session = sessionFactory.openSession();
        List<Student> allStudents = session
                .createQuery("select  s from Student s", Student.class)
                .list();
        session.close();
        return allStudents;
    }
    public Student updateStudent(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction(); //стартует транзакция
        session.getTransaction().commit();
        student = session.merge(student);
        session.close();
        return student;
    }
}
