package src.characters;

public class Dragon extends Character {

    public Dragon() {
        super("Dragon", 300, 60, 60, 20);
    }
}

class DragonTest {
    public static void main(String[] args) {
        Dragon dragon = new Dragon();
        assert dragon.getName().equals("Dragon") : "Name not set correctly";
        assert dragon.getHp() == 300 : "HP not set correctly";
        assert dragon.getAttack() == 60 : "Attack not set correctly";
        assert dragon.getDefense() == 60 : "Defense not set correctly";
        assert dragon.getCost() == 20 : "Cost not set correctly";
        System.out.println("All Dragon tests passed!");
    }
}