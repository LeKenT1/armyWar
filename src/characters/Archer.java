package src.characters;

public class Archer extends Character {

    public Archer() {
        super("Archer", 40, 30, 10, 3);
    }
}

class ArcherTest {
    public static void main(String[] args) {
        Archer archer = new Archer();
        assert archer.getName().equals("Archer") : "Name not set correctly";
        assert archer.getHp() == 40 : "HP not set correctly";
        assert archer.getAttack() == 30 : "Attack not set correctly";
        assert archer.getDefense() == 10 : "Defense not set correctly";
        assert archer.getCost() == 3 : "Cost not set correctly";
        System.out.println("All Archer tests passed!");
    }
}