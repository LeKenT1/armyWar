package src;

import src.characters.*;
import src.objects.*;
import src.armies.*;
import src.game.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Player 1, enter your name : ");
        String name1 = scanner.nextLine();
        Player player1 = new Player(name1);

        System.out.println("Player 2, enter your name : ");
        String name2 = scanner.nextLine();
        Player player2 = new Player(name2);

        player1.addCredits(20);
        player2.addCredits(20);

        System.out.println("\nCharacters : ");
        System.out.println("-------------------------------------------------------");
        System.out.printf("| %-5s | %-8s | %-4s | %-5s | %-5s | %-5s |\n", "Choice", "Name", "HP", "Attack",
                "Defense", "Cost");
        System.out.println("-------------------------------------------------------");

        Warrior warrior = new Warrior();
        Archer archer = new Archer();
        Troll troll = new Troll();
        Wizard wizard = new Wizard();
        Dragon dragon = new Dragon();

        System.out.printf("| %-6s | \033[31m%-8s\033[0m | %-4s | %-6s | %-6s | %-6s | \n",
                "1", warrior.getName(), warrior.getHp(), warrior.getAttack(), warrior.getDefense(), warrior.getCost());

        System.out.printf("| %-6s | \033[33m%-8s\033[0m | %-4s | %-6s | %-6s | %-6s | \n",
                "2", archer.getName(), archer.getHp(), archer.getAttack(), archer.getDefense(), archer.getCost());

        System.out.printf("| %-6s | \033[34m%-8s\033[0m | %-4s | %-6s | %-6s | %-6s | \n",
                "3", wizard.getName(), wizard.getHp(), wizard.getAttack(), wizard.getDefense(), wizard.getCost());

        System.out.printf("| %-6s | \033[32m%-8s\033[0m | %-4s | %-6s | %-6s | %-6s | \n",
                "4", troll.getName(), troll.getHp(), troll.getAttack(), troll.getDefense(), troll.getCost());

        System.out.printf("| %-6s | \033[35m%-8s\033[0m | %-4s | %-6s | %-6s | %-6s | \n",
                "5", dragon.getName(), dragon.getHp(), dragon.getAttack(), dragon.getDefense(), dragon.getCost());

        System.out.println("------------------------------------------------------");

        System.out.println(player1.getName() + ", choose your characters : ");
        while (player1.getCredits() > 3) {
            System.out.println("You have " + player1.getCredits() + " credits left, choose a character (1-5): ");

            int choice = -1;
            while (choice < 1 || choice > 5) {
                try {
                    choice = scanner.nextInt();
                    if (choice < 1 || choice > 5) {
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number.");
                    scanner.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    player1.chooseCharacter(new Warrior());
                    break;
                case 2:
                    player1.chooseCharacter(new Archer());
                    break;
                case 3:
                    player1.chooseCharacter(new Wizard());
                    break;
                case 4:
                    player1.chooseCharacter(new Troll());
                    break;
                case 5:
                    player1.chooseCharacter(new Dragon());
                    break;
                default:
                    break;
            }
        }

        System.out.println(player2.getName() + ", choose your characters : ");
        while (player2.getCredits() > 3) {
            System.out.println("You have " + player2.getCredits() + " credits left, choose a character (1-5): ");

            int choice = -1;
            while (choice < 1 || choice > 5) {
                try {
                    choice = scanner.nextInt();
                    if (choice < 1 || choice > 5) {
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number.");
                    scanner.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    player2.chooseCharacter(new Warrior());
                    break;
                case 2:
                    player2.chooseCharacter(new Archer());
                    break;
                case 3:
                    player2.chooseCharacter(new Wizard());
                    break;
                case 4:
                    player2.chooseCharacter(new Troll());
                    break;
                case 5:
                    player2.chooseCharacter(new Dragon());
                    break;
                default:
                    break;
            }
        }

        HealingPotion healingPotion = new HealingPotion(20);
        Bomb bomb = new Bomb(20);

        System.out.println(player1.getName() + ", choose your objects (maximum 2 objects) : ");
        player1.getArmy().chooseObject(healingPotion);
        player1.getArmy().chooseObject(bomb);

        player1.getArmy().chooseObject(bomb);
        player2.getArmy().chooseObject(healingPotion);

        GameEngine gameEngine = new GameEngine(player1, player2);

        gameEngine.startGame();
    }
}

class MainTest {
    public static void main(String[] args) {
        Player testPlayer1 = new Player("TestPlayer1");
        Player testPlayer2 = new Player("TestPlayer2");
        
        testPlayer1.addCredits(20);
        testPlayer2.addCredits(20);
        
        assert testPlayer1.getCredits() == 20 : "Credits not added correctly for player 1";
        assert testPlayer2.getCredits() == 20 : "Credits not added correctly for player 2";
        
        testPlayer1.chooseCharacter(new Warrior());
        testPlayer2.chooseCharacter(new Archer());
        
        assert testPlayer1.getArmy().getCharacters().size() == 1 : "Character not added to player 1's army";
        assert testPlayer2.getArmy().getCharacters().size() == 1 : "Character not added to player 2's army";
        
        HealingPotion healingPotion = new HealingPotion(20);
        Bomb bomb = new Bomb(20);
        
        testPlayer1.getArmy().chooseObject(healingPotion);
        testPlayer2.getArmy().chooseObject(bomb);
        
        assert testPlayer1.getArmy().getObjects().size() == 1 : "Object not added to player 1's army";
        assert testPlayer2.getArmy().getObjects().size() == 1 : "Object not added to player 2's army";
        
        GameEngine testGameEngine = new GameEngine(testPlayer1, testPlayer2);
        assert testGameEngine != null : "GameEngine not initialized correctly";
        
        System.out.println("All Main tests passed!");
    }
}

