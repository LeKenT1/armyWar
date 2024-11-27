package src.game;

import src.objects.GameObject;
import src.characters.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameEngine {
    private Player player1;
    private Player player2;
    private Scanner scanner;
    private List<String> battleLog;

    private class Attack {
        Character attacker;
        Character target;

        Attack(Character attacker, Character target) {
            this.attacker = attacker;
            this.target = target;
        }
    }

    public GameEngine(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.scanner = new Scanner(System.in);
        this.battleLog = new ArrayList<>();
    }

    public void startGame() {
        System.out.println("Game started!\n");
        battleLog.clear();

        while (true) {
            if (isArmyFullyDead(player1) || isArmyFullyDead(player2)) {
                if (isArmyFullyDead(player1)) {
                    System.out.println(player2.getName() + " wins!");
                } else {
                    System.out.println(player1.getName() + " wins!");
                }
                break;
            }

            playTurn(player1, player2);
            displayBattleLog();
            resetDefenseStatus(player1);
            resetDefenseStatus(player2);

            if (isArmyFullyDead(player1) || isArmyFullyDead(player2)) {
                if (isArmyFullyDead(player1)) {
                    System.out.println(player2.getName() + " wins!");
                } else {
                    System.out.println(player1.getName() + " wins!");
                }
                break;
            }

            playTurn(player2, player1);
            displayBattleLog();
            resetDefenseStatus(player1);
            resetDefenseStatus(player2);
        }
    }

    private boolean isArmyFullyDead(Player player) {
        return player.getArmy().getCharacters().stream().allMatch(character -> character.getHp() <= 0);
    }

    private void displayBattleLog() {
        System.out.println("\n--- Battle Log ---");
        for (String logEntry : battleLog) {
            System.out.println(logEntry);
        }
        System.out.println("\n--- Current Army Status ---");
        displayArmyStatus(player1);
        displayArmyStatus(player2);
        System.out.println("--------------------\n");
        battleLog.clear();
    }

    private void displayArmyStatus(Player player) {
        System.out.println(player.getName() + "'s Army:");
        for (Character character : player.getArmy().getCharacters()) {
            if (character.getHp() <= 0) {
                System.out.println(character.getName() + ": DEAD");
            } else {
                System.out.println(character.getName() + ": " + character.getHp() + " HP");
            }
        }
    }

    private void playTurn(Player currentPlayer, Player opponent) {
        System.out.println(currentPlayer.getName() + "'s turn!\n");

        for (Character character : currentPlayer.getArmy().getCharacters()) {
            if (character.getHp() <= 0) {
                continue;
            }

            System.out.println(
                    "\nChoose an action for your " + character.getName() + ":\n1. Attack\n2. Defend");

            if (!currentPlayer.getArmy().getObjects().isEmpty()) {
                System.out.println("3. Use Object");
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    character.setAction("Attack");
                    break;
                case 2:
                    character.setAction("Defend");
                    break;
                case 3:
                    if (!currentPlayer.getArmy().getObjects().isEmpty()) {
                        character.setAction("Use Object");
                    } else {
                        System.out.println("Invalid choice, skipping turn...");
                    }
                    break;
                default:
                    System.out.println("Invalid choice, skipping turn...");
                    break;
            }
        }

        System.out.println(opponent.getName() + "'s turn!\n");

        for (Character character : opponent.getArmy().getCharacters()) {
            if (character.getHp() <= 0) {
                continue;
            }

            System.out.println(
                    "\nChoose an action for your " + character.getName() + ":\n1. Attack\n2. Defend");

            if (!currentPlayer.getArmy().getObjects().isEmpty()) {
                System.out.println("3. Use Object");
            }

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

        executeActions(currentPlayer, opponent);
    }

    private void executeActions(Player currentPlayer, Player opponent) {
        List<Attack> attacks = new ArrayList<>();
    
        for (Character character : currentPlayer.getArmy().getCharacters()) {
            if (character.getHp() > 0 && character.getAction().equals("Attack")) {
                Character target = selectRandomTarget(opponent);
                if (target != null) {
                    attacks.add(new Attack(character, target));
                }
            }
        }
    
        for (Character character : opponent.getArmy().getCharacters()) {
            if (character.getHp() > 0 && character.getAction().equals("Attack")) {
                Character target = selectRandomTarget(currentPlayer);
                if (target != null) {
                    attacks.add(new Attack(character, target));
                }
            }
        }
    
        for (Attack attack : attacks) {
            executeSimultaneousAttack(attack);
        }
    
        handleOtherActions(currentPlayer, opponent);
        handleOtherActions(opponent, currentPlayer);
    }

    private void handleOtherActions(Player actingPlayer, Player otherPlayer) {
        for (Character character : actingPlayer.getArmy().getCharacters()) {
            if (character.getHp() > 0) {
                switch (character.getAction()) {
                    case "Defend":
                        defend(character);
                        break;
                    case "Use Object":
                        useObject(actingPlayer, otherPlayer, character);
                        break;
                }
            }
        }
    }

    private Character selectRandomTarget(Player opponent) {
        return opponent.getArmy().getCharacters()
                .stream()
                .filter(c -> c.getHp() > 0)
                .findAny()
                .orElse(null);
    }

    private void executeSimultaneousAttack(Attack attack) {
        String attackLog = attack.attacker.getName() + " attacked " + attack.target.getName() + "!";
        battleLog.add(attackLog);

        int damage = attack.attacker.getAttack();

        if (attack.target.isDefending()) {
            int finalDamage = Math.max(damage - attack.target.getDefense(), 0);
            battleLog.add(attack.target.getName() + " is defending! Damage reduced to " + finalDamage);
            attack.target.setHp(attack.target.getHp() - finalDamage);
        } else {
            battleLog.add(attack.target.getName() + " receives " + damage + " damage.");
            attack.target.setHp(attack.target.getHp() - damage);
        }
    }

    private void defend(Character character) {
        battleLog.add(character.getName() + " is defending!");
        character.setDefending(true);
    }

    private void useObject(Player currentPlayer, Player opponent, Character user) {
        if (currentPlayer.getArmy().getObjects().isEmpty()) {
            battleLog.add("No objects available.");
            return;
        }

        Random random = new Random();
        GameObject object = currentPlayer.getArmy().getObjects()
                .get(random.nextInt(currentPlayer.getArmy().getObjects().size()));

        boolean isBeneficial = object.isBeneficial();
        List<Character> validTargets = isBeneficial
                ? currentPlayer.getArmy().getCharacters().stream().filter(c -> c.getHp() > 0).toList()
                : opponent.getArmy().getCharacters().stream().filter(c -> c.getHp() > 0).toList();

        if (validTargets.isEmpty()) {
            battleLog.add("No valid targets available for the object.");
            return;
        }

        Character target = validTargets.get(random.nextInt(validTargets.size()));

        battleLog.add(user.getName() + " uses " + object.getName() + " on " + target.getName());
        object.applyEffect(user, target);
    }

    private void resetDefenseStatus(Player player) {
        for (Character character : player.getArmy().getCharacters()) {
            character.setDefending(false);
        }
    }
}

class GameEngineTest {
    public static void main(String[] args) {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        GameEngine engine = new GameEngine(player1, player2);
        assert engine != null : "GameEngine not initialized correctly";
        System.out.println("GameEngine test passed!");
    }
}