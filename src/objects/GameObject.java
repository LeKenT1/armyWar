package src.objects;

import src.characters.Character;

public abstract class GameObject {
    private String name;

    public GameObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isBeneficial() {
        return false;
    }

    public abstract void applyEffect(Character user, Character target);
}
