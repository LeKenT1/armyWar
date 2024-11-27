package src.characters;

import src.objects.GameObject;

public abstract class Character {
    private String name;
    private int hp;
    private int attack;
    private int defense;
    private boolean defending;
    private boolean spirit;
    private int cost;
    private String action;

    public Character(String name, int hp, int attack, int defense, int cost) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.defending = false;
        this.spirit = false;
        this.cost = cost;
        this.action = "";
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public boolean isDefending() {
        return defending;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public boolean hasSpirit() {
        return spirit;
    }

    public void setSpirit(boolean spirit) {
        this.spirit = spirit;
    }

    public int getCost() {
        return cost;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void useObject(GameObject object, Character target) {
        System.out.println(name + " is using " + object.getName() + "!");
        object.applyEffect(this, target);
    }

    @Override
    public String toString() {
        return name + " [HP: " + hp + ", Attack: " + attack + ", Defense: " + defense + 
               ", Defending: " + defending + ", Spirit: " + spirit + ", Cost: " + cost + "]";
    }
}

class CharacterTest {
    public static void main(String[] args) {
        Character testChar = new Character("TestChar", 100, 10, 5, 5) {};
        assert testChar.getName().equals("TestChar") : "Name not set correctly";
        assert testChar.getHp() == 100 : "HP not set correctly";
        assert testChar.getAttack() == 10 : "Attack not set correctly";
        assert testChar.getDefense() == 5 : "Defense not set correctly";
        assert testChar.getCost() == 5 : "Cost not set correctly";
        System.out.println("All tests passed!");
    }
}