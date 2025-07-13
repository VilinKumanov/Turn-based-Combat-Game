package Characters;

public abstract class AbstractCharacter {
    private String name;
    private int health;
    private int maxHealth;
    private int level;
    private int experience;
    private boolean alive;
    protected int attackPower;
    protected String specialAbility;
    private boolean specialAbilityUsed;

    public AbstractCharacter(String name, int health, int attackPower, String specialAbility) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.attackPower = attackPower;
        this.specialAbility = specialAbility;
        this.level = 1;
        this.experience = 0;
        this.alive = true;
        this.specialAbilityUsed = false;
    }

    // === Getters ===
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public boolean isAlive() {
        return alive && health > 0;
    }

    public int getLevel() {
        return level;
    }

    public String getExperience() {
        return "You have " + ANSIColor.PURPLE + experience + ANSIColor.RESET + " experience points. "
             + ANSIColor.PURPLE + getExperienceToLevelUp() + ANSIColor.RESET + " are needed to level up.";
    }

    public String getSpecialAbility() {
        return specialAbility;
    }

    public boolean specialAbilityUsed() {
        return specialAbilityUsed;
    }

    // === Setters ===
    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(health, maxHealth));
        if (this.health <= 0) alive = false;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void setSpecialAbilityUsed(boolean used) {
        this.specialAbilityUsed = used;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // === Combat Methods ===
    public int attack() {
        return attackPower;
    }

    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        setHealth(this.health - damage);
    }

    public int heal() {
        int healAmount = (int) (Math.random() * 40) + 20;
        int actualHealed = Math.min(healAmount, maxHealth - health);
        health += actualHealed;

        System.out.println(ANSIColor.BLUE + name + ANSIColor.RESET + " heals for " +
                           ANSIColor.GREEN + actualHealed + ANSIColor.RESET + " health.");
        return actualHealed;
    }

    public void gainExperience(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Experience gain cannot be negative");
        }
        experience += amount;

        System.out.println(ANSIColor.BLUE + name + ANSIColor.RESET + " has gained " +
                           ANSIColor.PURPLE + amount + ANSIColor.RESET + " experience points.");

        if (experience >= getExperienceToLevelUp()) {
            levelUp();
        }
    }

    protected int getExperienceToLevelUp() {
        return 100 * level;
    }

    protected void levelUp() {
        level++;
        experience = 0;
        attackPower += 5;
        maxHealth += 20;
        health = maxHealth; // Restore health to max on level up

        System.out.println(ANSIColor.BLUE + name + ANSIColor.RESET + " has leveled up to level " +
                           ANSIColor.PURPLE + level + ANSIColor.RESET + "!");
    }

    @Override
    public String toString() {
        return "Character: " + ANSIColor.BLUE + name + ANSIColor.RESET +
               ", Health: " + ANSIColor.GREEN + health + "/" + maxHealth + ANSIColor.RESET +
               ", Attack Power: " + ANSIColor.RED + attackPower + ANSIColor.RESET +
               ", Special Ability: " + ANSIColor.RED + specialAbility + ANSIColor.RESET;
    }

    // === Abstract Method ===
    public abstract void useSpecialAbility(AbstractCharacter target);
}
