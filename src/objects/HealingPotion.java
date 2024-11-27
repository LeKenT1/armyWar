package src.objects;

import src.characters.Character;

public class HealingPotion extends GameObject {
    private int healAmount;

    public HealingPotion(int healAmount) {
        super("Healing Potion");
        this.healAmount = healAmount;
    }

    @Override
    public boolean isBeneficial() {
        return true;
    }

    @Override
    public void applyEffect(Character user, Character target) {
        System.out.println(user.getName() + " uses a Healing Potion on " + target.getName());
        target.setHp(target.getHp() + healAmount);
        System.out.println(target.getName() + " is healed by " + healAmount + " HP.");
    }
}

class HealingPotionTest {
    public static void main(String[] args) {
        HealingPotion healingPotion = new HealingPotion(50);
        assert healingPotion.getName().equals("Healing Potion") : "Name not set correctly";
        assert healingPotion.isBeneficial() : "HealingPotion should be beneficial";
        
        Character user = new Character("User", 100, 10, 5, 10) {};
        Character target = new Character("Target", 50, 10, 5, 10) {};
        
        healingPotion.applyEffect(user, target);
        assert target.getHp() == 100 : "Healing not applied correctly";
        
        System.out.println("All HealingPotion tests passed!");
    }
}