package Characters; 

// This interface defines the methods that any character in the game must implement.

public interface Character {
    String getName();
    int getHealth();
    int getAttackPower();
    String getSpecialAbility();
    int getLevel();
    String getExperience();
    boolean specialAbilityUsed();
    boolean isAlive();
    int attack();

    void setAlive(boolean alive);
    void takeDamage(int damage);
    void heal();
    void levelUp();
    void gainExperience(int amount);
    void useSpecialAbility(Character target);
    void setSpecialAbilityUsed(boolean used);
    void setAttackPower(int attackPower);
}
