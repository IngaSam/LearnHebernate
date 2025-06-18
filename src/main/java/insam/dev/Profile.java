package insam.dev;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="bio")
    private String bio;

    @Column(name="last_seen_time")
    private LocalDateTime lastSeenTime;

    @OneToOne
    @JoinColumn(name = "sdudent_id", referencedColumnName = "id") //id -находится в классе Student
    private Student student;

    public Profile() {
    }

    public Profile(String bio, LocalDateTime lastSeenTime, Student student) {
        this.bio = bio;
        this.lastSeenTime = lastSeenTime;
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDateTime getLastSeenTime() {
        return lastSeenTime;
    }

    public void setLastSeenTime(LocalDateTime lastSeenTime) {
        this.lastSeenTime = lastSeenTime;
    }

    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }
}
