import java.sql.*;

/**
 * DBManager handles all database operations related to character storage and retrieval.
 * It provides methods for creating, updating, retrieving, and deleting characters in the SQLite database.
 */
public class DBManager {
    private static Connection connection;

    /**
     * Establishes and returns a database connection.
     * Uses a singleton pattern to ensure only one connection is maintained.
     *
     * @return an active database connection
     * @throws SQLException if an error occurs while establishing the connection
     */
    private Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            // Database URL for SQLite connection
            String URL = "jdbc:sqlite:characters.db";
            connection = DriverManager.getConnection(URL);
        }
        return connection;
    }

    /**
     * Creates the "character" table in the database if it does not already exist.
     */
    public void createTableCharacter() {

        try (Statement stmt = getConnection().createStatement()) {
            //dropTableCharacter();
            // SQL Queries as constants to avoid duplication
            String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS character (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "class TEXT, " +
                    "level INTEGER, " +
                    "xp INTEGER, " +
                    "health INTEGER, " +
                    "mana INTEGER, " +
                    "intelligence INTEGER, " +
                    "strength INTEGER, " +
                    "armor INTEGER, " +
                    "agility INTEGER, " +
                    "endurance INTEGER)";
            stmt.execute(CREATE_TABLE_SQL);
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    /**
     * Deletes the "character" table from the database.
     */
    public void dropTableCharacter() {
        try (Statement stmt = getConnection().createStatement()) {
            String DROP_TABLE_SQL = "DROP TABLE IF EXISTS character";
            stmt.executeUpdate(DROP_TABLE_SQL);
        } catch (SQLException e) {
            System.err.println("Error dropping table: " + e.getMessage());
        }
    }

    /**
     * Inserts a character into the database.
     *
     * @param character The character to be saved (could be Magier, Krieger, or Spaeher).
     */
    public void saveNewCharacter(Character character) {
        String INSERT_CHARACTER_SQL = "INSERT INTO character(name, class, level, xp, health, mana, intelligence, strength, armor, agility, endurance) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(INSERT_CHARACTER_SQL)) {
            // Set character attributes
            pstmt.setString(1, character.getName());
            pstmt.setString(2, character.getClass().getSimpleName());
            pstmt.setInt(3, character.getLevel());
            pstmt.setInt(4, character.getXp());
            pstmt.setInt(5, character.getMaxHealth());

            // Set additional attributes based on character class
            setCharacterAttributes(pstmt, character, 6);
            pstmt.executeUpdate();

            updateIdOfCharacter(character);

            System.out.println("Dein " + character.getClass().getSimpleName() + " " + character.getName() + " wurde gespeichert.");
        } catch (SQLException e) {
            System.err.println("Error saving character: " + e.getMessage());
        }
    }

    /**
     * Retrieves and sets the ID of the last inserted character.
     *
     * @param character the character whose ID needs to be updated
     */
    private void updateIdOfCharacter(Character character) {
        String SELECT_LAST_CHARACTERS_SQL = "SELECT * FROM character ORDER BY ID DESC LIMIT 1";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_LAST_CHARACTERS_SQL)) {

            //Set ID
            if (rs.next()) {
                character.setId(rs.getInt("id"));
                System.out.println("ID: " + character.getId());
            }
        } catch (SQLException e) {
            System.err.println("Error updating character ID: " + e.getMessage());
        }

    }

    /**
     * Updates an existing character's data in the database.
     *
     * @param character the character whose data needs to be updated.
     */
    public void updateCharacter(Character character) {
        String UPDATE_CHARACTER_SQL = "UPDATE character SET level=?, xp=?, health=?, mana=?, intelligence=?, strength=?, armor=?, agility=?, endurance=? WHERE id=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(UPDATE_CHARACTER_SQL)) {

            // Set the updated character attributes
            pstmt.setInt(10, character.getId());
            pstmt.setInt(1, character.getLevel());
            pstmt.setInt(2, character.getXp());
            pstmt.setInt(3, character.getMaxHealth());

            // Set class-specific attributes
            setCharacterAttributes(pstmt, character, 4);

            pstmt.executeUpdate();
            System.out.println("Dein " + character.getClass().getSimpleName() + " " + character.getName() + " wurde erneut gespeichert.");
        } catch (SQLException e) {
            System.err.println("Error updating character: " + e.getMessage());
        }
    }

    /**
     * Sets the character's class-specific attributes in the prepared statement.
     *
     * @param pstmt     the prepared statement
     * @param character the character object containing the data
     * @param startIdx  the starting index for setting attributes
     * @throws SQLException if a database access error occurs
     */
    private static void setCharacterAttributes(PreparedStatement pstmt, Character character, int startIdx) throws SQLException {
        int mana = 0, intelligence = 0, strength = 0, armor = 0, agility = 0, endurance = 0;

        if (character instanceof Magier magier) {
            mana = magier.getMaxMana();
            intelligence = magier.getIntelligence();
        } else if (character instanceof Krieger krieger) {
            strength = krieger.getStrength();
            armor = krieger.getArmor();
        } else if (character instanceof Spaeher spaeher) {
            agility = spaeher.getAgility();
            endurance = spaeher.getEndurance();
        }

        pstmt.setInt(startIdx, mana);
        pstmt.setInt(startIdx + 1, intelligence);
        pstmt.setInt(startIdx + 2, strength);
        pstmt.setInt(startIdx + 3, armor);
        pstmt.setInt(startIdx + 4, agility);
        pstmt.setInt(startIdx + 5, endurance);

    }


    /**
     * Retrieves and displays all characters from the database.
     *
     * @return true if characters are found, false otherwise.
     */
    public boolean showAllCharacters() {
        String SELECT_ALL_CHARACTERS_SQL = "SELECT * FROM character";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_CHARACTERS_SQL)) {

            // Check if there are any characters in the ResultSet
            boolean hasCharacters = false;

            // Iterate the result set and print character details
            while (rs.next()) {
                if (!hasCharacters) {// only the first time
                    System.out.println("\nListe der gespeicherten Charaktere:");
                }
                printCharacterDetails(rs);
                hasCharacters = true;
            }
            return hasCharacters;
        } catch (SQLException e) {
            System.err.println("Error fetching characters: " + e.getMessage());
        }
        return false;
    }

    /**
     * Prints the details of a character from the ResultSet.
     *
     * @param rs the ResultSet containing character data.
     * @throws SQLException if an SQL error occurs.
     */
    private void printCharacterDetails(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String characterClass = rs.getString("class");
        int level = rs.getInt("level");
        int xp = rs.getInt("xp");
        int health = rs.getInt("health");

        System.out.print("ID: [" + id + "] Name: " + name + ", Klasse: " + characterClass
                + ", Level: " + level + ", XP: " + xp + ", HP: " + health);

        // Display additional attributes based on character class
        switch (characterClass) {
            case "Magier" -> {
                int mana = rs.getInt("mana");
                int intelligence = rs.getInt("intelligence");
                System.out.print(", Mana: " + mana + ", Intelligenz: " + intelligence);
            }
            case "Krieger" -> {
                int strength = rs.getInt("strength");
                int armor = rs.getInt("armor");
                System.out.print(", Staerke: " + strength + ", Rüstung: " + armor);
            }
            case "Spaeher" -> {
                int agility = rs.getInt("agility");
                int endurance = rs.getInt("endurance");
                System.out.print(", Beweglichkeit: " + agility + ", Ausdauer: " + endurance);
            }
        }
        System.out.println();
    }

    /**
     * @return how many characters are saved in the database
     */
    public int countCharacters() {
        String SELECT_COUNT_CHARACTERS_SQL = "SELECT COUNT(*) FROM character";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_COUNT_CHARACTERS_SQL)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error counting characters: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Retrieves a character by ID from the database.
     *
     * @param selectedId the ID of the character to retrieve.
     * @return the character object (Magier, Krieger, or Spaeher), or null if not found.
     */
    public Character getCharacter(int selectedId) {
        String SELECT_CHARACTER_BY_ID_SQL = "SELECT * FROM character WHERE id =?";
        try (PreparedStatement stmt = getConnection().prepareStatement(SELECT_CHARACTER_BY_ID_SQL)) {

            stmt.setInt(1, selectedId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return createCharacterFromResultSet(rs);
                } else {
                    // No character found
                    System.out.println("Kein Charakter mit der ID " + selectedId + " gefunden.");
                    return null;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching character by ID: " + e.getMessage());
            return null;
        }
    }

    /**
     * Creates a character object from the ResultSet.
     *
     * @param rs the ResultSet containing character data.
     * @return the character object (Magier, Krieger, or Spaeher).
     * @throws SQLException if an SQL error occurs.
     */
    private Character createCharacterFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String characterClass = rs.getString("class");
        int level = rs.getInt("level");
        int xp = rs.getInt("xp");
        int health = rs.getInt("health");

        return switch (characterClass) {
            case "Magier" -> new Magier(id, name, health, level, xp, rs.getInt("intelligence"), rs.getInt("mana"));
            case "Krieger" -> new Krieger(id, name, health, level, xp, rs.getInt("strength"), rs.getInt("armor"));
            case "Spaeher" -> new Spaeher(id, name, health, level, xp, rs.getInt("agility"), rs.getInt("endurance"));
            default -> throw new IllegalArgumentException("Unknown character class: " + characterClass);
        };
    }

}