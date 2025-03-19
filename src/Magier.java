import java.util.Random;

/**
 * Magier (Mage) class, representing a spellcaster with mana and intelligence attributes.
 * Can perform magical attacks and uses mana.
 */
public class Magier extends Character {
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
     * Sets character attributes based on XP.
     */
    @Override
    public void setValues(int xp) {
        super.setCharacterValues(xp);
        this.maxMana = 50 + (xp / 10) * 5;
        this.mana = maxMana;
        this.intelligence = 10 + (xp / 10) * 2;
    }


    /**
     * Mage attack logic. Uses mana to cast spells, with an option to regenerate mana if depleted.
     */
    @Override
    public void attack(Character opponent) {
        Random rand = new Random();
        if (this.getHealth() < getMaxHealth() * 0.4 && rand.nextBoolean()) {
            this.heal();
        } else if (opponent.dodge()) {
            System.out.println(getName() + " greift an; " + opponent.getName() + " schafft es auszuweichen!");
        } else {
            int damage;
            String damageType = "";
            if (mana < 5) {
                // even if the character has not enough mana, they can do a bit of damage and regenerate mana
                System.out.println(getName() + " hat nicht genug Mana für einen magischen Angriff.");
                damage = rand.nextInt(1, 6);
                setMana(getMana() + randomIncrease(4)); // Regenerate 0-4 mana
            } else {
                damageType = " magischen";
                damage = this.intelligence * 2 + randomIncrease(intelligence);
                mana -= 5;
            }
            opponent.takeDamage(damage);
            System.out.println(getName() + " greift " + opponent.getName() +
                    " an und verursacht " + opponent.takeDamage(damage) + damageType + " Schaden!");

        }
    }


    @Override
    public String toString() {
        return "[" + super.toString() +
                ", mana=" + mana + "/" + maxMana +
                ", intelligenz=" + intelligence +
                "]";
    }

}
