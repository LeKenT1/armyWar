package src.game;

import java.util.ArrayList;
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

    public void addCredits(int amount) {
        if (amount > 0) {
            this.credits += amount;
        } else {
            System.out.println("The number of credits must be positive!");
        }
    }

    public boolean spendCredits(int amount) {
        if (amount <= credits) {
            credits -= amount;
            return true;
        } else {
            System.out.println("Insufficient credits!");
            return false;
        }
    }

    public void chooseCharacter(Character character) {
        int cost = character.getCost();
        if (spendCredits(cost)) {
            army.addCharacter(character);
            System.out.println(getName() + " has chosen " + character.getName() + " for " + cost + " credits.");
        } else {
            System.out.println(getName() + " doesn't have enough credits to choose " + character.getName());
        }
    }
}

class PlayerTest {
    public static void main(String[] args) {
        Player player = new Player("TestPlayer");
        assert player.getName().equals("TestPlayer") : "Name not set correctly";
        assert player.getCredits() == 0 : "Initial credits should be 0";
        
        player.addCredits(100);
        assert player.getCredits() == 100 : "Credits not added correctly";
        
        player.spendCredits(50);
        assert player.getCredits() == 50 : "Credits not spent correctly";
        
        Character testChar = new Character("TestChar", 100, 10, 5, 30) {};
        player.chooseCharacter(testChar);
        assert player.getArmy().getCharacters().size() == 1 : "Character not added to army";
        assert player.getCredits() == 20 : "Credits not deducted after choosing character";
        
        System.out.println("All Player tests passed!");
    }
}