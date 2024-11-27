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