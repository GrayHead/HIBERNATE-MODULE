package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ElementCollection
    private List<String> skills;

    public User(String name) {
        this.name = name;
    }

    public User(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public User(String name, Gender gender, List<String> skills) {
        this.name = name;
        this.gender = gender;
        this.skills = skills;
    }
}
