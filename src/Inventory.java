public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private int food;
    private int firewood;
    private int water;

    public Inventory() {
        this.weapon = new Weapon("Yumruk", -1, 0, 0);
        this.armor = new Armor(-1, "Paçavra", 0, 0);
        this.food = food;
        this.firewood = firewood;
        this.water = water;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getFirewood() {
        return firewood;
    }

    public void setFirewood(int firewood) {
        this.firewood = firewood;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }
}
