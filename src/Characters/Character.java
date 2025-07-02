package Characters;
// This interface defines the methods that any character in the game must implement.
public interface Character {
    String getName();
    int getHealth();
    int getAttackPower();
    String getSpecialAbility();
    boolean specialAbilityUsed();
    boolean isAlive();
    int attack();

    void setAlive(boolean alive);
    void takeDamage(int damage);
    void heal();
    void levelUp();
    String getLevel();
    String getExperience();
    void gainExperience(int amount);
    void useSpecialAbility(Character target);
}