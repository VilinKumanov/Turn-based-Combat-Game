// Different enemies for the combat system that will be encountered
package Characters;


public class EnemyCharacters {

    public static class Goblin extends AbstractCharacter {

        public Goblin() {
            super("Goblin", 80, 25, "Nocturnal Ambush");
        }
        @Override
        public void useSpecialAbility(Character target) {
        }
    }
    public static class Orc extends AbstractCharacter {
        public Orc() {
            super("Orc", 120, 30, "Brute Force");
        }
        @Override
        public void useSpecialAbility(Character target) {
        }
    }

    public static class Dragon extends AbstractCharacter {
        public Dragon() {
            super("Dragon", 200, 15, "Fire Breath");
        }
        @Override
        public void useSpecialAbility(Character target) {
        }
    }

}