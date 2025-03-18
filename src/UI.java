
import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    // Create a Scanner instance for user input
    private static final Scanner scanner = new Scanner(System.in);
    private static final String equalsLine = "===============================================================================";
    private static final String stars = "*******************************************************************************";
    private static final String logo = """
            ──┘ └───────────────────────────────────────────────────────────────────────────────────────────────────────────────┘ └──
            ──┐ ┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────┐ ┌──
              │ │                                                                                                               │ │ \s
              │ │  ███╗   ███╗███████╗██╗███╗   ██╗███████╗███╗   ██╗    ██╗  ██╗██╗     ███████╗██╗███╗   ██╗███████╗███████╗  │ │ \s
              │ │  ████╗ ████║██╔════╝██║████╗  ██║██╔════╝████╗  ██║    ██║ ██╔╝██║     ██╔════╝██║████╗  ██║██╔════╝██╔════╝  │ │ \s
              │ │  ██╔████╔██║█████╗  ██║██╔██╗ ██║█████╗  ██╔██╗ ██║    █████╔╝ ██║     █████╗  ██║██╔██╗ ██║█████╗  ███████╗  │ │ \s
              │ │  ██║╚██╔╝██║██╔══╝  ██║██║╚██╗██║██╔══╝  ██║╚██╗██║    ██╔═██╗ ██║     ██╔══╝  ██║██║╚██╗██║██╔══╝  ╚════██║  │ │ \s
              │ │  ██║ ╚═╝ ██║███████╗██║██║ ╚████║███████╗██║ ╚████║    ██║  ██╗███████╗███████╗██║██║ ╚████║███████╗███████║  │ │ \s
              │ │  ╚═╝     ╚═╝╚══════╝╚═╝╚═╝  ╚═══╝╚══════╝╚═╝  ╚═══╝    ╚═╝  ╚═╝╚══════╝╚══════╝╚═╝╚═╝  ╚═══╝╚══════╝╚══════╝  │ │ \s
              │ │                                                                                                               │ │ \s
              │ │                                          ██████╗ ██████╗  ██████╗                                             │ │ \s
              │ │                                          ██╔══██╗██╔══██╗██╔════╝                                             │ │ \s
              │ │                                          ██████╔╝██████╔╝██║  ███╗                                            │ │ \s
              │ │                                          ██╔══██╗██╔═══╝ ██║   ██║                                            │ │ \s
              │ │                                          ██║  ██║██║     ╚██████╔╝                                            │ │ \s
              │ │                                          ╚═╝  ╚═╝╚═╝      ╚═════╝                                             │ │ \s
            ──┘ └───────────────────────────────────────────────────────────────────────────────────────────────────────────────┘ └──
            ──┐ ┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────┐ ┌──
            """;
    private static final String reclaimer = "Im folgenden Spiel wird aus Gründen der Einfachheit nicht gegendert. Alle Begriffe sind nicht geschlechtsspezifisch gemeint.";
    private static final String mage = """
                          _,._     \s
              .||,       /_ _\\\\    \s
             \\.`',/      |'L'| |   \s
             = ,. =      | -,| L   \s
             / ││ \\    ,-'\\"/,'`.  \s
               ││     ,'   `,,. `. \s
               ,│____,' , ,;' \\| | \s
              (3|\\    _/|/'   _| | \s
               ││/,-''  | >-'' _,\\\\\s
               ││'      ==\\ ,-'  ,'\s
               ││       |  V \\ ,|  \s
               ││       |    |` |  \s
               ││       |    \\   \\\s
               ││       |     |   \\
               ││       |      \\   \\
               ││       |____)__\\___\\\
            """;
    private static final String warrior = """
            |\\             //
             \\\\           _!_
              \\\\         /___\\
               \\\\        [+++]
                \\\\    _ _\\^^^/_ _
                 \\\\/ (    '-'  ( )
                 /( \\/ | {&}   /\\ \\
                   \\  / \\     / _> )
                    "`   >:::;-'`""'-.
                        /:::/         \\
                       /  /||   {&}   |
                      (  / (\\         /
                      / /   \\'-.___.-'
                    _/ /     \\ \\
                   /___|    /___|""";
    private static final String scout = """
                    .    ____
                  .' \\  / \\==\\
                 /    \\ 77 \\ |
                /_.----\\\\__,-----.
            <--(\\_|_____<__|_____/
                \\  ````/|   ``/```
                 `.   / |    I|
                   `./  |____I|
                        !!!!!!!
                        | | I |
                        \\ \\ I |
                        | | I |
                       _|_|_I_|
                      /__/____|""";

    /**
     * Prints the game logo to the console
     */
    public static void printLogo() {
        System.out.println(equalsLine);
        System.out.println("""    
                \t\t\t\t\t\t╦ ╦╦  ╦  ╦╔═╔═╗╔╦╗╔╦╗╔═╗╔╗╔  ╔╗ ╔═╗╦
                \t\t\t\t\t\t║║║║  ║  ╠╩╗║ ║║║║║║║║╣ ║║║  ╠╩╗║╣ ║
                \t\t\t\t\t\t╚╩╝╩═╝╩═╝╩ ╩╚═╝╩ ╩╩ ╩╚═╝╝╚╝  ╚═╝╚═╝╩""");
        System.out.println(logo);
        System.out.println(reclaimer);
        System.out.println();
    }

    /**
     * Displays the main menu with options to the user
     */
    public static int getMenuSelection() {

        System.out.println("""
                 _____                                       _____\s
                ( ___ )─────────────────────────────────────( ___ )
                 ║   ║            ╔╦╗┌─┐┌┐┌┬ ┬┌─┐            ║   ║\s
                 ║   ║            ║║║├┤ ││││ │├┤             ║   ║\s
                 ║   ║            ╩ ╩└─┘┘└┘└─┘└─┘            ║   ║\s
                 ║   ║    [1] Neuen Charakter erstellen      ║   ║\s
                 ║   ║    [2] Lade gespeicherten Charakter   ║   ║\s
                 ║   ║    [3] Speicher deinen Charakter      ║   ║\s
                 ║   ║    [4] Kämpfe in der Arena            ║   ║\s
                 ║   ║    [5] Spiel beenden                  ║   ║\s
                 ║___║                                       ║___║\s
                (_____)─────────────────────────────────────(_____)""");
        return getSelection(1, 5);
    }


    /**
     * Prompts the user for a character name and returns it
     */
    public static String getCharacterName() {
        System.out.print("Gib den Namen deines Charakters ein: ");
        return scanner.nextLine();
    }

    /**
     * Displays the character class options
     *
     * @return the user's choice
     */
    public static int getCharacterClass() {
        System.out.println("\nWähle eine Klasse:");
        System.out.println("""
                        ╦╔═┬  ┌─┐┌─┐┌─┐┌─┐┌┐┌
                 ____   ╠╩╗│  ├─┤└─┐└─┐├┤ │││   ____
                ( __ )  ╩ ╩┴─┘┴ ┴└─┘└─┘└─┘┘└┘  ( __ )
                 │  │~~~~~~~~~~~~~~~~~~~~~~~~~~~│  │\s
                 │  │        [1] Magier         │  │\s
                 │  │        [2] Krieger        │  │\s
                 │  │        [3] Späher         │  │\s
                 │__│~~~~~~~~~~~~~~~~~~~~~~~~~~~│__│\s
                (____)                         (____)""");
        return getSelection(1, 3);
    }

    /**
     * asks the user whether they want to save the character as a new one or update an existing one
     *
     * @return 0 for Save as a new characte;  1 for Update character in DB
     */
    public static int getSaveOption() {
        System.out.println();
        System.out.println("""
                ╔════════════════════════════════════╗
                ║  Speichermöglichkeiten:            ║
                ║  [0] Auswahl verlassen             ║
                ║  [1] aktualisiere Charakter        ║
                ║  [2] speicher als neuen Charakter  ║
                ╚════════════════════════════════════╝""");
        return getSelection(0, 2);
    }

    public static int getArenaSelection() {
        System.out.println();
        System.out.println("""
                ╔╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╗
                ╟┼┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┴┼╢
                ╟┤  WILKOMMEN IN DER                                    ├╢
                ╟┤  ╔═╗┬─┐┌─┐┌┐┌┌─┐                                     ├╢
                ╟┤  ╠═╣├┬┘├┤ │││├─┤                                     ├╢
                ╟┤  ╩ ╩┴└─└─┘┘└┘┴ ┴                                     ├╢
                ╟┤                                                      ├╢
                ╟┤  Wie möchtest du kämfen?                             ├╢
                ╟┤  [0] Arena verlassen                                 ├╢
                ╟┤  [1] Duell: kämpfe gegen einen Gegner                ├╢
                ╟┤  [2] Training: kämpfe in einem trainigs Wettkampf    ├╢
                ╟┤  [3] Turnier: kämpfe in einem kleinen Turnier        ├╢
                ╟┼┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┬┼╢
                ╚╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╝""");
        return getSelection(0, 3);
    }

    private static int getSelection(int start, int end) {
        while (true) {
            System.out.print("Bitte wähle eine Option (" + start + "-" + end + "): ");
            if (scanner.hasNextInt()) {
                int selection = scanner.nextInt();
                scanner.nextLine();
                if (selection >= start && selection <= end) {
                    return selection;
                }
            } else {
                scanner.next();  // Ungültige Eingabe löschen
            }
            System.out.println("Ungültige Auswahl, bitte versuche es erneut.");
        }
    }

    /**
     * Asks the user to select a character ID from the list and returns it
     */
    public static int getCharacterById() {
        System.out.println("Wähle die ID des Charakters oder 0 um die Auswahl zu verlassen:");
        int selectedID = -1;
        while (selectedID < 0) {
            System.out.print("ID: ");
            try {
                selectedID = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();  // Consume newline character
                System.out.println("Ungültige Auswahl, bitte versuche es erneut.");
            }
        }
        return selectedID;
    }


    public static void printCharacter(Character character) {
        String spazialeCharacterStats = "";
        if (character instanceof Magier magier) {
            System.out.println(mage);
            spazialeCharacterStats = "  Intelligenz: " + magier.getIntelligence() +
                    "\n  Mana: " + magier.getMaxMana();
        }
        if (character instanceof Krieger krieger) {
            System.out.println(warrior);
            spazialeCharacterStats = "  Stärke: " + krieger.getStrength() +
                    "\n  Rüstung: " + krieger.getArmor();
        }
        if (character instanceof Spaeher spaeher) {
            System.out.println(scout);
            spazialeCharacterStats = "  Beweglichkeit: " + spaeher.getAgility() +
                    "\n  Ausdauer: " + spaeher.getMaxEndurance();
        }
        System.out.println("********  Player  ********");
        System.out.println("  Name: " + character.getName());
        System.out.println("  Klasse: " + character.getClass().getSimpleName());
        System.out.println("  Level: " + character.getLevel() + "\t\t\tXP: " + character.getXp());
        System.out.println("  HP: " + character.getMaxHealth());
        System.out.println(spazialeCharacterStats);
        System.out.println("*************************");
    }


    /**
     * Simulates an arena fight between the player's character and a random opponent
     *
     * @param character of the player
     */
    public static void arenaDuel(Character character) {
        Battle battle = new Battle();
        Character opponent = battle.randomCharacter(character.getLevel());
        System.out.println("\n" + stars);
        System.out.println("Du trittst gegen " + opponent.getClass().getSimpleName() + " " + opponent.getName() + " in der Arena an!");

        battle.duel(character, opponent);
        System.out.println(stars);
    }

    public static void arenaTraining(Character character) {
        Battle battle = new Battle();
        int countOpponents = 3;

        System.out.println("\n" + stars);
        System.out.println("Du hast dich entschlossen an einem trainnings Wettkampf mit " + countOpponents + " Gegnern teilzunehem.");
        System.out.println("Jeder kämpft zweimal gegen jeden.\n");
        Character[] fighters = battle.createRandomFighterArray(character, countOpponents);
        System.out.println("Die Teilnehmer sind:");
        for (Character fighter : fighters) {
            System.out.println(fighter.toSmallString());
        }
        int[] result = battle.trainingTournament(fighters);

        System.out.println("\nErgebnis:");
        for (int i = 0; i < fighters.length; i++) {
            System.out.println(fighters[i].toSmallString() + "\t" +
                    result[i] + " Gewinne");
        }
        System.out.println(stars);
    }

    public static void arenaTournament(Character character) {
        Battle battle = new Battle();
        int countOpponents = 8;
        System.out.println("\n" + stars);
        System.out.println("Du nimmst an einem Tournier mit " + countOpponents + " Teilnehmern in der Arena teil \n");

        ArrayList<Character> fighters = battle.tournament(character, countOpponents);

        int characterPosition = fighters.size() - fighters.indexOf(character);
        System.out.println(character.getClass().getSimpleName() + " " + character.getName() +
                " beendete das Turnier auf Platz " + characterPosition + ".\n");
        System.out.println("*** Ergebnisliste des Tourniers: ***");
        for (int i = 1; i <= fighters.size(); i++) {
            System.out.println("Platz " + i + ": " + fighters.get(fighters.size() - i).toSmallString());
        }
        System.out.println("\n" + stars);
    }


    public static void exitGame() {
        System.out.println(equalsLine);
        System.out.println("Spiel wird beendet.");
        System.out.println("Auf Wiedersehen!");

        System.out.println();
        System.out.println(logo);
    }
}
