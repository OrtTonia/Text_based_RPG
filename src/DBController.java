import java.sql.*;

/**
 * DBController handles all database operations related to character storage and retrieval.
 */
public class DBController {

    // Database URL for SQLite connection
    private final String URL = "jdbc:sqlite:characters.db";

    // SQL Queries as constants to avoid duplication
    private final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS character (" +
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

    private final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS character";

    private final String INSERT_CHARACTER_SQL = "INSERT INTO character(name, class, level, xp, health, mana, intelligence, strength, armor, agility, endurance) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final String SELECT_ALL_CHARACTERS_SQL = "SELECT * FROM character";

    private final String SELECT_CHARACTER_BY_ID_SQL = "SELECT * FROM character WHERE id =?";

    private final String UPDATE_CHARACTER_SQL = "UPDATE character SET level=?, xp=?, health=?, mana=?, intelligence=?, strength=?, armor=?, agility=?, endurance=? WHERE id=?";

    private final String SELECT_LAST_CHARACTERS_SQL = "SELECT * FROM character ORDER BY ID DESC LIMIT 1";

    /**
     * Creates the "character" table in the database if it doesn't already exist.
     */
    public void createTableCharacter() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            //stmt.executeUpdate(DROP_TABLE_SQL);
            stmt.execute(CREATE_TABLE_SQL);
        } catch (SQLException e) {
            // Log detailed error message
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public void dropTableCharacter() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(DROP_TABLE_SQL);
        } catch (SQLException e) {
            // Log detailed error message
            System.err.println("Error dropping table: " + e.getMessage());
        }
    }

    /**
     * Saves a character to the database.
     *
     * @param character The character to be saved (could be Magier, Krieger, or Spaeher).
     */
    public void saveNewCharacter(Character character) {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(INSERT_CHARACTER_SQL)) {

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

            System.out.println("Dein  " + character.getClass().getSimpleName() + " " + character.getName() + " wurde gespeichert.");
        } catch (SQLException e) {
            System.err.println("Error saving character: " + e.getMessage());
        }
    }

    /**
     * Updates the ID of the character after it has been saved.
     *
     * @param character The character whose ID needs to be updated.
     */
    private void updateIdOfCharacter(Character character) {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
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
     * @param character The character whose data needs to be updated.
     */
    public void updateCharacter(Character character) {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_CHARACTER_SQL)) {

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
     * @param pstmt     The prepared statement to set attributes in.
     * @param character The character object containing the data.
     * @param startIdx  The starting index for setting attributes.
     * @throws SQLException If an SQL error occurs.
     */
    private static void setCharacterAttributes(PreparedStatement pstmt, Character character, int startIdx) throws SQLException {
        if (character instanceof Magier) {
            pstmt.setInt(startIdx, ((Magier) character).getMaxMana());
            pstmt.setInt(startIdx + 1, ((Magier) character).getIntelligence());
            pstmt.setNull(startIdx + 2, Types.INTEGER); // Set null for fields not needed
            pstmt.setNull(startIdx + 3, Types.INTEGER);
            pstmt.setNull(startIdx + 4, Types.INTEGER);
            pstmt.setNull(startIdx + 5, Types.INTEGER);
        } else if (character instanceof Krieger) {
            pstmt.setNull(startIdx, Types.INTEGER);
            pstmt.setNull(startIdx + 1, Types.INTEGER);
            pstmt.setInt(startIdx + 2, ((Krieger) character).getStrength());
            pstmt.setInt(startIdx + 3, ((Krieger) character).getArmor());
            pstmt.setNull(startIdx + 4, Types.INTEGER);
            pstmt.setNull(startIdx + 5, Types.INTEGER);
        } else if (character instanceof Spaeher) {
            pstmt.setNull(startIdx, Types.INTEGER);
            pstmt.setNull(startIdx + 1, Types.INTEGER);
            pstmt.setNull(startIdx + 2, Types.INTEGER);
            pstmt.setNull(startIdx + 3, Types.INTEGER);
            pstmt.setInt(startIdx + 4, ((Spaeher) character).getAgility());
            pstmt.setInt(startIdx + 5, ((Spaeher) character).getEndurance());
        }
    }


    /**
     * Retrieves and displays all characters from the database.
     *
     * @return true if characters are found, false otherwise.
     */
    public boolean showAllCharacters() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
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
     * @param rs The ResultSet containing character data.
     * @throws SQLException If an SQL error occurs.
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
     * Retrieves a character by ID from the database.
     *
     * @param selectedId The ID of the character to retrieve.
     * @return The character object (Magier, Krieger, or Spaeher), or null if not found.
     */
    public Character getCharacter(int selectedId) {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(SELECT_CHARACTER_BY_ID_SQL)) {

            stmt.setInt(1, selectedId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return createCharacterFromResultSet(rs);
                } else {
                    // No character found
                    System.out.println("Kein Charakter mit dieser ID gefunden.");
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
     * @param rs The ResultSet containing character data.
     * @return The character object (Magier, Krieger, or Spaeher).
     * @throws SQLException If an SQL error occurs.
     */
    private Character createCharacterFromResultSet(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String characterClass = rs.getString("class");
        int id = rs.getInt("id");
        int level = rs.getInt("level");
        int xp = rs.getInt("xp");
        int health = rs.getInt("health");

        switch (characterClass) {
            case "Magier" -> {
                int mana = rs.getInt("mana");
                int intelligence = rs.getInt("intelligence");
                return new Magier(id, name, health, level, xp, intelligence, mana);
            }
            case "Krieger" -> {
                int strength = rs.getInt("strength");
                int armor = rs.getInt("armor");
                return new Krieger(id, name, health, level, xp, strength, armor);
            }
            case "Spaeher" -> {
                int agility = rs.getInt("agility");
                int endurance = rs.getInt("endurance");
                return new Spaeher(id, name, health, level, xp, agility, endurance);
            }
        }
        return null;
    }

}