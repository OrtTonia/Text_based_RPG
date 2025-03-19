import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Battle {

    /**
     * Simulates a battle between two characters.
     *
     * @param attacker The first character attacking.
     * @param opponent The second character defending.
     * @return false if attacker wins, true if opponent wins.
     */
    boolean duel(Character attacker, Character opponent) {
        resetCharacterStats(attacker);
        resetCharacterStats(opponent);

        System.out.println("\nKampf beginnt zwischen: " + attacker.getName() + " und " + opponent.getName());
        System.out.println(attacker);
        System.out.println(opponent);

        int round = 1;
        while (attacker.getHealth() > 0 && opponent.getHealth() > 0) {
            System.out.println("\nRunde " + round++ + ":");

            attacker.attack(opponent);
            // Check if opponent is dead
            if (!opponent.isAlive()) {
                declareWinner(attacker, opponent);
                return false;
            }

            opponent.attack(attacker);
            // Check if attacker is dead
            if (!attacker.isAlive()) {
                declareWinner(opponent, attacker);
                return true;
            }
        }
        return false;
    }


    /**
     * Generates a random character (Magier, Krieger, or Rogue).
     *
     * @return A randomly generated character
     */
    Character randomCharacter(int level) {
        Random rand = new Random();
        String[] names = {
                "Merlin", "Cerridwen", "Asa", "Sabrina", "Zinta", "Roxanne", "David", "Alana",
                "Armand", "Einar", "Gregor", "Jari", "Siglinde", "Gloria", "Siegfried", "Xena",
                "Olivier", "Igor", "Edith", "Hel", "Angelina", "Marco", "Alexander", "Gertrude"};

        // Select a random name
        String name = names[rand.nextInt(names.length)];

        return switch (rand.nextInt(3)) {
            case 0 -> new Magier(name, randomXp(level));
            case 1 -> new Krieger(name, randomXp(level));
            case 2 -> new Spaeher(name, randomXp(level));
            default -> throw new IllegalStateException("Fehler bei der Charaktererstellung");
        };
    }


    // Generates a random Xp based on level
    private int randomXp(int level) {
        return new Random().nextInt(Math.max(level - 2, 0) * 10, (level + 3) * 10);

    }

    /**
     * Declares the winner and awards XP.
     *
     * @param winner The winning character.
     * @param loser  TThe defeated opponent
     */
    private void declareWinner(Character winner, Character loser) {
        System.out.println("\nDas Kampf ist vorbei!");
        int xpIncrease = Math.max(1 + (loser.getXp() - winner.getLevel() * 10) / 3, 1);
        System.out.println(winner.getClass().getSimpleName() + " " +
                winner.getName() + " hat den Kampf gewonnen!");
        winner.increaseXp(xpIncrease);
        System.out.println();
        System.out.println(winner.toSmallString());
        System.out.println(loser.toSmallString());
    }

    // Resets character stats such as health and mana/endurance.
    private void resetCharacterStats(Character character) {
        character.setHealth(character.getMaxHealth());
        if (character instanceof Magier magier)
            magier.setMana(magier.getMaxMana());
        if (character instanceof Spaeher rogue)
            rogue.setEndurance(rogue.getMaxEndurance());
    }

    /**
     * Conducts a tournament with an array of fighters, returning the wins.
     *
     * @param fighters List of characters participating in the tournament.
     * @return An array containing the number of wins for each character.
     */
    int[] trainingTournament(Character[] fighters) {
        // Simulate battles between all pairs of fighters

        int[] wins = new int[fighters.length];
        for (int i = 0; i < fighters.length; i++) {
            for (int j = 0; j < fighters.length; j++) {
                if (i == j) continue; // Skip if fighting yourself
                System.out.println("\n####################################");
                resetCharacterStats(fighters[i]);
                resetCharacterStats(fighters[j]);

                // Simulate battle and update win count accordingly
                if (duel(fighters[i], fighters[j])) {
                    wins[j]++;
                } else {
                    wins[i]++;
                }
            }
        }
        return wins;
    }


    /**
     * Conducts a tournament with an array of fighters, returning the wins. Losers are removed from the tournament.
     *
     * @param activeFighters List of characters participating in the tournament.
     * @return An array containing the number of wins for each character.
     */
    private ArrayList<Character> tournament(ArrayList<Character> activeFighters) {

        ArrayList<Character> losers = new ArrayList<>();
        Collections.shuffle(activeFighters);

        Random rand = new Random();

        int count1 = 0;
        // Simulate battles until only one fighter remains
        while (activeFighters.size() > 1) {
            System.out.println("\n####################################");
            int count2 = (count1 + 1) % activeFighters.size();

            // Reset both characters' stats before each battle
            resetCharacterStats(activeFighters.get(count1));
            resetCharacterStats(activeFighters.get(count2));
            int attacker, opponent; //Random who is allowed to attack first
            if (rand.nextBoolean()) {
                attacker = count1;
                opponent = count2;
            } else {
                attacker = count2;
                opponent = count1;
            }

            // Simulate battle and update win count accordingly
            boolean opponentWon = duel(activeFighters.get(attacker), activeFighters.get(opponent));

            // Adjust index to account for removal
            if (opponentWon) {
                losers.add(activeFighters.remove(attacker));  // Remove losing fighter from the tournament
            } else {
                losers.add(activeFighters.remove(opponent));  // Remove losing fighter from the tournament
            }
            count1 = (count1 + 1) % activeFighters.size();  // Adjust index to account for removal
        }
        System.out.println("\nDer Gewinner des Tourniers ist " + activeFighters.getLast().getClass().getSimpleName() +
                " " + activeFighters.getLast().getName() + "\n");
        losers.add(activeFighters.getLast()); // The winner of the tournament
        return losers;
    }


    ArrayList<Character> tournament(Character character, int n) {
        ArrayList<Character> fighters = createRandomFighterList(character.getLevel(), n - 1);
        fighters.add(character);  // Add the specified character to the tournament
        return tournament(fighters);  // Run the tournament
    }

    ArrayList<Character> createRandomFighterList(int level, int number) {
        ArrayList<Character> fighters = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            fighters.add(randomCharacter(level));
        }
        return fighters;
    }

    Character[] createRandomFighterArray(Character character, int n) {
        Character[] fighters = new Character[n + 1];
        fighters[0] = character;
        for (int i = 1; i < fighters.length; i++) {
            fighters[i] = randomCharacter(character.getLevel());
        }
        return fighters;
    }



}
