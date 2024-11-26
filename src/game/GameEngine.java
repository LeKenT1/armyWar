package src.game;

import src.objects.GameObject;
import src.characters.Character;

import java.util.Random;
import java.util.Scanner;

public class GameEngine {
    private Player player1;
    private Player player2;
    private Scanner scanner;

    public GameEngine(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Game started!");

        while (true) {
            playTurn(player1, player2);
            if (player2.getArmy().isDefeated()) {
                System.out.println(player1.getName() + " wins!");
                break;
            }

            playTurn(player2, player1);
            if (player1.getArmy().isDefeated()) {
                System.out.println(player2.getName() + " wins!");
                break;
            }
        }
    }

    private void playTurn(Player currentPlayer, Player opponent) {
        System.out.println(currentPlayer.getName() + "'s turn!");

        for (Character character : currentPlayer.getArmy().getCharacters()) {
            if (character.getHp() <= 0) {
                continue;
            }

            System.out.println(character + " - Choose an action: 1. Attack  2. Defend  3. Use Object");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> attack(character, opponent);
                case 2 -> defend(character);
                case 3 -> useObject(currentPlayer, opponent, character);
                default -> System.out.println("Invalid choice, skipping turn...");
            }
        }
    }

    private void attack(Character attacker, Player opponent) {
        Random random = new Random();
        
        Character target = opponent.getArmy().getCharacters()
                .stream()
                .filter(c -> c.getHp() > 0)
                .skip(random.nextInt((int) opponent.getArmy().getCharacters().stream().filter(c -> c.getHp() > 0).count()))
                .findFirst()
                .orElse(null);
    
        if (target != null) {
            System.out.println(attacker.getName() + " attacks " + target.getName() + "!");
    
            int damage = attacker.getAttack();
            int defense = target.getDefense();
    
            int finalDamage = Math.max(damage - defense, 0);
    
            target.setHp(target.getHp() - finalDamage);
    
            System.out.println(target.getName() + " receives " + finalDamage + " damage.");
            System.out.println(target.getName() + " now has " + target.getHp() + " HP.");
        }
    }
    

    private void defend(Character character) {
        System.out.println(character.getName() + " is defending!");
        character.setDefending(true);
    }

    private void useObject(Player currentPlayer, Player opponent, Character user) {
        if (currentPlayer.getArmy().getObjects().isEmpty()) {
            System.out.println("No objects available.");
            return;
        }

        System.out.println("Choose an object to use:");
        for (int i = 0; i < currentPlayer.getArmy().getObjects().size(); i++) {
            System.out.println((i + 1) + ". " + currentPlayer.getArmy().getObjects().get(i).getName());
        }

        int choice = scanner.nextInt() - 1;

        if (choice >= 0 && choice < currentPlayer.getArmy().getObjects().size()) {
            GameObject object = currentPlayer.getArmy().getObjects().get(choice);

            System.out.println("Choose a target:");
            for (int i = 0; i < opponent.getArmy().getCharacters().size(); i++) {
                System.out.println((i + 1) + ". " + opponent.getArmy().getCharacters().get(i).getName());
            }

            int targetChoice = scanner.nextInt() - 1;

            if (targetChoice >= 0 && targetChoice < opponent.getArmy().getCharacters().size()) {
                object.applyEffect(user, opponent.getArmy().getCharacters().get(targetChoice));
            }
        }
    }
}
