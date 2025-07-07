import java.util.Random;

import Characters.ANSIColor;
import Characters.Character; 

public class CombatSystem {
    private Character player;
    private Character enemy;
    private Menu menu;
    private int turns = 0;
    private int enemyOriginalAttackPower; 
    private int playerOriginalAttackPower; 
    int playerAction;

    public CombatSystem(Character player, Character enemy, Menu menu) {
        this.player = player;
        this.enemy = enemy;
        this.menu = menu;
    }

    public void startBattle() {

        System.out.println("A battle has started between "
        +ANSIColor.BLUE+player.getName()
        +ANSIColor.RESET+" and "
        +ANSIColor.RED
        +enemy.getName()+ANSIColor.RESET);

        while (player.isAlive() && enemy.isAlive()) {
            playerTurn();
            if (enemy.isAlive()) {
                enemyTurn();
            }
        }
        // Check who won the battle
        if (player.isAlive()) {
            System.out.println(ANSIColor.BLUE+player.getName()
            +ANSIColor.GREEN+" has won the battle!"+ANSIColor.RESET);

            player.gainExperience(50);
            player.setSpecialAbilityUsed(false); // Reset special ability usage
            menu.displayCombatMenu();

        } else {
            System.out.println(ANSIColor.BLUE+enemy.getName()
            +ANSIColor.RED+" has won the battle!"
            +ANSIColor.RESET);
            System.out.println(ANSIColor.RED+"Game Over."+ANSIColor.RESET);
        }
    }


    private void playerTurn() {
        System.out.println(ANSIColor.YELLOW+"Select an action:"+ANSIColor.RESET);
        System.out.println(ANSIColor.YELLOW+"1. "+ANSIColor.RED+"Attack"+ANSIColor.RESET);
        System.out.println(ANSIColor.YELLOW+"2. "+ANSIColor.GREEN+"Heal"+ANSIColor.RESET);
        System.out.println(ANSIColor.YELLOW+"3. "+ANSIColor.RED+"Use Special Ability"+ANSIColor.RESET);

        playerAction = Menu.playerInput();
        int damage =(int) (Math.random() * player.attack()) + (+ 10 * player.getLevel()); // Random damage between 10 and attack power

        if (playerAction == 1) {

            Menu.clearConsole();
            enemy.takeDamage(damage);

            System.out.println(ANSIColor.BLUE+player.getName()
            +ANSIColor.RESET+" attacks "
            +ANSIColor.RED+enemy.getName()
            +ANSIColor.RESET+" for "
            +ANSIColor.RED+damage
            +ANSIColor.RESET+" damage.");
    
            System.out.println(ANSIColor.RED+enemy.getName()
            +ANSIColor.RESET+"'s remaining health: "
            +ANSIColor.GREEN+enemy.getHealth()+ANSIColor.RESET);

        } else if (playerAction == 2) {
            Menu.clearConsole();
            player.heal();

            System.out.println(ANSIColor.BLUE+player.getName()+
            ANSIColor.RESET+"'s current health: "+
            ANSIColor.GREEN+ player.getHealth()+ANSIColor.RESET);
        } else if (playerAction == 3) {
            Menu.clearConsole();

            if (!player.specialAbilityUsed()) {
                player.useSpecialAbility(enemy);
                // Mark the special ability as used
            } else {
                System.out.println(player.getName() + " has used their special ability.");
            }
            
        } else {
            System.out.println("Invalid action. Please choose again.");
            playerTurn(); // Restart the player's turn if invalid action
            return;
        }
    }



    private void enemyTurn() {
        if (player.specialAbilityUsed()) // Check if the player has used their special ability
            if ("Nullification".equals(player.getSpecialAbility())) {
                if (turns < 2) {
                    turns++;
                    enemyOriginalAttackPower = enemy.getAttackPower(); // Store original attack power
                    System.out.println(ANSIColor.RED+enemy.getName()
                    +ANSIColor.RESET+" is nullified and cannot attack this turn.");
                    return; // Skip the enemy's turn if nullified
                } else {
                    turns = 0; // Reset after 2 turns
                    System.out.println(ANSIColor.RED+enemy.getName()
                    +ANSIColor.RESET+" is no longer nullified.");
                    player.setSpecialAbilityUsed(false);
                }
            }
            else if ("Berserk".equals(player.getSpecialAbility())) { //LOGIC STILL NOT WORKING
                if (turns < 3) {
                    turns++;
                    playerOriginalAttackPower = player.getAttackPower(); // Store original attack power
                    player.setAttackPower(player.getAttackPower() + 20); // Increase attack power by 20
                } else {
                    turns = 0; // Reset after 2 turns
                    System.out.println(ANSIColor.BLUE+player.getName()
                    +ANSIColor.RESET+" is no longer berserk.");
                    player.setSpecialAbilityUsed(false);
                    player.setAttackPower(playerOriginalAttackPower); // Reset attack power
                }
        }
        
        int damage = (int) (Math.random() * enemy.attack()) + 10;
        player.takeDamage(damage);

        System.out.println();
        System.out.println(ANSIColor.RED+enemy.getName()
        +ANSIColor.RESET+" attacks "
        +ANSIColor.BLUE+player.getName()
        +ANSIColor.RESET+" for "
        +ANSIColor.RED+damage
        +ANSIColor.RESET+" damage.");

        System.out.println(ANSIColor.BLUE+player.getName()
        +ANSIColor.RESET+"'s remaining health: "
        +ANSIColor.GREEN+ player.getHealth()+ANSIColor.RESET);
        System.out.println();
    }
}