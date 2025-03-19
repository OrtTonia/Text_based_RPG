public class Main {

    public static void main(String[] args) {
        // create view
        GameView gameView = new GameView();


        // Create the table if not exist for storing characters in the database
        DBController db = new DBController();
        db.createTableCharacter();

        // Display the game logo
        gameView.printWelcomeMessage();

        Game controller = new Game(gameView, db);
        // Start the game loop
        controller.startGame();
    }


}
