package Characters;

// Different characters for the combat system that the player can choose from

public class PlayerCharacters {
        
        public static class Mage extends AbstractCharacter {
            public Mage(String name) {
                super(name, 100, 30, "Nullification");
            }

            @Override
            public void useSpecialAbility(AbstractCharacter target) {
            setSpecialAbilityUsed(true);
            // Nullify enemy's next attack
            System.out.println(ANSIColor.BLUE+getName()
            +ANSIColor.RESET+" uses Nullification! "
            +ANSIColor.RED+target.getName()
            +ANSIColor.RESET+"'s next 2 attack are nullified.");
            }
            
        }

        public static class Warmonger extends AbstractCharacter {
            public Warmonger(String name) {
                super(name, 50, 50, "Berserk");
            }

            @Override
            public void useSpecialAbility(AbstractCharacter target) {
                setSpecialAbilityUsed(true);
                // Increase attack power for the next turn
                System.out.println(ANSIColor.BLUE+getName()+
                ANSIColor.RESET+" uses "
                +ANSIColor.RED+specialAbility
                +ANSIColor.RESET+" Attack power increased for the next 2 turns.");
                setAttackPower(attackPower + 20); // Increase attack power by 20
            }
        }

}
