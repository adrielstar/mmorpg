package service;

import dataAccObject.MainDataAccObject;
import models.Character;
import init.IdentetyUnit;

import java.util.List;
/**
 * Created by Adriel on 10/8/2015.
 */



public class CharacterServiceImpl extends MainDataAccObject implements CharacterService {

    @Override
    public boolean addCharacter(models.Character character) { return add(character); }

    @Override
    public List<Character> CharacterList() { return getList(IdentetyUnit.Character); }

    @Override
    public void deleteCharacter(String characterName) {
        delete(characterName);
    }

    @Override
    public void updateCharacter(Character character) {
        update(character);
    }
}