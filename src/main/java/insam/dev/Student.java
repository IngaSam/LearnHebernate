package insam.dev;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student name", unique = true, nullable = false)
    private String name;

    @Column(name="student age")
    private Integer age;

    @OneToOne(mappedBy = "student", cascade = CascadeType.REMOVE) //profile автоматом тоже будет переходить в состояние persist
    private Profile profile;

    @ManyToOne
    @JoinColumn(name ="group_id")
    private Group group;

    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(
            name="student_courses",
            joinColumns = @JoinColumn(name="student_id", referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name="course_id", referencedColumnName = "id"  ))
    private List<Course> courseList =new ArrayList<Course>();

    public Student() {
    }

    public Student(String name,  Integer age, Group group) {
        this.group = group;
        this.age = age;
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
