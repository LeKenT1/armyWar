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
