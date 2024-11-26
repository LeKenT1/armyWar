package src.game;

import java.util.List;

import src.armies.Army;
import src.characters.Character;
import src.objects.*;

public class Player {
    private String name;
    private Army army;
    private int credits;
    private List<GameObject> objectsChosen;

    public Player(String name) {
        this.name = name;
        this.army = new Army();
        this.credits = 0;
    }

    public String getName() {
        return name;
    }

    public Army getArmy() {
        return army;
    }

    public int getCredits() {
        return credits;
    }

    public List<GameObject> getObjectsChosen() {
        return objectsChosen;
    }

    public void addCredits(int amount) {
        if (amount > 0) {
            this.credits += amount;
        } else {
            System.out.println("Le nombre de crédits doit être positif !");
        }
    }

    public boolean spendCredits(int amount) {
        if (amount <= credits) {
            credits -= amount;
            return true;
        } else {
            System.out.println("Crédits insuffisants !");
            return false;
        }
    }

    public void chooseCharacter(Character character) {
        int cost = character.getCost();
        if (spendCredits(cost)) {
            army.addCharacter(character);
            System.out.println(getName() + " a choisi " + character.getName() + " pour " + cost + " crédits.");
        } else {
            System.out.println(getName() + " n'a pas assez de crédits pour choisir " + character.getName());
        }
    }
    
    public void chooseObject(GameObject object) {
        if (objectsChosen.size() < 2) {
            objectsChosen.add(object);
            System.out.println(getName() + " a choisi " + object.getClass().getSimpleName());
        } else {
            System.out.println(getName() + " a déjà choisi 2 objets, impossible d'en choisir un autre.");
        }
    }

}
