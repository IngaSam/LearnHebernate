package insam.dev.service;

import insam.dev.Course;
import insam.dev.Student;
import insam.dev.TransactionHelper;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public CourseService(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }
    public Course saveCourse(Course course) {
        return transactionHelper.executeInTransaction(session ->{
            session.persist(course);
            return course;
        } );
    }
    public void enrollStudentToCourse(Long  courseId, Long studentId) {
        transactionHelper.executeInTransaction(session -> {
/*            var student = session.get(Student.class, studentId);
            var course = session.get(Course.class, courseId);
            student.getCourseList().add(course);*/

            String sql = """
                    INSERT INTO student_courses (student_id, course_id)
                    VALUES (:studentID , :courseID);
                    """;
            session.createNativeQuery(sql, Void.class)
                    .setParameter("studentID", studentId)
                    .setParameter("courseID", courseId)
                    .executeUpdate();
        });
    }
}
