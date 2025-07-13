import java.util.Random;
import Characters.ANSIColor;
import Characters.AbstractCharacter;

public class CombatSystem {
    private final AbstractCharacter player;
    private final AbstractCharacter enemy;
    private final Menu menu;
    private int turns = 0;
    private int originalEnemyAttackPower;
    private int originalPlayerAttackPower;
    private boolean enemySpecialAbilityUsed = false;

    public CombatSystem(AbstractCharacter player, AbstractCharacter enemy, Menu menu) {
        this.player = player;
        this.enemy = enemy;
        this.menu = menu;
    }

    public void startBattle() {
        enemySpecialAbilityUsed = false;
        System.out.println("A battle has started between " +
                ANSIColor.BLUE + player.getName() + ANSIColor.RESET + " and " +
                ANSIColor.RED + enemy.getName() + ANSIColor.RESET);

        while (player.isAlive() && enemy.isAlive()) {
            playerTurn();
            if (enemy.isAlive()) {
                enemyTurn();
            }
        }

        // Battle result
        if (player.isAlive()) {
            System.out.println(ANSIColor.BLUE + player.getName() +
                    ANSIColor.GREEN + " has won the battle!" + ANSIColor.RESET);
            player.gainExperience(50);
            player.setSpecialAbilityUsed(false);
            menu.displayCombatMenu();
        } else {
            System.out.println(ANSIColor.BLUE + enemy.getName() +
                    ANSIColor.RED + " has won the battle!" + ANSIColor.RESET);
            System.out.println(ANSIColor.RED + "Game Over." + ANSIColor.RESET);
        }
    }

    private void playerTurn() {
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println(ANSIColor.YELLOW + "Select an action:" + ANSIColor.RESET);
            System.out.println("1. " + ANSIColor.RED + "Attack" + ANSIColor.RESET);
            System.out.println("2. " + ANSIColor.GREEN + "Heal" + ANSIColor.RESET);
            System.out.println("3. " + ANSIColor.RED + "Use Special Ability" + ANSIColor.RESET);

            int action = Menu.playerInput();
            Menu.clearConsole();

            switch (action) {
                case 1 -> {
                    int damage = (int) (Math.random() * player.attack()) + (10 * player.getLevel());
                    enemy.takeDamage(damage);
                    printAttackStatus(player, enemy, damage);
                    validChoice = true;
                }
                case 2 -> {
                    player.heal();
                    System.out.println(ANSIColor.BLUE + player.getName() +
                            ANSIColor.RESET + "'s current health: " +
                            ANSIColor.GREEN + player.getHealth() + ANSIColor.RESET);
                    validChoice = true;
                }
                case 3 -> {
                    if (!player.specialAbilityUsed()) {
                        player.useSpecialAbility(enemy);
                        validChoice = true;
                    } else {
                        System.out.println(player.getName() + " has already used their special ability.");
                    }
                }
                default -> System.out.println("Invalid action. Please choose again.");
            }
        }
    }

    private void enemyTurn() {
        if (handleSpecialAbilities()) {
        return;
    }

    // Randomly trigger special ability if not used yet
    if (!enemySpecialAbilityUsed && Math.random() < 0.25) { // 25% chance per turn
        enemy.useSpecialAbility(player);
        enemySpecialAbilityUsed = true;
        System.out.println(player.getName() + "'s remaining health: " +
                ANSIColor.GREEN + player.getHealth() + ANSIColor.RESET);
        return; // Enemy uses special ability instead of attacking this turn
    }

        int damage = (int) (Math.random() * enemy.attack()) + 10;
        player.takeDamage(damage);
        printAttackStatus(enemy, player, damage);
    }

    private boolean handleSpecialAbilities() {
        if (!player.specialAbilityUsed()) return false;

        String ability = player.getSpecialAbility();

        switch (ability) {
            case "Nullification" -> {
                if (turns < 2) {
                    System.out.println(ANSIColor.RED + enemy.getName() + ANSIColor.RESET +
                            " is nullified and cannot attack this turn.");
                    turns++;
                    return true;
                } else {
                    turns = 0;
                    System.out.println(ANSIColor.RED + enemy.getName() + ANSIColor.RESET +
                            " is no longer nullified.");
                    player.setSpecialAbilityUsed(false);
                }
            }

            case "Berserk" -> {
                if (turns == 0) {
                    originalPlayerAttackPower = player.getAttackPower();
                    player.setAttackPower(originalPlayerAttackPower + 20);
                }

                if (turns < 3) {
                    turns++;
                } else {
                    System.out.println(ANSIColor.BLUE + player.getName() + ANSIColor.RESET +
                            " is no longer berserk.");
                    player.setAttackPower(originalPlayerAttackPower);
                    player.setSpecialAbilityUsed(false);
                    turns = 0;
                }
            }
        }
        return false;
    }

    private void printAttackStatus(AbstractCharacter attacker, AbstractCharacter target, int damage) {
        System.out.println(ANSIColor.BLUE + attacker.getName() + ANSIColor.RESET + " attacks " + ANSIColor.RED + target.getName() + ANSIColor.RESET +
                " for " + ANSIColor.RED + damage + ANSIColor.RESET + " damage.");
        System.out.println(target.getName() + "'s remaining health: " +
                ANSIColor.GREEN + target.getHealth() + ANSIColor.RESET);
    }
}
