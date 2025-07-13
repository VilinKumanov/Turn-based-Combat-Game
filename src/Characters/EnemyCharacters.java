package Characters;

// Different enemies for the combat system that will be encountered

public class EnemyCharacters {

    public static class Goblin extends AbstractCharacter {

        public Goblin() {
            super("Goblin", 80, 25, "Nocturnal Ambush");
        }
        @Override
        public void useSpecialAbility(AbstractCharacter target) {
            // Goblin's special ability: Ambush attack
            int ambushDamage = (int) (Math.random() * 40) + 20; // Random damage between 20 and 40
            target.takeDamage(ambushDamage);
            System.out.println(ANSIColor.RED + getName() + ANSIColor.RESET +
                               " uses Nocturnal Ambush! " +
                               ANSIColor.GREEN + target.getName() + ANSIColor.RESET +
                               " takes " + ANSIColor.RED + ambushDamage + ANSIColor.RESET + " damage.");
        }
    }
    public static class Orc extends AbstractCharacter {
        public Orc() {
            super("Orc", 120, 30, "Brute Force");
        }
        @Override
        public void useSpecialAbility(AbstractCharacter target) {
            // Orc's special ability: Increase attack power
            int originalAttackPower = getAttackPower();
            setAttackPower(originalAttackPower + 10); // Increase attack power by 10
            System.out.println(ANSIColor.RED + getName() + ANSIColor.RESET +
                               " uses Brute Force! Attack power increased to " +
                               ANSIColor.RED + getAttackPower() + ANSIColor.RESET + ".");
        }
    }

    public static class Dragon extends AbstractCharacter {
        public Dragon() {
            super("Dragon", 200, 15, "Fire Breath");
        }
        @Override
        public void useSpecialAbility(AbstractCharacter target) {
            // Dragon's special ability: Fire Breath attack
            int fireBreathDamage = (int) (Math.random() * 40) + 25; // Random damage between 25 and 40
            target.takeDamage(fireBreathDamage);
            System.out.println(ANSIColor.RED + getName() + ANSIColor.RESET +
                               " uses Fire Breath! " +
                               ANSIColor.GREEN + target.getName() + ANSIColor.RESET +
                               " takes " + ANSIColor.RED + fireBreathDamage + ANSIColor.RESET + " damage.");
        }
    }

}