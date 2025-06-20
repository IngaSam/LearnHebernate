package insam.dev;

import jakarta.persistence.*;

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

    public Student() {
    }

    public Student(String name,  Integer age, Group group) {
        this.group = group;
        this.age = age;
        this.name = name;
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
