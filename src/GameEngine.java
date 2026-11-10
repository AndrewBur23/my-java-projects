public class GameEngine {
    public static void main(String[] args) {

        System.out.println("--- Minecraft Entity Engine Started ---");

        Entity cow = new Entity("Cow", 20);

        Player steve = new Player("Steve", 23, 8);

        Zombie regularZombie = new Zombie("Zombie Bob", 33, 7);

        regularZombie.bite(cow);
        regularZombie.bite(steve);
        steve.hit(regularZombie);

        System.out.println("\n--- Morning comes ---");
        regularZombie.burnUnderSun();

    }
}

class Entity {
    String name;
    int health;

    public Entity(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public void takeDamage(int damage) {

        int oldHealth = this.health;
        this.health -= damage;

        System.out.println(name + " HP: " + oldHealth + " -> " + this.health + " (Took " + damage + " damage)");
    }

}

class Player extends Entity {
    int weaponDamage;

    public Player(String name, int health, int weaponDamage) {
        super(name, health);
        this.weaponDamage = weaponDamage;
    }

    public void hit(Entity target) {
        System.out.println(this.name + " hits " + target.name + " with a sword!");
        target.takeDamage(this.weaponDamage);
    }
}


class Zombie extends Entity implements Burnable {
    int attackPower;

    public Zombie(String name, int health, int attackPower) {
        super(name, health);
        this.attackPower = attackPower;
    }

    public void bite(Entity target) {
        System.out.println(this.name + " bites " + target.name + "!");
        target.takeDamage(this.attackPower);
    }

    @Override
    public void burnUnderSun() {
        System.out.println(this.name + " is burning under the sun!");
        this.takeDamage(5);
    }
}

interface Burnable {
    void burnUnderSun();
}