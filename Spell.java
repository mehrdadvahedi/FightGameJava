public class Spell {

  // variables
  private int damage;
  private int manaCost;

  // constructors
  public Spell(int damage, int manaCost) {
    this.damage = damage;
    this.manaCost = manaCost;
  }

  // getters
  public int damage() {
    return this.damage;
  }

  public int manaCost() {
    return this.manaCost;
  }

  // setters
  public void damage(int damage) {
    this.damage = damage;
  }

  public void manaCost(int manaCost) {
    this.manaCost = manaCost;
  }

}
