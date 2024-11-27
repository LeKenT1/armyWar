package src.characters;

public class Troll extends Character {

    public Troll() {
        super("Troll", 130, 30, 50, 8);
    }
}

class TrollTest {
    public static void main(String[] args) {
        Troll troll = new Troll();
        assert troll.getName().equals("Troll") : "Name not set correctly";
        assert troll.getHp() == 130 : "HP not set correctly";
        assert troll.getAttack() == 30 : "Attack not set correctly";
        assert troll.getDefense() == 50 : "Defense not set correctly";
        assert troll.getCost() == 8 : "Cost not set correctly";
        System.out.println("All Troll tests passed!");
    }
}