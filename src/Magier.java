import java.util.Random;

/**
 * The Magier (Mage) class represents a spellcaster with mana and intelligence attributes.
 * Mages use mana to perform magical attacks and must manage their mana effectively during combat.
 * @author ortenburger
 */
public class Magier extends Character {
    private static final Random rand = new Random();
    private int mana;
    private int maxMana;
    private int intelligence;

    // Constructor
    public Magier(int id, String name, int health, int level, int xp, int intelligence, int mana) {
        super(id, name, health, level, xp);
        this.mana = mana;
        this.maxMana = mana;
        this.intelligence = intelligence;
    }

    public Magier(String name, int xp) {
        super(name, xp);
        setValues(xp);
    }

    public Magier(String name) {
        this(name, 0);
    }

    // Getter und Setter
    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setMana(int mana) {
        this.mana = Math.max(0, Math.min(mana, maxMana));
    }

    /**
     * Sets character attributes based on experience points (XP).
     * Intelligence and mana increase as the character gains more XP.
     */
    @Override
    public void setValues(int xp) {
        super.setCharacterValues(xp);
        this.maxMana = 50 + getLevel() * 5;
        this.mana = maxMana;
        this.intelligence = 10 + getLevel() * 2;
    }

    /**
     * Resets character stats such as health and mana.
     */
    @Override
    void resetCharacterStats() {
        super.resetCharacterStats();
        mana = maxMana;
    }

    /**
     * Executes an attack against an opponent.
     * Mages consume mana to deal magical damage. If mana is low, they regenerate instead of attacking.
     *
     * @param opponent The opponent being attacked.
     */
    @Override
    public void attack(Character opponent) {
        if (this.getHealth() < getMaxHealth() * 0.4 && rand.nextBoolean()) {
            this.heal();
        } else if (opponent.dodge()) {
            System.out.println(getName() + " greift an; " + opponent.getName() + " schafft es auszuweichen!");
        } else {
            int damage = calculateDamage();

            String damageType = (mana >= 5) ? " magischen" : " schwachen";

            opponent.takeDamage(damage);
            System.out.println(getName() + " greift " + opponent.getName() +
                    " an und verursacht " + opponent.takeDamage(damage) + damageType + " Schaden!");

        }
    }

    /**
     * Calculates the damage dealt by the Mage.
     * If mana is low, the attack is weak, and some mana is regenerated instead.
     *
     * @return the calculated damage value.
     */
    private int calculateDamage() {
        if (mana < 5) {
            System.out.println(getName() + " hat nicht genug Mana für einen magischen Angriff.");
            setMana(getMana() + randomIncrease(4)); // Regenerate 0-4 mana
            return rand.nextInt(1, 6); // Weak attack
        }

        mana -= 5; // Spend mana for the attack
        return intelligence * 2 + randomIncrease(intelligence);
    }

    /**
     * Returns a formatted string representation of the Mage.
     *
     * @return a string containing the character's mana and intelligence values.
     */
    @Override
    public String toString() {
        return "[" + super.toString() +
                ", mana=" + mana + "/" + maxMana +
                ", intelligenz=" + intelligence +
                "]";
    }

}
