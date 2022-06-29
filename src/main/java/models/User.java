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

    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name = "passport_id" , referencedColumnName = "id")
    private Passport passport;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_card",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> cards;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_sg",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =  @JoinColumn(name = "sg_id")
    )
    private List<Sunglass> sunglasses;




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

    public User(String name, Gender gender, List<String> skills, Passport passport) {
        this.name = name;
        this.gender = gender;
        this.skills = skills;
        this.passport = passport;
    }

    public User(String name, Gender gender, List<String> skills, Passport passport, List<Card> cards) {
        this.name = name;
        this.gender = gender;
        this.skills = skills;
        this.passport = passport;
        this.cards = cards;
    }

    public User(String name, Gender gender, List<String> skills, Passport passport, List<Card> cards, List<Sunglass> sunglasses) {
        this.name = name;
        this.gender = gender;
        this.skills = skills;
        this.passport = passport;
        this.cards = cards;
        this.sunglasses = sunglasses;
    }
}
