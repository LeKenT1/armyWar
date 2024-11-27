package src.characters;

public class Warrior extends Character {

    public Warrior() {
        super("Warrior", 70, 20, 40, 4);
    }
}

class WarriorTest {
    public static void main(String[] args) {
        Warrior warrior = new Warrior();
        assert warrior.getName().equals("Warrior") : "Name not set correctly";
        assert warrior.getHp() == 70 : "HP not set correctly";
        assert warrior.getAttack() == 20 : "Attack not set correctly";
        assert warrior.getDefense() == 40 : "Defense not set correctly";
        assert warrior.getCost() == 4 : "Cost not set correctly";
        System.out.println("All Warrior tests passed!");
    }
}