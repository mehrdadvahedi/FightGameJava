public class Hero {
  // variables
  private String name;
  private int id;
  private int life;
  private int mana;
  private int damage;

  // constructors
  public Hero(String name, int id, int life, int mana, int damage) {
    this.name = name;
    this.id = id;
    this.life = life;
    this.mana = mana;
    this.damage = damage;
  }

  // getters
  public String name() {
    return name;
  }

  public int id() {
    return id;
  }

  public int life() {
    return life;
  }

  public int mana() {
    return mana;
  }

  public int damage() {
    return damage;
  }

  // setters
  public void name(String name) {
    this.name = name;
  }

  public void id(int id) {
    this.id = id;
  }

  public void life(int life) {
    this.life = life;
  }

  public void mana(int mana) {
    this.mana = mana;
  }

  public void damage(int damage) {
    this.damage = damage;
  }

  // these adders can be more usefull than setters :)
  public void addLife(int add) {
    this.life += add;
  }

  public void addMana(int add) {
    this.mana += add;
  }

  // methods
  public void spell(Hero enemy, Spell spell) {
    this.addMana(-1 * spell.manaCost());
    enemy.addLife(-1 * spell.damage());
  }

}
