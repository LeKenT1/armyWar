package src.objects;

import src.characters.Character;

public class Bomb extends GameObject {

    private int damage;

    public Bomb(int damage) {
        super("Bomb");
        this.damage = damage;
    }

    @Override
    public void applyEffect(Character user, Character target) {
        System.out.println(user.getName() + " throws a Bomb at " + target.getName() + "!");
        target.setHp(target.getHp() - damage);
        System.out.println(target.getName() + " takes " + damage + " damage.");
    }
}
