import java.util.Scanner;

/**
 * The GameController class manages the game logic, user inputs, and interactions
 * between the player, database, and battles.
 * @author ortenburger
 */
public class GameController {
    private final Scanner scanner;
    private Character myCharacter;
    private final GameView view;
    private final DBManager db;
    private final Battle battle;

    /**
     * Constructs a new GameController.
     *
     * @param view    The GameView instance for displaying information.
     * @param db      The DBManager instance for character persistence.
     * @param scanner The Scanner instance for user input.
     */
    public GameController(GameView view, DBManager db, Scanner scanner) {
        this.view = view;
        this.db = db;
        this.scanner = scanner;
        this.myCharacter = null;
        this.battle = new Battle();
    }

    /**
     * Starts the game loop, handling user interactions and navigation.
     */
    void startGame() {
        // Start the game loop
        while (true) {
            // Display the current character if one exists
            if (myCharacter != null) {
                view.printCharacterStatus(myCharacter);
                view.printMenu();
            } else {
                view.printStartMenu();
            }
            switch (getSelection(null, 1, 5)) {
                case 1 -> createCharacter();
                case 2 -> loadCharacter();
                case 3 -> saveCharacter();
                case 4 -> handleArena();
                case 5 -> {
                    view.printExitGame();
                    return;
                }
                default -> System.out.println("Bitte versuche es erneut.");
            }
        }
    }


    /**
     * Prompts the user to create a new character by choosing a name and class.
     */
    private void createCharacter() {
        System.out.println("Gib den Namen deines Charakters ein: ");
        String name = scanner.nextLine();
        view.printCharacterClassMenu();
        int selectedClass = getSelection("Wähle eine Klasse", 1, 3);

        Character newCharacter = switch (selectedClass) {
            case 1 -> new Magier(name);
            case 2 -> new Krieger(name);
            case 3 -> new Spaeher(name);
            default -> {
                System.out.println("Ungültige Wahl. Standardmäßig wird ein Krieger erstellt.");
                yield new Krieger(name);
            }
        };
        System.out.println("\nEin " + newCharacter.getClass().getSimpleName() +
                " mit dem Namen " + newCharacter.getName() + " wurde erstellt!");
        this.myCharacter = newCharacter;
    }

    /**
     * shows all saved characters and Loads a saved character from the database.
     */
    private void loadCharacter() {
        if (db.showAllCharacters()) {
            int selectedID = getCharacterById();
            if (selectedID > 0) {
                if (db.getCharacter(selectedID) != null)
                    myCharacter = db.getCharacter(selectedID);
            }
        } else {
            System.out.println("\nEs sind Keine Charaktere gespeichert!");
        }
    }

    /**
     * Saves the current character to the database.
     */
    private void saveCharacter() {
        if (myCharacter == null) {
            System.out.println("Kein Charakter vorhanden. Erstelle zuerst einen Charakter.");
            return;
        }
        if (myCharacter.getId() != 0) {
            // If the character already has an ID, ask whether to update or save as new
            view.printSaveMenu();
            int saveOption = getSelection(null, 0, 2);

            if (saveOption == 1)
                db.updateCharacter(myCharacter);
            else if (saveOption == 2)
                db.saveNewCharacter(myCharacter);
        } else {
            // If the character doesn't have an ID, save as new
            db.saveNewCharacter(myCharacter);
        }
    }

    /**
     * Manages the different battle options available in the arena.
     */
    private void handleArena() {
        if (myCharacter == null) {
            System.out.println("Kein Charakter vorhanden. Erstelle oder lade zuerst einen Charakter.");
        } else {
            while (true) {
                view.printCharacterStatus(myCharacter);
                view.printArenaMenu();
                int selectedFight = getSelection(null, 0, 3);

                if (selectedFight == 0) return;

                switch (selectedFight) {
                    case 1 -> arenaDuel();
                    case 2 -> arenaTraining();
                    case 3 -> arenaTournament();
                }
            }
        }
    }

    /**
     * Simulates an arena fight between the player's character and a random opponent
     */
    private void arenaDuel() {
        System.out.println();
        view.printStars();
        battle.arenaDuel(myCharacter);
        view.printStars();
    }

    /**
     * Runs a training match against multiple opponents.
     */
    private void arenaTraining() {
        System.out.println();
        view.printStars();
        int numberOfOpponents = getNumberOfOpponents();
        System.out.println("Du hast dich entschlossen an einem trainnings Wettkampf mit " + numberOfOpponents + " Gegnern teilzunehem.");
        System.out.println("Jeder kämpft zweimal gegen jeden.\n");

        battle.arenaTraining(myCharacter, numberOfOpponents);
        view.printStars();
    }

    /**
     * Runs a tournament-style battle sequence.
     */
    private void arenaTournament() {
        System.out.println();
        view.printStars();
        int numberOfOpponents = getNumberOfOpponentsInTournament();
        if (numberOfOpponents == 0)
            return;
        battle.arenaTournament(myCharacter, numberOfOpponents);
        view.printStars();
    }


    /**
     * Gets the number of opponents for a training match.
     */
    private int getNumberOfOpponents() {
        return getSelection("Gib die Anzahl der Gegner ein", 1, 12);
    }

    /**
     * Gets the number of opponents for a tournament.
     */
    private int getNumberOfOpponentsInTournament() {
        view.printTournamentMenu();
        int selection = getSelection("Bei welchem Tournier möchtest du teilnehmen", 0, 3);
        return switch (selection) {
            case 1 -> 8;
            case 2 -> 16;
            case 3 -> 32;
            default -> 0;
        };
    }

    /**
     * Retrieves a validated user selection between given bounds.
     */
    private int getSelection(String str, int start, int end) {
        str = str == null ? "Bitte wähle eine Option" : str;
        int selection;
        do {
            System.out.print(str + " (" + start + "-" + end + "): ");
            while (!scanner.hasNextInt()) {
                System.out.print("\nUngültige Eingabe, bitte Zahl zwischen " + start + " und " + end + " eingeben.");
                scanner.next();
            }
            selection = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } while (selection < start || selection > end);

        return selection;
    }

    /**
     * Asks the user to select a character ID from the list and returns it.
     */
    private int getCharacterById() {
        System.out.println("Wähle die ID des Charakters oder 0 um die Auswahl zu verlassen:");
        System.out.print("ID: ");
        int selectedID;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Ungültige Eingabe, bitte eine Zahl eingeben.");
                scanner.next();
            }
            selectedID = scanner.nextInt();
            scanner.nextLine();
        } while (selectedID < 0);

        return selectedID;
    }
}
