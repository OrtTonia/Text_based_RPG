import java.util.Random;

/**
 * Abstract class representing a character in the game.
 * Contains fundamental attributes such as name, level, XP, and health.
 */
public abstract class Character {

    private int id;
    private String name;
    private int level;
    private int xp;
    private int health;
    private int maxHealth;

    /**
     * Constructor with all parameters
     */
    public Character(int id, String name, int maxHealth, int level, int xp) {
        this.id = id;
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.level = level;
        this.xp = xp;
    }

    /**
     * Constructor with name and XP, calculates other values automatically
     */
    public Character(String name, int xp) {
        this.name = name;
        setValues(xp);
    }

    /**
     * Constructor with default values
     */
    public Character(String name) {
        this(name, 0);
    }


    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(health, maxHealth)); // Ensures health is within valid range
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }


    /**
     * Sets character values based on XP
     */
    protected void setCharacterValues(int xp) {
        this.level = xp / 10;
        this.xp = xp;
        this.maxHealth = 100 + level * 10;
        this.health = maxHealth;
    }

    abstract void setValues(int xp);

    /**
     * Increases XP and triggers level-up if needed.
     */
    public void increaseXp(int xp) {
        setXp(this.xp + Math.abs(xp));
        //Level UP
        if (getXp() / 10 > getLevel()) {
            this.setValues(getXp());
            System.out.println(getName() + " hat level " + getLevel() + " erreicht!");
        }
    }

    /**
     * Calculates received damage and reduces health accordingly.
     *
     * @return Actual damage taken.
     */
    public int takeDamage(int damage) {
        int oldHealth = getHealth();
        setHealth(this.health - damage);
        return oldHealth - getHealth();
    }

    /**
     * Performs healing between 30% and 60% of max HP.
     */
    public void heal() {
        int healing = new Random().nextInt((int) (maxHealth * 0.3), (int) (maxHealth * 0.6));
        setHealth(this.health + healing);
        System.out.println(name + " heilt sich um " + healing + " Punkte!");
    }

    /**
     * Returns a random increase up to the given maximum.
     */
    int randomIncrease(int max) {
        return (int) (Math.random() * (max + 1));
    }

    /**
     * Determines whether the character dodges an attack (30% chance).
     */
    public boolean dodge() {
        return Math.random() < 0.3;
    }

    // Abstract attack method
    public abstract void attack(Character opponent);

    /**
     * Provides a short string representation of the character.
     */
    public String toSmallString() {
        return "[" + name +
                ": Klasse=" + this.getClass().getSimpleName() +
                ", Level=" + level +
                ", XP=" + xp +
                ", HP=" + health + "/" + maxHealth + "]";
    }

    @Override
    public String toString() {
        return "Name=" + name +
                ", Klasse=" + this.getClass().getSimpleName() +
                ", Level=" + level +
                ", XP=" + xp +
                ", HP=" + health + "/" + maxHealth;

    }

}

