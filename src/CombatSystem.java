import java.util.Random;
import Characters.Character; 

public class CombatSystem {
    private Character player;
    private Character enemy;
    private Menu menu;
    int playerAction;

    public CombatSystem(Character player, Character enemy, Menu menu) {
        this.player = player;
        this.enemy = enemy;
        this.menu = menu;
    }

    public void startBattle() {
        System.out.println("A battle has started between " + player.getName() + " and " + enemy.getName());
        while (player.isAlive() && enemy.isAlive()) {
            playerTurn();
            if (enemy.isAlive()) {
                enemyTurn();
            }
        }
        if (player.isAlive()) {
            System.out.println(player.getName() + " has won the battle!");
            player.gainExperience(50);
            menu.displayCombatMenu();
        } else {
            System.out.println(enemy.getName() + " has won the battle!");
            System.out.println("Game Over.");
        }
    }


    private void playerTurn() {
        int damage =(int) (Math.random() * player.attack()) + 10; // Random damage

        System.out.println("Select an action:");
        System.out.println("1. Attack");
        System.out.println("2. Heal");
        System.out.println("3. Use Special Ability");
        playerAction = Menu.playerInput();

        if (playerAction == 1) {
            Menu.clearConsole();
            enemy.takeDamage(damage);
            System.out.println(player.getName() + " attacks " + enemy.getName() + " for " + damage + " damage.");
            System.out.println(enemy.getName() + "'s remaining health: " + enemy.getHealth());
        } else if (playerAction == 2) {
            player.heal();
            System.out.println(player.getName() + "'s current health: " + player.getHealth());
        } else if (playerAction == 3) {
            if (player.specialAbilityUsed() != true) {
                player.useSpecialAbility(enemy);
                System.out.println(player.getName() + " uses special ability: " + player.getSpecialAbility());
                // Mark the special ability as used
            } else {
                System.out.println(player.getName() + " has no special ability to use.");
            }
        } else {
            System.out.println("Invalid action. Please choose again.");
            playerTurn(); // Restart the player's turn if invalid action
            return;
        }
    }



    private void enemyTurn() {
        int damage =(int) (Math.random() * enemy.attack()) + 10; // Random damage
        player.takeDamage(damage);
        System.out.println();
        System.out.println(enemy.getName() + " attacks " + player.getName() + " for " + damage + " damage.");
        System.out.println(player.getName() + "'s remaining health: " + player.getHealth());
    }
}