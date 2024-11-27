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

class GameObjectTest {
    public static void main(String[] args) {
        GameObject testObject = new GameObject("TestObject") {
            @Override
            public void applyEffect(Character user, Character target) {}
        };
        
        assert testObject.getName().equals("TestObject") : "Name not set correctly";
        assert !testObject.isBeneficial() : "isBeneficial should default to false";
        
        System.out.println("All GameObject tests passed!");
    }
}