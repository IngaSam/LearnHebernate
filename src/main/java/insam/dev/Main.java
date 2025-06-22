package insam.dev;

import insam.dev.service.CourseService;
import insam.dev.service.GroupService;
import insam.dev.service.ProfileService;
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
        ProfileService profileService = context.getBean(ProfileService.class);
        GroupService groupService = context.getBean(GroupService.class);
        CourseService courseService = context.getBean(CourseService.class);

/*
        Group group1 = groupService.saveGroup("1",2024L);
        Group group2 = groupService.saveGroup("2",2024L);
        Group group3 = groupService.saveGroup("3",2024L);
*/

/*        Student student1 = new Student("Vasya", 22, group1);
        Student student2 = new Student("Pasha", 20, group1);

        studentService.saveStudent(student1);
        studentService.saveStudent(student2);*/

        Course course1 =new Course("math-1", "math");
        Course course2 =new Course("math-2", "math");
        Course course3 =new Course("math-3", "math");
/*        courseService.saveCourse(course1);
        courseService.saveCourse(course2);
        courseService.saveCourse(course3);*/
        courseService.enrollStudentToCourse(2L, 2L);
        courseService.enrollStudentToCourse(3L, 2L);

        Student student = studentService.getById(2L);
        System.out.println(student);
    }
}