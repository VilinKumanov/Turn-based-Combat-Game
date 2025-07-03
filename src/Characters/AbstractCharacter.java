package Characters;
// This abstract class provides a base implementation for the Character interface.

public abstract class AbstractCharacter implements Character {
    private String name;
    private int health;
    protected int attackPower;
    private boolean alive;
    private int level;
    private int experience;
    private int maxHealth;
    protected String specialAbility;
    public boolean specialAbilityUsed;

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
    return "You have "
    +ANSIColor.PURPLE+experience
    +ANSIColor.RESET+" experience points. "
    +ANSIColor.PURPLE+100 * level 
    +ANSIColor.RESET+" experience points are needed to level up.";
    }
    @Override public void setAlive(boolean alive) {
    this.alive = alive;
    }
    @Override public String getSpecialAbility() {
        return specialAbility;
    }

    @Override
    public String toString() {
        return "Character: "
        +ANSIColor.BLUE+getName()
        +ANSIColor.RESET+", Health: "
        +ANSIColor.GREEN+getHealth()
        +ANSIColor.RESET+", Attack Power: "
        +ANSIColor.RED+getAttackPower() 
        +ANSIColor.RESET+" Special Ability: "
        +ANSIColor.RED+getSpecialAbility()
        +ANSIColor.RESET;
    }

    public void setSpecialAbilityUsed(boolean used) {
        this.specialAbilityUsed = used;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public abstract void useSpecialAbility(Character target);

    public boolean specialAbilityUsed() {
        return specialAbilityUsed;
    }

    @Override public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        setHealth(health - damage);
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health <= 0) {
            this.alive = false;
            this.health = 0; // Ensure health does not go below zero
        }
    }

    @Override public void heal() {
        int randomHeal = (int) (Math.random() * 40) + 20; // Random heal between 20 and 40
        if (health + randomHeal > maxHealth) { // Assuming max health is 100
            randomHeal = maxHealth - health; // Heal only up to max health
            health = maxHealth; // Set health to max
        }
        else health += randomHeal;
        System.out.println(ANSIColor.BLUE+name
        +ANSIColor.RESET+" heals for "
        +ANSIColor.GREEN+randomHeal
        +ANSIColor.RESET+" health.");
    }






    public void gainExperience(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Experience gain cannot be negative");
        }
        experience += amount;
        System.out.println(ANSIColor.BLUE+name
        +ANSIColor.RESET+" has gained "
        +ANSIColor.PURPLE+amount
        +ANSIColor.RESET+" experience points.");
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
        
        System.out.println(ANSIColor.BLUE+name
        +ANSIColor.RESET+" has leveled up to level "
        +ANSIColor.PURPLE+level
        +ANSIColor.RESET+"!");
    }



}