import java.util.Random;

/**
 * The Spaeher (Scout) class represents a fast and agile character type.
 * It has attributes such as agility and endurance, which influence its combat effectiveness.
 */
public class Spaeher extends Character {
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
     */
    @Override
    public void setValues(int xp) {
        super.setCharacterValues(xp);
        this.maxEndurance = 50 + (xp / 10) * 5;
        this.endurance = this.maxEndurance;
        this.agility = 10 + (xp / 10) * 2;
    }


    /**
     * Executes an attack against an opponent.
     * The Spaeher (scout) has a chance to heal if health is low, and critical hit chance based on agility.
     */
    @Override
    public void attack(Character opponent) {
        Random rand = new Random();

        // If health is below 30%, 50% chance to heal instead of attacking.
        if (this.getHealth() < getMaxHealth() * 0.3 && rand.nextBoolean()) {
            this.heal();
        } else if (opponent.dodge()) {
            System.out.println(getName() + " greift an; " + opponent.getName() + " ist dem Angriff von " + getName() + " ausgewichen!");
        } else {
            int damage;
            if (endurance >= 5) {
                damage = agility + randomIncrease(agility);
                // Critical hit chance based on agility
                if (rand.nextDouble() < (agility / (100.0 + agility - 10))) {
                    damage *= 2; // Critical hit doubles damage
                    System.out.print("Kritischer Treffer! ");
                }
                setEndurance(endurance - 9); // Reduce endurance after attack.
            } else {
                System.out.println(getName() + " hat nicht genug Ausdauer für einen gezielten Angriff.");
                damage = randomIncrease(agility / 2);
                endurance += randomIncrease(3);  // Regenerate some endurance.
            }

            opponent.takeDamage(damage);
            System.out.println(getName() + " greift " + opponent.getName() +
                    " an und verursacht " + opponent.takeDamage(damage) + " Schaden!");
        }
    }

    @Override
    public String toString() {
        return "[" + super.toString() +
                ", ausdauer=" + endurance + "/" + maxEndurance +
                ", beweglichkeit=" + agility +
                ']';
    }

}
