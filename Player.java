import java.util.*;

public class Player implements Performance, SuperPower {
  // variables
  private String name;
  private Hero hero;
  private boolean superPowerIsUsed;
  private static final Spell spell1 = new Spell(SuperPower.Minimum_spell_damage, 400);
  private static final Spell spell2 = new Spell(SuperPower.Minimum_spell_damage * 2, 800);
  private static final Spell spell3 = new Spell(SuperPower.Minimum_spell_damage * 3, 1200);

  // constructors
  public Player(String name) {
    this.name = name;
    this.hero = null;
    this.superPowerIsUsed = false;
  }

  // methods
  public void createHero(int num) {
    switch (num) {
      case 1: {
        String tempName = "Strength";
        int tempLife = 2000 + rand(500);
        int tempDamage = 200 + rand(100);
        int tempMana = 600 + rand(800);
        int tempId = num * 1000 + rand(999);
        this.hero = new Hero(tempName, tempId, tempLife, tempMana, tempDamage);
        break;
      }
      case 2: {
        String tempName = "Agility";
        int tempLife = 1200 + rand(700);
        int tempDamage = 300 + rand(100);
        int tempMana = 800 + rand(800);
        int tempId = num * 1000 + rand(999);
        this.hero = new Hero(tempName, tempId, tempLife, tempMana, tempDamage);
        break;
      }
      case 3: {
        String tempName = "Intelligence";
        int tempLife = 1200 + rand(700);
        int tempDamage = 100 + rand(100);
        int tempMana = 1600 + rand(500);
        int tempId = num * 1000 + rand(999);
        this.hero = new Hero(tempName, tempId, tempLife, tempMana, tempDamage);
        break;
      }
      default: {
        System.err.println(
            "EOROR!! Player.java in createHero(int) input value" +
                "is Not supported. supported values are just {1, 2, 3}");
      }
    }
  }

  public boolean spell(int num, Hero enemy) {
    switch (num) {
      case 1: {
        if (spell1.manaCost() > hero.mana())
          return false;
        hero.spell(enemy, spell1);
        return true;
      }
      case 2: {
        if (spell2.manaCost() > hero.mana())
          return false;
        hero.spell(enemy, spell2);
        return true;
      }
      case 3: {
        if (spell3.manaCost() > hero.mana())
          return false;
        hero.spell(enemy, spell3);
        return true;
      }
      default: {
        System.err.println(
            "EOROR!! Player.java in spell(int) input value" +
                "is Not supported. supported values are just {1, 2, 3}");
        return false;
      }
    }
  }

  // getters
  public String name() {
    return name;
  }

  public Hero hero() {
    return hero;
  }

  // setters
  public void name(String name) {
    this.name = name;
  }

  public void hero(Hero hero) {
    this.hero = hero;
  }

  // permition chekers
  public boolean superPowerPermition() {
    return !superPowerIsUsed;
  }

  public boolean spellPermition(int num) {
    switch (num) {
      case 1: {
        if (spell1.manaCost() > hero.mana())
          return false;
        return true;
      }
      case 2: {
        if (spell2.manaCost() > hero.mana())
          return false;
        return true;
      }
      case 3: {
        if (spell3.manaCost() > hero.mana())
          return false;
        return true;
      }
      default: {
        return false;
      }
    }
  }

  // Performance
  public void Attack(Player enemy) {
    enemy.hero().addLife(-1 * hero.damage());
    // System.out.println("damage: "+hero.damage());
  }

  public void Heal() {
    hero.addLife(Performance.heal);
  }

  // SuperPower
  public void superPowerAttack(Hero enemy) {
    enemy.addLife(-1 * SuperPower.damage);
  }

  public void superPowerHeal() {
    hero.addLife(SuperPower.heal);
  }

  public boolean useSuperPower(Hero enemy) {
    if (superPowerIsUsed)
      return false;
    this.superPowerAttack(enemy);
    this.superPowerHeal();
    superPowerIsUsed = true;
    return true;
  }

  // more functions
  // Random function can be usefull
  private static int rand(int uper) {
    Random rand = new Random();
    int x = rand.nextInt(uper + 1);
    return x;
  }
  // private static int rand(int lower, int uper) {
  // Random rand = new Random();
  // int x = rand.nextInt((uper - lower) + 1) + lower;
  // return x;
  // }

}
