package Characters;
// This abstract class provides a base implementation for the Character interface.

public abstract class AbstractCharacter implements Character {
    private String name;
    private int health;
    private int attackPower;
    private boolean alive;
    private int level;
    private int experience;
    private int maxHealth;
    public String specialAbility;

    public AbstractCharacter(String name, int health, int attackPower, String specialAbility) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.alive = true;
        this.level = 1;
        this.experience = 0;
        this.maxHealth = health;
        this.specialAbility = specialAbility;
    }
    @Override public String getName() {
        return name;
    }
    @Override public int getHealth() {
        return health;
    }
    @Override public int getAttackPower() {
        return attackPower;
    }
    @Override public boolean isAlive() {
        return alive && health > 0;
    }
    @Override public int attack() {
        return attackPower;
    }
    @Override public int getLevel() {
    return level;
    }
    @Override public String getExperience() {
    return "You have "+experience+ " experience points. "+100 * level +" experience points are needed to level up.";
    }
    @Override public void setAlive(boolean alive) {
    this.alive = alive;
    }
    public String getSpecialAbility() {
        return specialAbility;
    }
    @Override public boolean specialAbilityUsed() {
        return true;
    }
    @Override
    public String toString() {
        return "Character: " + getName()
        +", Health: " + getHealth()
        +", Attack Power: " + getAttackPower() 
        +", Level: " + getLevel()
        +", Experience: " + getExperience()
        +"Special Ability: " + getSpecialAbility();
    }

    public abstract void useSpecialAbility(Character target);


    public void setHealth(int health) {
        this.health = health;
        if (this.health <= 0) {
            this.alive = false;
            this.health = 0; // Ensure health does not go below zero
        }
    }
    @Override public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        setHealth(health - damage);
    }
    @Override public void heal() {
        int randomHeal = (int) (Math.random() * 40) + 20; // Random heal between 20 and 40
        if (health + randomHeal > maxHealth) { // Assuming max health is 100
            randomHeal = 100 - health; // Heal only up to max health
            health = 100; // Set health to max
        }
        else health += randomHeal;
        System.out.println(name + " heals for " + randomHeal + " health.");
    }






    public void gainExperience(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Experience gain cannot be negative");
        }
        experience += amount;
        System.out.println(name + " has gained " + amount + " experience points.");
        // Check for level up
        if (experience >= 100 * level) { // Example condition for leveling up
            levelUp();
        }
    }

    public void levelUp() {
        level++;
        experience = 0; // Reset experience on level up
        attackPower += 5; // Increase attack power on level up
        maxHealth += 10; // Increase max health on level up
        health += 10; // Increase health on level up
        
        System.out.println(name + " has leveled up to level " + level + "!");
    }



}