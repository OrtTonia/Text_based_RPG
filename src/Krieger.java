import java.util.Random;

/**
 * Warrior class representing a strong and heavily armored character.
 * has a Berserker mode: Increases damage if health is below 25% and cant doge anymore
 */
public class Krieger extends Character {
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
     */
    @Override
    public void setValues(int xp) {
        super.setCharacterValues(xp);
        this.strength = 15 + (xp / 10) * 2;
        this.armor = strength / 2;
    }

    /**
     * Determines if the warrior dodges an attack.
     * The warrior cannot dodge if health is below 25%.
     */
    @Override
    public boolean dodge() {
        return (this.getHealth() >= getMaxHealth() * 0.25) && super.dodge();
    }

    /**
     * Attacks an opponent, considering warrior-specific damage calculations.
     * Includes a berserker mechanic where damage increases if health is below 25%.
     */
    @Override
    public void attack(Character opponent) {
        Random rand = new Random();
        if (opponent.dodge()) {
            System.out.println(getName() + " greift an; " + opponent.getName() + " ist dem Angriff von " + getName() + " ausgewichen!");
        } else {
            int damage = strength + rand.nextInt(strength) + 10;

            // Berserker mode: Increases damage if health is below 25%
            if (this.getHealth() < getMaxHealth() * 0.25 && rand.nextBoolean()) {
                damage += rand.nextInt(strength);
            }
            System.out.println(getName() + " greift " + opponent.getName() +
                    " an und verursacht " + opponent.takeDamage(damage) + " Schaden!");

        }
    }

    /**
     * Reduces damage taken by the armor value, ensuring at least 1 damage is received.
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
