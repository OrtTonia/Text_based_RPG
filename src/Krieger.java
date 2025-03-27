import java.util.Random;

/**
 * Warrior class representing a strong and heavily armored character.
 * It features a Berserker mode: If health drops below 25%, the warrior deals increased damage but can no longer dodge attacks.
 * @author ortenburger
 */
public class Krieger extends Character {
    private static final Random rand = new Random(); // Shared Random instance to optimize performance
    private int strength;
    private int armor;

    // Constructor
    public Krieger(int id, String name, int gesundheit, int level, int xp, int strength, int armor) {
        super(id, name, gesundheit, level, xp);
        this.strength = strength;
        this.armor = armor;
    }

    public Krieger(String name, int xp) {
        super(name, xp);
        setValues(xp);
    }

    public Krieger(String name) {
        this(name, 0);
    }

    // Getter und Setter
    public int getStrength() {
        return strength;
    }

    public int getArmor() {
        return armor;
    }

    /**
     * Sets the warrior's attributes based on XP.
     * Strength increases with experience, and armor is set to half the strength value.
     */
    @Override
    public void setValues(int xp) {
        super.setCharacterValues(xp);
        this.strength = 15 + getLevel() * 2;
        this.armor = strength / 2;
    }

    /**
     * Determines whether the warrior can dodge an attack.
     * If the warrior's health is below 25%, dodging is disabled due to Berserker mode.
     *
     * @return true if the warrior successfully dodges, otherwise false.
     */
    @Override
    public boolean dodge() {
        return (this.getHealth() >= getMaxHealth() * 0.25) && super.dodge();
    }

    /**
     * Executes an attack against an opponent.
     * The warrior's attack includes a base damage calculation and a chance for Berserker mode to further increase damage.
     *
     * @param opponent The opponent being attacked.
     */
    @Override
    public void attack(Character opponent) {

        if (opponent.dodge()) {
            System.out.println(getName() + " greift an; " + opponent.getName() + " ist dem Angriff von " + getName() + " ausgewichen!");
            return;
        }
        int damage = calculateDamage();
        System.out.println(getName() + " greift " + opponent.getName() +
                " an und verursacht " + opponent.takeDamage(damage) + " Schaden!");
    }


    /**
     * Calculates the damage dealt by the warrior.
     * Includes a Berserker mode where damage is increased when health is below 25%.
     *
     * @return the calculated damage value.
     */
    private int calculateDamage() {
        int baseDamage = strength + rand.nextInt(strength) + 10;

        // Berserker mode: Additional damage when health is low.
        if (this.getHealth() < getMaxHealth() * 0.25 && rand.nextBoolean()) {
            baseDamage += rand.nextInt(strength);
        }
        return baseDamage;
    }

    /**
     * Reduces incoming damage based on armor value, ensuring at least 1 damage is taken.
     *
     * @param damage The incoming damage value.
     * @return The final damage received after armor reduction.
     */
    @Override
    public int takeDamage(int damage) {
        return super.takeDamage(Math.max(damage - armor, 1));
    }


    @Override
    public String toString() {
        return "[" + super.toString() +
                ", staerke=" + strength +
                ", ruestung=" + armor +
                ']';
    }

}
