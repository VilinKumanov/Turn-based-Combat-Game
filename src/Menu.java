import java.util.Scanner;
import Characters.Character;
import Characters.PlayerCharacters.Mage;
import Characters.PlayerCharacters.Warmonger;
import Characters.EnemyCharacters.Goblin;
import Characters.EnemyCharacters.Orc;
import Characters.EnemyCharacters.Dragon;
import java.util.Random;
import java.util.ArrayList;


public class Menu{
    int choice;
    Character selectedCharacter;

    public static void clearConsole() {  // Clear the console by printing empty lines
    for (int i = 0; i < 50; ++i) System.out.println(); 
    }

    public static int playerInput() {  // Method to handle player input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    // Player characters
    Mage mage = new Mage("Gardjoka");
    Warmonger warmonger = new Warmonger("Glavniq");
        
    // Enemy characters
    Goblin goblin = new Goblin();
    Orc orc = new Orc();
    Dragon dragon = new Dragon();

    Random random = new Random();
    // Array of enemy characters for combat
    ArrayList<Character> enemyCharacters = new ArrayList<>(); // Array of enemy characters
    {
    enemyCharacters.add(goblin);
    enemyCharacters.add(orc);
    enemyCharacters.add(dragon);
    }




    public void startMenu() {
        clearConsole();
        System.out.println("Welcome to the Game!");
        characterSelectMenu();
    }

    public void characterSelectMenu() {
        System.out.println("Choose your character:");
        System.out.println("1."+ mage.toString());
        System.out.println("2."+ warmonger.toString());
        choice = playerInput();

        if (choice == 1) {
            selectedCharacter = mage;
            clearConsole();
            System.out.println("You selected: " + selectedCharacter.getName());
        } else if (choice == 2) {
            selectedCharacter = warmonger;
            clearConsole();
            System.out.println("You selected: " + selectedCharacter.getName());
        } else {
            System.out.println("Invalid choice.");
            characterSelectMenu(); // Restart the menu if invalid choice
            return;
        }
        displayCombatMenu(); // Show combat menu after character selection
    }


    public void displayCombatMenu() {
        System.out.println("Combat Menu:");
        System.out.println("1. Start Battle");
        System.out.println("2. View Inventory");
        System.out.println("3. View Character Stats");
        System.out.println("4. Back to Main Menu");
        System.out.print("Please select an option: ");
        int combatChoice = playerInput();

        // Handle combat menu choices
        if(combatChoice == 1) {
            clearConsole();
            System.out.println("Starting battle...");
            Character enemyCharacter = enemyCharacters.get(random.nextInt(enemyCharacters.size())); // Randomly select an enemy character
            enemyCharacters.remove(enemyCharacter); // Remove the selected enemy from the array to avoid repetition in future battles
            if (enemyCharacters.isEmpty()) {
                System.out.println("You have defeated all enemies!");
                return; // Exit if no enemies left
            }
            CombatSystem combatSystem = new CombatSystem(selectedCharacter, enemyCharacter, this); // Start combat with the selected character and a random enemy
            System.out.println("You are facing: " + enemyCharacter.getName());
            combatSystem.startBattle(); // Start the battle
        } else if(combatChoice == 2) {
            clearConsole();
            System.out.println("Inventory is empty.");
            displayCombatMenu(); // Go back to the combat menu
        } else if (combatChoice == 3) {
            clearConsole();
            selectedCharacter.toString();
            
            displayCombatMenu(); // Go back to the combat menu
        } else if(combatChoice == 4) {
            startMenu(); // Go back to the main menu
        } else {
            clearConsole();
            System.out.println("Invalid choice. Please try again.");
            displayCombatMenu(); // Restart the combat menu
        }
    }
}