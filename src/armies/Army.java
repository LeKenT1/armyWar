package src.armies;

import src.objects.GameObject;
import src.characters.Character;

import java.util.ArrayList;
import java.util.List;

public class Army {
    private List<Character> characters;
    private List<GameObject> objects;

    public Army() {
        this.characters = new ArrayList<>();
        this.objects = new ArrayList<>();
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void chooseObject(GameObject object) {
        if (objects.size() < 2) {
            objects.add(object);
            System.out.println("Object added to the army: " + object.getClass().getSimpleName());
        } else {
            System.out.println("The army already has 2 objects.");
        }
    }

    public boolean isDefeated() {
        return characters.stream().allMatch(c -> c.getHp() <= 0);
    }
}

class ArmyTest {
    public static void main(String[] args) {
        Army army = new Army();
        Character testCharacter = new Character("Test", 100, 10, 10, 5) {};
        army.addCharacter(testCharacter);
        assert army.getCharacters().size() == 1 : "Character not added to army";
        
        GameObject testObject = new GameObject("TestObject") {
            @Override
            public void applyEffect(Character user, Character target) {}
            
            @Override
            public boolean isBeneficial() {
                return true;
            }
        };
        army.chooseObject(testObject);
        assert army.getObjects().size() == 1 : "Object not added to army";
        
        army.chooseObject(testObject);
        army.chooseObject(testObject);
        assert army.getObjects().size() == 2 : "Army should have max 2 objects";
        
        System.out.println("All tests passed!");
    }
}