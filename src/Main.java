import java.util.Scanner;

/**
 * The main class contains the main() methods to start a simple, text-based RPG game
 * @author ortenburger
 */
public class Main {

    public static void main(String[] args) {
        // create view
        GameView gameView = new GameView();

        Scanner scanner = new Scanner(System.in);

        // Create the table if not exist for storing characters in the database
        DBManager db = new DBManager();
        db.createTableCharacter();

        // Display the game logo
        gameView.printWelcomeMessage();

        GameController controller = new GameController(gameView, db, scanner);
        // Start the game loop
        controller.startGame();
    }


}
