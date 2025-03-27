import java.util.List;

/**
 * The GameView class is responsible for displaying game-related messages, menus, and character information to the console.
 * ASCII art from https://www.asciiart.eu/
 * @author ortenburger
 */
public class GameView {
    private final String equalsLine = "===============================================================================";
    private final String logo = """
            ─┘ └─────────────────────────────────────────────────────────────────────────────────────────────┘ └──
            ─┐ ┌─────────────────────────────────────────────────────────────────────────────────────────────┐ ┌──
             │ │                                                                                             │ │\s
             │ │  ███╗   ███╗███████╗██╗███╗   ██╗    ██╗  ██╗██╗     ███████╗██╗███╗   ██╗███████╗███████╗  │ │\s
             │ │  ████╗ ████║██╔════╝██║████╗  ██║    ██║ ██╔╝██║     ██╔════╝██║████╗  ██║██╔════╝██╔════╝  │ │\s
             │ │  ██╔████╔██║█████╗  ██║██╔██╗ ██║    █████╔╝ ██║     █████╗  ██║██╔██╗ ██║█████╗  ███████╗  │ │\s
             │ │  ██║╚██╔╝██║██╔══╝  ██║██║╚██╗██║    ██╔═██╗ ██║     ██╔══╝  ██║██║╚██╗██║██╔══╝  ╚════██║  │ │\s
             │ │  ██║ ╚═╝ ██║███████╗██║██║ ╚████║    ██║  ██╗███████╗███████╗██║██║ ╚████║███████╗███████║  │ │\s
             │ │  ╚═╝     ╚═╝╚══════╝╚═╝╚═╝  ╚═══╝    ╚═╝  ╚═╝╚══════╝╚══════╝╚═╝╚═╝  ╚═══╝╚══════╝╚══════╝  │ │\s
             │ │                                                                                             │ │\s
             │ │                                  ██████╗ ██████╗  ██████╗                                   │ │\s
             │ │                                  ██╔══██╗██╔══██╗██╔════╝                                   │ │\s
             │ │                                  ██████╔╝██████╔╝██║  ███╗                                  │ │\s
             │ │                                  ██╔══██╗██╔═══╝ ██║   ██║                                  │ │\s
             │ │                                  ██║  ██║██║     ╚██████╔╝                                  │ │\s
             │ │                                  ╚═╝  ╚═╝╚═╝      ╚═════╝                                   │ │\s
           ──┘ └─────────────────────────────────────────────────────────────────────────────────────────────┘ └──
           ──┐ ┌─────────────────────────────────────────────────────────────────────────────────────────────┐ ┌──
            """;

    /**
     * Prints the game logo and welcome message to the console.
     */
    public void printWelcomeMessage() {
        System.out.println(equalsLine);
        System.out.println("""    
                \t\t\t\t\t\t╦ ╦╦  ╦  ╦╔═╔═╗╔╦╗╔╦╗╔═╗╔╗╔  ╔╗ ╔═╗╦
                \t\t\t\t\t\t║║║║  ║  ╠╩╗║ ║║║║║║║║╣ ║║║  ╠╩╗║╣ ║
                \t\t\t\t\t\t╚╩╝╩═╝╩═╝╩ ╩╚═╝╩ ╩╩ ╩╚═╝╝╚╝  ╚═╝╚═╝╩""");
        System.out.println(logo);
        String reclaimer = "Im folgenden Spiel wird aus Gründen der Einfachheit nicht gegendert. Alle Begriffe sind nicht geschlechtsspezifisch gemeint.";
        System.out.println(reclaimer);
        System.out.println();

    }

    /**
     * Displays the initial start menu options.
     */
    public void printStartMenu() {
        System.out.println("""
                 _____                                       _____\s
                ( ___ )─────────────────────────────────────( ___ )
                 ║   ║            ╔╦╗┌─┐┌┐┌┬ ┬┌─┐            ║   ║\s
                 ║   ║            ║║║├┤ ││││ │├┤             ║   ║\s
                 ║   ║            ╩ ╩└─┘┘└┘└─┘└─┘            ║   ║\s
                 ║   ║    [1] Neuen Charakter erstellen      ║   ║\s
                 ║   ║    [2] Lade gespeicherten Charakter   ║   ║\s
                 ║   ║    [5] Spiel beenden                  ║   ║\s
                 ║___║                                       ║___║\s
                (_____)─────────────────────────────────────(_____)""");
    }

    /**
     * Displays the main menu options for the user.
     */
    public void printMenu() {
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

    }

    /**
     * Displays the available character classes for selection.
     */
    public void printCharacterClassMenu() {
        List<String> classes = List.of("Magier ", "Krieger", "Späher ");

        System.out.println("\nWähle eine Klasse:");
        System.out.println("""
                        ╦╔═┬  ┌─┐┌─┐┌─┐┌─┐┌┐┌
                 ____   ╠╩╗│  ├─┤└─┐└─┐├┤ │││   ____
                ( __ )  ╩ ╩┴─┘┴ ┴└─┘└─┘└─┘┘└┘  ( __ )
                 │  │~~~~~~~~~~~~~~~~~~~~~~~~~~~│  │""");

        for (int i = 0; i < classes.size(); i++) {
            System.out.printf(" │  │        [%d] %s        │  │%n", i + 1, classes.get(i));
        }

        System.out.println("""
                 │__│~~~~~~~~~~~~~~~~~~~~~~~~~~~│__│
                (____)                         (____)""");
    }

    /**
     * Displays options for saving a character (update or new save).
     */
    public void printSaveMenu() {
        System.out.println();
        System.out.println("""
                ╔════════════════════════════════════╗
                ║  Speichermöglichkeiten:            ║
                ║  [0] Auswahl verlassen             ║
                ║  [1] aktualisiere Charakter        ║
                ║  [2] speicher als neuen Charakter  ║
                ╚════════════════════════════════════╝""");

    }

    /**
     * Displays the different battle options available in the arena.
     */
    public void printArenaMenu() {
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
    }

    /**
     * Prints the current status of a character, including stats and ASCII image.
     *
     * @param character The character whose stats are displayed.
     */
    public void printCharacterStatus(Character character) {
        String spazialeCharacterStats = "";
        if (character instanceof Magier magier) {
            String mage = """
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
            System.out.println(mage);
            spazialeCharacterStats = "  Intelligenz: " + magier.getIntelligence() +
                    "\n  Mana: " + magier.getMaxMana();
        }
        if (character instanceof Krieger krieger) {
            String warrior = """
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
            System.out.println(warrior);
            spazialeCharacterStats = "  Stärke: " + krieger.getStrength() +
                    "\n  Rüstung: " + krieger.getArmor();
        }
        if (character instanceof Spaeher spaeher) {
            String scout = """
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
            System.out.println(scout);
            spazialeCharacterStats = "  Beweglichkeit: " + spaeher.getAgility() +
                    "\n  Ausdauer: " + spaeher.getMaxEndurance();
        }
        System.out.println("*********  Player  *********");
        System.out.println("  Name: " + character.getName());
        System.out.println("  Klasse: " + character.getClass().getSimpleName());
        System.out.println("  Level: " + character.getLevel() + "\t\t\tXP: " + character.getXp());
        System.out.println("  HP: " + character.getMaxHealth());
        System.out.println(spazialeCharacterStats);
        System.out.println("***************************");
    }

    /**
     * Prints a line of stars for visual separation.
     */
    public void printStars() {
        String stars = "*******************************************************************************";
        System.out.println(stars);
    }

    /**
     * Displays tournament options to the user.
     */
    public void printTournamentMenu() {
        System.out.println();
        System.out.println("""
                ╔═────────────────────────────────────────────═╗
                │  Tourniere:                                   │
                │  [0] Auswahl verlassen                       │
                │  [1] kleines Tournier mit 8 Teilenehmern     │
                │  [2] mittleres Tournier mit 16 Teilenehmern  │
                │  [3] großes Tournier mit 32 Teilenehmern     │
                ╚─────────────────────────────────────────────═╝""");
    }


    /**
     * Displays an exit message and game closure.
     */
    public void printExitGame() {
        System.out.println(equalsLine);
        System.out.println("Spiel wird beendet.");
        System.out.println("Auf Wiedersehen!");

        System.out.println();
        System.out.println(logo);
    }
}
