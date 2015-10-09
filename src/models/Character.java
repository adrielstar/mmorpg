package models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adriel on 10/7/2015.
 */


@Entity
@Table(name = "characters")
public class Character {

    @Id
    @Column(name = "name")
    private String cCharacterName;

    @Column(name = "class")
    private String cCharacterClass;

    @Column(name = "race")
    private String cCharacterRace;

    @Column(name = "level")
    private Integer cCharacterLevel;

    @ManyToMany(mappedBy="mCharacters")
    private Set<User> cUsers = new HashSet<>();

    public Character() {
        super();
    }

    public Character(String characterName, String characterClass, String characterRace,
                     Integer characterLevel) {
        super();
        cCharacterName = characterName;
        cCharacterClass = characterClass;
        cCharacterRace = characterRace;
        cCharacterLevel = characterLevel;
    }

    public String getCharacterName() {
        return cCharacterName;
    }

    public String getCharacterClass() {
        return cCharacterClass;
    }

    public String getCharacterRace() {
        return cCharacterRace;
    }

    public Integer getCharacterLevel() {
        return cCharacterLevel;
    }

    public Set<User> getUsers() {
        return cUsers;
    }
}
