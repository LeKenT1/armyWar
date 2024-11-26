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

        System.out.println("Entrez le nom du joueur 1 : ");
        String name1 = scanner.nextLine();
        Player player1 = new Player(name1);

        System.out.println("Entrez le nom du joueur 2 : ");
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
                "1", warrior.getName(), warrior.getCost(), warrior.getHp(), warrior.getAttack(), warrior.getDefense());

        System.out.printf("| %-6s | \033[33m%-8s\033[0m | %-4s | %-6s | %-6s | %-6s | \n",
                "2", archer.getName(), archer.getCost(), archer.getHp(), archer.getAttack(), archer.getDefense());

        System.out.printf("| %-6s | \033[34m%-8s\033[0m | %-4s | %-6s | %-6s | %-6s | \n",
                "3", wizard.getName(), wizard.getCost(), wizard.getHp(), wizard.getAttack(), wizard.getDefense());

        System.out.printf("| %-6s | \033[32m%-8s\033[0m | %-4s | %-6s | %-6s | %-6s | \n",
                "4", troll.getName(), troll.getCost(), troll.getHp(), troll.getAttack(), troll.getDefense());

        System.out.printf("| %-6s | \033[35m%-8s\033[0m | %-4s | %-6s | %-6s | %-6s | \n",
                "5", dragon.getName(), dragon.getCost(), dragon.getHp(), dragon.getAttack(), dragon.getDefense());

        System.out.println("------------------------------------------------------");

        // Sélection des personnages pour Joueur 1
        System.out.println(player1.getName() + ", choisissez vos personnages : ");
        while (player1.getCredits() > 0) {
            System.out.println("il vous reste " + player1.getCredits() + ", choisissez un personnage (1-4): ");

            int choice = -1;
            while (choice < 1 || choice > 4) {
                try {
                    choice = scanner.nextInt();
                    if (choice < 1 || choice > 4) {
                        System.out.println("Choix invalide. Tapez un chiffre entre 1 et 4.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Veuillez entrer un nombre.");
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
                default:
                    break;
            }
        }

        // Sélection des personnages pour Joueur 2
        System.out.println(player2.getName() + ", choisissez vos personnages (maximum 2 de chaque type) : ");
        for (int i = 0; i < 2; i++) {
            System.out.println("Choisissez un personnage (1-4): ");
            int choice = scanner.nextInt();
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
                default:
                    System.out.println("Choix invalide.");
                    i--; // Pour que le joueur refasse un choix valide
                    break;
            }
        }

        // Sélection d'objets pour Joueur 1
        HealingPotion healingPotion = new HealingPotion(20);
        Bomb bomb = new Bomb(20);

        System.out.println(player1.getName() + ", choisissez vos objets (maximum 2 objets) : ");
        player1.chooseObject(healingPotion);
        player1.chooseObject(bomb);

        // Sélection d'objets pour Joueur 2
        SpiritSummon fireSpirit = new SpiritSummon("Fire");
        player2.chooseObject(fireSpirit);
        player2.chooseObject(healingPotion);

        // Création du moteur de jeu
        GameEngine gameEngine = new GameEngine(player1, player2);

        // Démarrer le combat
        gameEngine.startGame();
    }
}
