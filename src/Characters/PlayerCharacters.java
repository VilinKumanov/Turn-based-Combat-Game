package Characters;
// Different characters for the combat system that the player can choose from

public class PlayerCharacters {
        
        public static class Mage extends AbstractCharacter {
            public Mage(String name) {
                super(name, 100, 30, "Nullification");
            }

            @Override
            public void useSpecialAbility(Character target) {
            // Nullify enemy's next attack
            System.out.println(getName() + " uses Nullification! " + target.getName() + " loses their next turn.");
            // You can add logic to set a flag on the target, etc.
            
            }
            
        }

        public static class Warmonger extends AbstractCharacter {
            public Warmonger(String name) {
                super(name, 50, 50, "Berserk");
            }

            @Override
            public void useSpecialAbility(Character target) {
                // Increase attack power for the next turn
                System.out.println(getName() + " uses Berserk! Attack power increased for the next turn.");
            }
        }

}
