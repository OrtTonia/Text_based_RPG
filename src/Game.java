import java.util.Scanner;

public class Game {

    private final Scanner scanner = new Scanner(System.in);
    private Character myCharacter = null;
    private final GameView view;
    private DBController db = new DBController();

    public Game(GameView view, DBController db) {
        this.view = view;
    }

    void startGame() {

        // Start the game loop
        while (true) {
            // Display the current character if one exists
            if (myCharacter != null) {
                view.printCharacterStatus(myCharacter);
            }

            view.printMenue();

            // and get the user's selection
            switch (getSelection(1, 5)) {
                case 1 -> createCharacter();
                case 2 -> loadCharacter();
                case 3 -> saveCharacter();
                case 4 -> handleArena();
                case 5 -> {
                    view.printExitGame();
                    return;
                }
                default -> view.printMessage("Bitte versuche es erneut.");
            }
        }
    }


    // Creates a new character by asking the user for name and class
    private void createCharacter() {
        view.printMessage("Gib den Namen deines Charakters ein: ");
        String name = scanner.nextLine();
        view.printCharacterClass();
        int selectedClass = getSelection(1, 3);

        Character newCharacter = switch (selectedClass) {
            case 1 -> new Magier(name);
            case 2 -> new Krieger(name);
            case 3 -> new Spaeher(name);
            default -> {
                view.printMessage("Ungültige Wahl. Standardmäßig wird ein Krieger erstellt.");
                yield new Krieger(name);
            }
        };
        view.printMessage("\nEin " + newCharacter.getClass().getSimpleName() +
                " mit dem Namen " + newCharacter.getName() + " wurde erstellt!");
        this.myCharacter = newCharacter;
    }

    //shows all saved characters and loads one if selected
    private void loadCharacter() {
        if (db.showAllCharacters()) {

            int selectedID = getCharacterById();
            if (selectedID > 0) {
                myCharacter = db.getCharacter(selectedID);
            }
        } else {
            view.printMessage("\nEs sind Keine Charaktere gespeichert!");
        }
    }

    // Saves the character to the database (either new or update)
    private void saveCharacter() {
        if (myCharacter == null) {
            view.printMessage("Kein Charakter vorhanden. Erstelle zuerst einen Charakter.");
            return;
        }
        if (myCharacter.getId() != 0) {
            // If the character already has an ID, ask whether to update or save as new
            view.printSaveOption();
            int saveOption = getSelection(0, 2);

            if (saveOption == 1)
                db.updateCharacter(myCharacter);
            else if (saveOption == 2)
                db.saveNewCharacter(myCharacter);
        } else {
            // If the character doesn't have an ID, save as new
            db.saveNewCharacter(myCharacter);
        }
    }

    private void handleArena() {
        if (myCharacter == null) {
            view.printMessage("Kein Charakter vorhanden. Erstelle oder lade zuerst einen Charakter.");
        } else {
            while (true) {

                view.printArenaOptionen();
                int selectedFight = getSelection(0, 3);
                switch (selectedFight) {
                    case 1 -> view.arenaDuel(myCharacter);
                    case 2 -> view.arenaTraining(myCharacter, 3);
                    case 3 -> view.arenaTournament(myCharacter);
                    case 0 -> {
                        return;
                    }
                }
            }
        }
    }

    private int getSelection(int start, int end) {
        while (true) {
            view.printMessage("Bitte wähle eine Option (" + start + "-" + end + "): ");
            if (scanner.hasNextInt()) {
                int selection = scanner.nextInt();
                scanner.nextLine();
                if (selection >= start && selection <= end) {
                    return selection;
                }
            } else {
                scanner.next();  // Ungültige Eingabe löschen
            }
            view.printMessage("Ungültige Auswahl, bitte versuche es erneut.");
        }
    }

    /**
     * Asks the user to select a character ID from the list and returns it
     */
    public int getCharacterById() {
        view.printMessage("Wähle die ID des Charakters oder 0 um die Auswahl zu verlassen:");
        int selectedID = -1;
        while (selectedID < 0) {
            System.out.print("ID: ");
            if (scanner.hasNextInt()) {
                selectedID = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();  // Consume newline character
                view.printMessage("Ungültige Auswahl, bitte versuche es erneut.");
            }
        }
        return selectedID;
    }
}
