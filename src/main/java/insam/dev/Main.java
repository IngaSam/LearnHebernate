package insam.dev;

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

        Group group1 = groupService.saveGroup("1",2024L);
        Group group2 = groupService.saveGroup("2",2024L);
        Group group3 = groupService.saveGroup("3",2024L);

        Student student1 = new Student("Vasya", 22, group1);
        Student student2 = new Student("Pasha", 20, group1);

        studentService.saveStudent(student1);
        studentService.saveStudent(student2);

        var session = sessionFactory.openSession();

        group1=session.get(Group.class, 1L);
        session.close();
        List<Student> studentList=group1.getStudentList();
        studentList.forEach(System.out::println);



        
    }
}