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
        System.out.println("Game started!\n");

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
        System.out.println(currentPlayer.getName() + "'s turn!\n");

        // Demander les actions pour les personnages du joueur courant
        for (Character character : currentPlayer.getArmy().getCharacters()) {
            if (character.getHp() <= 0) {
                continue;
            }

            // Afficher les actions possibles pour chaque personnage
            System.out.println(
                    "\nChoose an action for your " + character.getName() + ":\n1. Attack\n2. Defend\n3. Use Object");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    character.setAction("Attack");
                    break;
                case 2:
                    character.setAction("Defend");
                    break;
                case 3:
                    character.setAction("Use Object");
                    break;
                default:
                    System.out.println("Invalid choice, skipping turn...");
                    break;
            }
        }

        // Maintenant, le joueur adverse (opposant) doit aussi choisir ses actions
        System.out.println(opponent.getName() + "'s turn!\n");

        for (Character character : opponent.getArmy().getCharacters()) {
            if (character.getHp() <= 0) {
                continue;
            }

            // Afficher les actions possibles pour chaque personnage
            System.out.println(
                    "\nChoose an action for your " + character.getName() + ":\n1. Attack\n2. Defend\n3. Use Object");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    character.setAction("Attack");
                    break;
                case 2:
                    character.setAction("Defend");
                    break;
                case 3:
                    character.setAction("Use Object");
                    break;
                default:
                    System.out.println("Invalid choice, skipping turn...");
                    break;
            }
        }

        // Après que les deux joueurs ont choisi les actions de leurs personnages, on
        // passe à l'exécution des actions

        // Exécuter les actions pour le joueur courant
        for (Character character : currentPlayer.getArmy().getCharacters()) {
            if (character.getHp() <= 0) {
                continue;
            }

            switch (character.getAction()) {
                case "Attack":
                    attack(character, opponent);
                    break;
                case "Defend":
                    defend(character);
                    break;
                case "Use Object":
                    useObject(currentPlayer, opponent, character);
                    break;
                default:
                    break;
            }
        }

        // Exécuter les actions pour l'adversaire
        for (Character character : opponent.getArmy().getCharacters()) {
            if (character.getHp() <= 0) {
                continue;
            }

            switch (character.getAction()) {
                case "Attack":
                    attack(character, currentPlayer);
                    break;
                case "Defend":
                    defend(character);
                    break;
                case "Use Object":
                    useObject(opponent, currentPlayer, character);
                    break;
                default:
                    break;
            }
        }

        // Vérifier si un joueur a gagné ce tour (en fonction de l'état des armées)
        if (opponent.getArmy().isDefeated()) {
            System.out.println(currentPlayer.getName() + " wins!");
        } else if (currentPlayer.getArmy().isDefeated()) {
            System.out.println(opponent.getName() + " wins!");
        } else {
            System.out.println("The round ended in a draw.");
        }
    }

    private void attack(Character attacker, Player opponent) {
        Random random = new Random();

        Character target = opponent.getArmy().getCharacters()
                .stream()
                .filter(c -> c.getHp() > 0)
                .skip(random
                        .nextInt((int) opponent.getArmy().getCharacters().stream().filter(c -> c.getHp() > 0).count()))
                .findFirst()
                .orElse(null);

        if (target != null) {
            System.out.println(attacker.getName() + " attacks " + target.getName() + "!");

            int damage = attacker.getAttack();

            if (target.isDefending()) {
                int finalDamage = Math.max(damage - target.getDefense(), 0);
                System.out.println(target.getName() + " is defending! Damage reduced to " + finalDamage);
                target.setHp(target.getHp() - finalDamage);
            } else {
                System.out.println(target.getName() + " receives " + damage + " damage.");
                target.setHp(target.getHp() - damage);
            }

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
