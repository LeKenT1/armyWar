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

    // public void addObject(GameObject object) {
    //     if (objects.size() < 2) {
    //         objects.add(object);
    //     } else {
    //         System.out.println("You can only choose 2 objects.");
    //     }
    // }

    public void chooseObject(GameObject object) {
        if (objects.size() < 2) {
            objects.add(object);
            System.out.println("Objet ajouté à l'armée : " + object.getClass().getSimpleName());
        } else {
            System.out.println("L'armée a déjà 2 objets.");
        }
    }

    public boolean isDefeated() {
        return characters.stream().allMatch(c -> c.getHp() <= 0);
    }
}
