import java.util.Scanner;

public class Game {
    private static final Scanner scanner = new Scanner(System.in);
    static Character myCharacter = null;

    static void play() {
        try (Scanner scanner = new Scanner(System.in)) {

            // Start the game loop
            while (true) {
                // Display the current character if one exists
                if (myCharacter != null) {
                    UI.printCharacter(myCharacter);
                }

                // Show the main menu
                UI.printMenue();
                // and get the user's selection
                switch (getSelection(1, 5)) {
                    case 1:
                        // Create a new character
                        myCharacter = createCharacter();
                        break;
                    case 2:
                        // Load an existing character
                        if (DBController.showAllCharacters()) {

                            int selectedID = getCharacterById();
                            if (selectedID > 0) {
                                myCharacter = DBController.getCharacter(selectedID);
                            }
                        } else {
                            System.out.println("\nEs sind Keine Charaktere gespeichert!");
                        }
                        break;
                    case 3:
                        // Save the current character
                        saveCharacter();
                        break;
                    case 4:
                        // Fight in the arena
                        if (myCharacter != null) {
                            handleArena();
                        } else {
                            System.out.println("Kein Charakter vorhanden. Erstelle oder lade zuerst einen Charakter.");
                        }
                        break;
                    case 5:
                        // Exit the game
                        UI.exitGame();
                        return;
                    default:
                        System.out.println("Bitte versuche es erneut.");
                }
            }
        }
    }


    // Creates a new character by asking the user for name and class
    private static Character createCharacter() {
        UI.printCharacterName();
        String name = scanner.nextLine();
        UI.printCharacterClass();
        int selectedClass = getSelection(1, 3);

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
        return newCharacter;
    }


    // Saves the character to the database (either new or update)
    private static void saveCharacter() {
        if (myCharacter == null) {
            System.out.println("Kein Charakter vorhanden. Erstelle zuerst einen Charakter.");
            return;
        }
        if (myCharacter.getId() != 0) {
            // If the character already has an ID, ask whether to update or save as new
            UI.printSaveOption();
            int saveOption = getSelection(0, 2);

            if (saveOption == 1)
                DBController.updateCharacter(myCharacter);
            else if (saveOption == 2)
                DBController.saveNewCharacter(myCharacter);
        } else {
            // If the character doesn't have an ID, save as new
            DBController.saveNewCharacter(myCharacter);
        }
    }

    private static void handleArena() {
        while (true) {

            UI.printArenaOptionen();
            int selectedFight = getSelection(0, 3);
            switch (selectedFight) {
                case 1 -> UI.arenaDuel(myCharacter);
                case 2 -> UI.arenaTraining(myCharacter,3);
                case 3 -> UI.arenaTournament(myCharacter);
                case 0 -> {
                    return;
                }
            }
        }
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
}
