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

class BombTest {
    public static void main(String[] args) {
        Bomb bomb = new Bomb(50);
        assert bomb.getName().equals("Bomb") : "Name not set correctly";
        
        Character user = new Character("User", 100, 10, 5, 10) {};
        Character target = new Character("Target", 100, 10, 5, 10) {};
        
        bomb.applyEffect(user, target);
        assert target.getHp() == 50 : "Damage not applied correctly";
        
        System.out.println("All Bomb tests passed!");
    }
}