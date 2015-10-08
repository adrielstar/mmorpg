package service;

/**
 * Created by Adriel on 10/8/2015.
 */
import models.Character;

import java.util.List;

public interface CharacterService {
    boolean addCharacter(Character character);
    List<Character> CharacterList();
    void deleteCharacter(String characterName);
    void updateCharacter(Character character);
}