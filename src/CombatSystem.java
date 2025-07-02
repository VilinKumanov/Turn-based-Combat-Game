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
        System.out.println("A battle has started between "+
        ANSIColor.BLUE+player.getName()+
        ANSIColor.RESET+" and "+
        ANSIColor.RED+
        enemy.getName()+ANSIColor.RESET);
        while (player.isAlive() && enemy.isAlive()) {
            playerTurn();
            if (enemy.isAlive()) {
                enemyTurn();
            }
        }
        if (player.isAlive()) {
            System.out.println(ANSIColor.BLUE + player.getName()
            +ANSIColor.GREEN+" has won the battle!"
            +ANSIColor.RESET);
            player.gainExperience(50);
            menu.displayCombatMenu();
        } else {
            System.out.println(ANSIColor.BLUE + enemy.getName()
            +ANSIColor.RED+" has won the battle!"
            +ANSIColor.RESET);
            System.out.println(ANSIColor.RED+"Game Over."
            +ANSIColor.RESET);
        }
    }


    private void playerTurn() {
        int damage =(int) (Math.random() * player.attack()) + 10; // Random damage

        System.out.println(ANSIColor.YELLOW+"Select an action:"+ANSIColor.RESET);
        System.out.println(ANSIColor.YELLOW+"1. "+ANSIColor.RED+"Attack"+ANSIColor.RESET);
        System.out.println(ANSIColor.YELLOW+"2. "+ANSIColor.GREEN+"Heal"+ANSIColor.RESET);
        System.out.println(ANSIColor.YELLOW+"3. "+ANSIColor.RED+"Use Special Ability"+ANSIColor.RESET);
        playerAction = Menu.playerInput();

        if (playerAction == 1) {
            Menu.clearConsole();
            enemy.takeDamage(damage);
            System.out.println(ANSIColor.BLUE+player.getName()+
            ANSIColor.RESET+" attacks "+
            ANSIColor.RED+enemy.getName()+
            ANSIColor.RESET+" for "+
            ANSIColor.RED+damage+
            ANSIColor.RESET+" damage.");
    
            System.out.println(ANSIColor.RED+enemy.getName()+
            ANSIColor.RESET+"'s remaining health: "+
            ANSIColor.GREEN+enemy.getHealth()+ANSIColor.RESET);
        } else if (playerAction == 2) {
            Menu.clearConsole();
            player.heal();
            System.out.println(ANSIColor.BLUE+player.getName()+
            ANSIColor.RESET+"'s current health: "+
            ANSIColor.GREEN+ player.getHealth()+ANSIColor.RESET);
        } else if (playerAction == 3) {
            Menu.clearConsole();
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
        int damage =(int) (Math.random() * enemy.attack()) + 5; // Random damage
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