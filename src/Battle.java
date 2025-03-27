import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The Battle class manages combat mechanics
 * It supports various battle modes and ensures fair fights through character stat resets before each duel.
 * @author ortenburger
 */
public class Battle {
    private static final Random rand = new Random();

    /**
     * Simulates a battle between the character and a randomly generated opponent.
     *
     * @param character The character entering the fight.
     */
    public void arenaDuel(Character character) {
        Character opponent = createRandomCharacter(character.getLevel());
        System.out.println("Du trittst gegen " + opponent.getClass().getSimpleName() + " " + opponent.getName() + " in der Arena an!");
        duel(character, opponent);
    }

    /**
     * Simulates a battle between two characters.
     *
     * @param attacker The first character attacking.
     * @param opponent The second character defending.
     * @return false if attacker wins, true if opponent wins.
     */
    private boolean duel(Character attacker, Character opponent) {
        attacker.resetCharacterStats();
        opponent.resetCharacterStats();

        System.out.println("\nKampf beginnt zwischen: " + attacker.getName() + " und " + opponent.getName());
        System.out.println(attacker);
        System.out.println(opponent);

        int round = 1;
        while (attacker.getHealth() > 0 && opponent.getHealth() > 0) {
            System.out.println("\nRunde " + round++ + ":");

            if (attackAndCheckIfFightIsOver(attacker, opponent)) return false;
            if (attackAndCheckIfFightIsOver(opponent, attacker)) return true;
        }
        return false;
    }

    /**
     * Handles an attack and checks if the defender is defeated.
     */
    private boolean attackAndCheckIfFightIsOver(Character attacker, Character defender) {
        attacker.attack(defender);
        if (!defender.isAlive()) {
            declareWinner(attacker, defender);
            return true;
        }
        return false;
    }

    /**
     * Generates a random character (Magier, Krieger, or Spaeher).
     *
     * @return A randomly generated character
     */
    private Character createRandomCharacter(int level) {
        String[] names = {
                "Merlin", "Cerridwen", "Asa", "Sabrina", "Zinta", "Roxanne", "David", "Alana",
                "Armand", "Einar", "Gregor", "Jari", "Siglinde", "Gloria", "Siegfried", "Xena",
                "Olivier", "Igor", "Edith", "Hel", "Angelina", "Marco", "Alexander", "Gertrude"};

        // Select a random name
        String name = names[rand.nextInt(names.length)];

        int xp = randomXp(level);

        return switch (rand.nextInt(3)) {
            case 0 -> new Magier(name, xp);
            case 1 -> new Krieger(name, xp);
            case 2 -> new Spaeher(name, xp);
            default -> throw new IllegalStateException("Fehler bei der Charaktererstellung");
        };
    }

    /**
     * Generates a random XP value based on the character's level.
     */
    private int randomXp(int level) {
        return rand.nextInt(Math.max(level - 2, 0) * 20, (level + 3) * 20);

    }

    /**
     * Declares the winner of a battle and awards XP.
     *
     * @param winner The winning character
     * @param loser  TThe defeated opponent
     */
    private void declareWinner(Character winner, Character loser) {
        System.out.println("\nDas Kampf ist vorbei!");
        int xpIncrease = Math.max(2 + (loser.getXp() - winner.getXp()) / 10, 1);
        System.out.println(winner.getClass().getSimpleName() + " " +
                winner.getName() + " hat den Kampf gewonnen!");
        winner.increaseXp(xpIncrease);
        System.out.println();
        System.out.println(winner.toSmallString());
        System.out.println(loser.toSmallString());
    }

    /**
     * Runs a training tournament where all fighters battle each other twice.
     */
    public void arenaTraining(Character character, int numberOfOpponents) {
        Character[] fighters = createRandomFighterArray(character, numberOfOpponents);
        System.out.println("Die Teilnehmer sind:");
        for (Character fighter : fighters) {
            System.out.println(fighter.toSmallString());
        }
        int[] result = trainingTournament(fighters);

        System.out.println("\nErgebnis:");
        for (int i = 0; i < fighters.length; i++) {
            System.out.println(fighters[i].toSmallString() + "\t" + " Gewinne: " +
                    result[i]);
        }
    }


    /**
     * Runs a knockout-style tournament where losers are eliminated until one winner remains.
     */
    public void arenaTournament(Character character, int numberOfOpponents) {
        System.out.println("Du nimmst an einem Tournier mit " + numberOfOpponents + " Teilnehmern in der Arena teil \n");

        ArrayList<Character> fighters = createRandomFighterList(character.getLevel(), numberOfOpponents - 1);
        fighters.add(character);  // Add the specified character to the tournament
        Collections.shuffle(fighters);
        System.out.println("\nTeilnehmerliste:");
        for (Character fighter : fighters) {
            System.out.println(fighter.toSmallString());
        }
        System.out.println();

        ArrayList<Character> fightersResultList = tournament(fighters);// Run the tournament

        int characterPosition = numberOfOpponents - fightersResultList.indexOf(character);
        if (character != fighters.getFirst()) {
            System.out.println("Dein " + character.getClass().getSimpleName() + " " + character.getName() +
                    " beendete das Turnier auf Platz " + characterPosition + ".\n");
        }
        System.out.println("**** Ergebnisliste des Tourniers: ****");
        for (int i = 1; i <= numberOfOpponents; i++) {
            System.out.println("Platz " + i + ": " + fightersResultList.get(numberOfOpponents - i).toSmallString());
        }
    }


    private int[] trainingTournament(Character[] fighters) {
        // Simulate battles between all pairs of fighters

        int[] wins = new int[fighters.length];
        for (int i = 0; i < fighters.length; i++) {
            for (int j = 0; j < fighters.length; j++) {
                if (i == j) continue; // Skip if fighting yourself
                System.out.println("\n####################################");
                fighters[i].resetCharacterStats();
                fighters[j].resetCharacterStats();

                // Simulate battle and update win count accordingly
                if (duel(fighters[i], fighters[j])) {
                    wins[j]++;
                } else {
                    wins[i]++;
                }
            }
        }
        System.out.println("\n####################################");
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

        int count1 = 0;
        // Simulate battles until only one fighter remains
        while (activeFighters.size() > 1) {
            System.out.println("\n####################################");
            int count2 = (count1 + 1) % activeFighters.size();

            // Reset both characters' stats before each battle
            activeFighters.get(count1).resetCharacterStats();
            activeFighters.get(count2).resetCharacterStats();
            int attacker, opponent; //Random who is allowed to attack first
            if (rand.nextBoolean()) {
                attacker = count1;
                opponent = count2;
            } else {
                attacker = count2;
                opponent = count1;
            }

            boolean opponentWon = duel(activeFighters.get(attacker), activeFighters.get(opponent));

            // Adjust index to account for removal
            if (opponentWon) {
                losers.add(activeFighters.remove(attacker));  // Remove losing fighter from the tournament
            } else {
                losers.add(activeFighters.remove(opponent));  // Remove losing fighter from the tournament
            }
            count1 = (count1 + 1) % activeFighters.size();  // Adjust index to account for removal
        }
        System.out.println("\n####################################");
        System.out.println("\nDer Gewinner des Tourniers ist " + activeFighters.getLast().getClass().getSimpleName() +
                " " + activeFighters.getLast().getName() + "\n");
        losers.add(activeFighters.getLast()); // The winner of the tournament
        return losers;
    }


    /**
     * Creates a list of random fighters based on level and number of participants.
     */
    private ArrayList<Character> createRandomFighterList(int level, int number) {
        ArrayList<Character> fighters = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            fighters.add(createRandomCharacter(level));
        }
        return fighters;
    }

    /**
     * Creates an array of random fighters including the given character.
     */
    private Character[] createRandomFighterArray(Character character, int n) {
        Character[] fighters = new Character[n + 1];
        fighters[0] = character;
        for (int i = 1; i < fighters.length; i++) {
            fighters[i] = createRandomCharacter(character.getLevel());
        }
        return fighters;
    }

}
