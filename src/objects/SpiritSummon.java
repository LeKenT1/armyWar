package src.objects;

import src.characters.Character;

public class SpiritSummon extends GameObject {
    private String spiritType;

    public SpiritSummon(String spiritType) {
        super("Spirit Summon (" + spiritType + ")");
        this.spiritType = spiritType;
    }

    @Override
    public void applyEffect(Character user, Character target) {
        System.out.println(user.getName() + " summons a " + spiritType + " spirit!");
        user.setSpirit(true);
    }
}

class SpiritSummonTest {
    public static void main(String[] args) {
        SpiritSummon spiritSummon = new SpiritSummon("Fire");
        assert spiritSummon.getName().equals("Spirit Summon (Fire)") : "Name not set correctly";
        
        Character user = new Character("User", 100, 10, 5, 10) {};
        Character target = new Character("Target", 100, 10, 5, 10) {};
        
        spiritSummon.applyEffect(user, target);
        assert user.hasSpirit() : "Spirit not summoned correctly";
        
        System.out.println("All SpiritSummon tests passed!");
    }
}