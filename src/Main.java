public class Main {

    public static void main(String[] args) {
        // Create the table if not exist for storing characters in the database
        DBController.createTableCharacter();

        // Display the game logo
        UI.printLogo();

        // Start the game loop
        Game.play();
    }


}
