import java.util.Random;

/**
 * The Spaeher (Scout) class represents a fast and agile character type.
 * It has attributes such as agility and endurance, which influence its combat effectiveness.
 */
public class Spaeher extends Character {
    private static final Random rand = new Random(); // Shared Random instance to optimize performance
    private int agility;
    private int endurance;
    private int maxEndurance;

    // Constructors
    public Spaeher(int id, String name, int health, int level, int xp, int agility, int endurance) {
        super(id, name, health, level, xp);
        this.agility = agility;
        this.endurance = endurance;
        this.maxEndurance = endurance;
    }

    public Spaeher(String name, int xp) {
        super(name, xp);
        setValues(xp);
    }

    public Spaeher(String name) {
        this(name, 0);
    }

    // Getter und Setter
    public int getAgility() {
        return agility;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getMaxEndurance() {
        return maxEndurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = Math.max(0, Math.min(endurance, maxEndurance));
    }

    /**
     * Sets the character's values based on XP.
     * The agility and endurance increase as the character gains more experience.
     */
    @Override
    public void setValues(int xp) {
        super.setCharacterValues(xp);
        this.maxEndurance = 50 + getLevel() * 5;
        this.endurance = this.maxEndurance;
        this.agility = 10 + getLevel() * 2;
    }

    /**
     * Resets the character's health and endurance to its maximum value
     */
    @Override
    void resetCharacterStats() {
        super.resetCharacterStats();
        endurance = maxEndurance;
    }

    /**
     * Determines if the character successfully dodges an attack.
     * Dodge probability is influenced by agility and remaining endurance.
     *
     * @return true if the attack is dodged, otherwise false.
     */
    @Override
    public boolean dodge() {
        boolean dodged = randomChanceBasedOnAgility();
        if (endurance > maxEndurance * 0.2 && dodged) {
            endurance -= 3;
        } else {
            dodged = false;
        }
        return super.dodge() || dodged;
    }

    /**
     * Executes an attack against an opponent.
     * The Spaeher (scout) has a chance to heal if health is low and can land critical hits based on agility.
     */
    @Override
    public void attack(Character opponent) {

        // If health is below 30%, 50% chance to heal instead of attacking.
        if (this.getHealth() < getMaxHealth() * 0.3 && rand.nextBoolean()) {
            this.heal();
        } else if (opponent.dodge()) {
            System.out.println(getName() + " greift an; " + opponent.getName() + " ist dem Angriff von " + getName() + " ausgewichen!");
        } else {
            int damage = calculateDamage();

            opponent.takeDamage(damage);
            System.out.println(getName() + " greift " + opponent.getName() +
                    " an und verursacht " + opponent.takeDamage(damage) + " Schaden!");
        }
    }

    /**
     * Calculates the damage dealt by the Spaeher based on agility and endurance.
     * If endurance is low, the attack is weaker, but some endurance is regained.
     *
     * @return the calculated damage value
     */
    private int calculateDamage() {
        if (endurance >= 5) {
            int baseDamage = agility + randomIncrease(agility);
            if (randomChanceBasedOnAgility()) {
                System.out.print("Kritischer Treffer! ");
                return baseDamage * 2;
            }
            setEndurance(endurance - 5);
            return baseDamage;
        } else {
            System.out.println(getName() + " hat nicht genug Ausdauer für einen gezielten Angriff.");
            endurance += randomIncrease(3);// Regenerate some endurance
            return randomIncrease(agility / 2);
        }
    }

    /**
     * Determines whether a random event happens based on the Spaeher's agility.
     * Higher agility increases the chance of success.
     *
     * @return true if the event occurs, otherwise false.
     */
    private boolean randomChanceBasedOnAgility() {
        double chance = agility / (100.0 + agility);
        return rand.nextDouble() < chance;
    }

    /**
     * Returns a formatted string representation of the Spaeher (scout).
     *
     * @return a string containing the character's name, endurance, and agility.
     */
    @Override
    public String toString() {
        return "[" + super.toString() +
                ", ausdauer=" + endurance + "/" + maxEndurance +
                ", beweglichkeit=" + agility +
                ']';
    }

}
