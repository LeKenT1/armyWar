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

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void addObject(GameObject object) {
        if (objects.size() < 2) { // Limit to 2 objects
            objects.add(object);
        } else {
            System.out.println("You can only choose 2 objects.");
        }
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public boolean isDefeated() {
        return characters.stream().allMatch(c -> c.getHp() <= 0);
    }
}
