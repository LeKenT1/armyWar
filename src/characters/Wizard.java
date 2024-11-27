package src.characters;

public class Wizard extends Character {

    public Wizard() {
        super("Wizard", 60, 60, 20, 6);
    }


}

class WizardTest {
    public static void main(String[] args) {
        Wizard wizard = new Wizard();
        assert wizard.getName().equals("Wizard") : "Name not set correctly";
        assert wizard.getHp() == 60 : "HP not set correctly";
        assert wizard.getAttack() == 60 : "Attack not set correctly";
        assert wizard.getDefense() == 20 : "Defense not set correctly";
        assert wizard.getCost() == 6 : "Cost not set correctly";
        System.out.println("All Wizard tests passed!");
    }
}