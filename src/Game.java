import java.util.Scanner;

public class Game {
    static Character myCharacter = null;

    static void play() {
        try (Scanner scanner = new Scanner(System.in)) {

            // Start the game loop
            while (true) {
                // Display the current character if one exists
                if (myCharacter != null) {
                    UI.printCharacter(myCharacter);
                }

                // Show the main menu and get the user's selection
                switch (UI.getMenuSelection()) {
                    case 1:
                        // Create a new character
                        myCharacter = createCharacter();
                        break;
                    case 2:
                        // Load an existing character
                        if (DBController.showAllCharacters()) {

                            int selectedID = UI.getCharacterById();
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
        String name = UI.getCharacterName();
        int selectedClass = UI.getCharacterClass();

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
            int saveOption = UI.getSaveOption();
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
            int selectedFight = UI.getArenaSelection();
            switch (selectedFight) {
                case 1 -> UI.arenaDuel(myCharacter);
                case 2 -> UI.arenaTraining(myCharacter);
                case 3 -> UI.arenaTournament(myCharacter);
                case 0 -> {
                    return;
                }
            }
        }
    }
}
