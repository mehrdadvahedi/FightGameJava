import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;

class MainFile {
  private static JFrame frame = new JFrame("Fight Game");
  private static JPanel startPagePanel = new JPanel();
  private static JPanel gamePagePanel = new JPanel();
  private static JPanel endPagePanel = new JPanel();
  private static final int width = 900;
  private static final int height = 500;
  private static int hero1 = 0;
  private static int hero2 = 0;
  private static boolean hero1Ok = false;
  private static boolean hero2Ok = false;
  private static String yellow = "#aaaaaa";
  private static String green = "#82E0AA";
  private static String purple = "#aa99ff";
  private static String nameL = "name test";
  private static String nameR = "name test";
  private static JLabel cl = new JLabel();
  private static JLabel cr = new JLabel();
  private static Player playerL = new Player("playerL");
  private static Player playerR = new Player("playerR");
  private static JLabel hl1 = new JLabel();
  private static JLabel hl2 = new JLabel();
  private static JLabel hl3 = new JLabel();
  private static JLabel hr1 = new JLabel();
  private static JLabel hr2 = new JLabel();
  private static JLabel hr3 = new JLabel();
  private static JButton vl1 = new JButton();
  private static JButton vl2 = new JButton();
  private static JButton vl3 = new JButton();
  private static JButton vl4 = new JButton();
  private static JButton vl5 = new JButton();
  private static JButton vl6 = new JButton();
  private static JButton vr1 = new JButton();
  private static JButton vr2 = new JButton();
  private static JButton vr3 = new JButton();
  private static JButton vr4 = new JButton();
  private static JButton vr5 = new JButton();
  private static JButton vr6 = new JButton();
  private static boolean turnR = true;
  private static boolean finishGame = false;
  private static String winner = "";
  private static int moveCount = 0;

  public static void hero1(int i) {
    hero1 = i;
  }

  public static void hero2(int i) {
    hero2 = i;
  }

  public static void hero1Ok() {
    hero1Ok = true;
  }

  public static void hero2Ok() {
    hero2Ok = true;
  }

  public static void switchStartPage2GamePage() {
    frame.add(gamePagePanel);
    // gamePagePanel.setVisible(true);
    startPagePanel.setVisible(false);
    String heroLIcon = heroIcon(hero1, "L");
    String heroRIcon = heroIcon(hero2, "R");
    cl.setIcon(new ImageIcon(heroLIcon));
    cr.setIcon(new ImageIcon(heroRIcon));
    playerL.createHero(hero1);
    playerR.createHero(hero2);
    update();
  }

  public static void switchGamePage2EndPage() {
    frame.add(endPagePanel);
    // gamePagePanel.setVisible(true);
    gamePagePanel.setVisible(false);
  }

  public static void main(String[] args) {
    frame.setBounds(0, 0, width, height);
    frame.setResizable(false);

    startPagePanel = startPage();
    gamePagePanel = gamePage();
    // frame.add(gamePagePanel);
    frame.add(startPagePanel);
    // TODO
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public static String heroIcon(int n, String LR) {
    String ans = "CH" + n + LR + "300.png";
    return ans;
  }

  public static void writeTxt() {
    try {

      FileWriter writer = new FileWriter("FinalAns.txt", true);
      writer.write(winner);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void update() {
    // TODO
    hl1.setText("" + (playerL.hero().life() <= 0 ? 0 : playerL.hero().life()));
    hl2.setText("" + playerL.hero().damage());
    hl3.setText("" + playerL.hero().mana());

    hr1.setText("" + (playerR.hero().life() <= 0 ? 0 : playerR.hero().life()));
    hr2.setText("" + playerR.hero().damage());
    hr3.setText("" + playerR.hero().mana());
    if (turnR) {
      vl1.setBackground(Color.white);
      vl2.setBackground(Color.white);
      // vl3.setBackground(Color.white);
      // vl4.setBackground(Color.white);
      // vl5.setBackground(Color.white);
      // vl6.setBackground(Color.white);
      vr1.setBackground(Color.gray);
      vr2.setBackground(Color.gray);
      // vr3.setBackground(Color.black);
      // vr4.setBackground(Color.black);
      // vr5.setBackground(Color.black);
      // vr6.setBackground(Color.black);
    } else {
      vr1.setBackground(Color.white);
      vr2.setBackground(Color.white);
      // vr3.setBackground(Color.white);
      // vr4.setBackground(Color.white);
      // vr5.setBackground(Color.white);
      // vr6.setBackground(Color.white);
      vl1.setBackground(Color.gray);
      vl2.setBackground(Color.gray);
      // vl3.setBackground(Color.black);
      // vl4.setBackground(Color.black);
      // vl5.setBackground(Color.black);
      // vl6.setBackground(Color.black);
    }
    if (playerL.spellPermition(1) && turnR) {
      vl3.setBackground(Color.white);
    } else {
      vl3.setBackground(Color.gray);
    }
    if (playerL.spellPermition(2) && turnR) {
      vl4.setBackground(Color.white);
    } else {
      vl4.setBackground(Color.gray);
    }
    if (playerL.spellPermition(3) && turnR) {
      vl5.setBackground(Color.white);
    } else {
      vl5.setBackground(Color.gray);
    }
    if (playerL.superPowerPermition() && turnR) {
      vl6.setBackground(Color.white);
    } else {
      vl6.setBackground(Color.gray);
    }

    if (playerR.spellPermition(1) && !turnR) {
      vr3.setBackground(Color.white);
    } else {
      vr3.setBackground(Color.gray);
    }
    if (playerR.spellPermition(2) && !turnR) {
      vr4.setBackground(Color.white);
    } else {
      vr4.setBackground(Color.gray);
    }
    if (playerR.spellPermition(3) && !turnR) {
      vr5.setBackground(Color.white);
    } else {
      vr5.setBackground(Color.gray);
    }
    if (playerR.superPowerPermition() && !turnR) {
      vr6.setBackground(Color.white);
    } else {
      vr6.setBackground(Color.gray);
    }

    // check finish game
    if (playerL.hero().life() <= 0 && !finishGame) {
      winner = "player right with character " + playerR.hero().name() + " win,\nGame finished in " + moveCount + " moves :)" + "\n\n";
      System.out.println(winner);
      finishGame = true;
      writeTxt();
      endPagePanel = endPage();
      switchGamePage2EndPage();
    }
    if (playerR.hero().life() <= 0 && !finishGame) {
      winner = "player left with character " + playerL.hero().name() + " win,\nGame finished in " + moveCount + " moves :)" + "\n\n";
      System.out.println(winner);
      finishGame = true;
      writeTxt();
      endPagePanel = endPage();
      switchGamePage2EndPage();
    }
    // turn change
    if (!finishGame) {
      turnR = !turnR;
    }
  }

  public static void AL_L() {
    vl1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (!turnR && !finishGame) {
          playerL.Heal();
          moveCount++;
          update();
        }
      }
    });
    vl2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (!turnR && !finishGame) {
          playerL.Attack(playerR);
          moveCount++;
          update();
        }
      }
    });
    vl3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (!turnR && playerL.spellPermition(1) && !finishGame) {
          playerL.spell(1, playerR.hero());
          moveCount++;
          update();
        }
      }
    });
    vl4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (!turnR && playerL.spellPermition(2) && !finishGame) {
          playerL.spell(2, playerR.hero());
          moveCount++;
          update();
        }
      }
    });
    vl5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (!turnR && playerL.spellPermition(3) && !finishGame) {
          playerL.spell(3, playerR.hero());
          moveCount++;
          update();
        }
      }
    });
    vl6.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (!turnR && playerL.superPowerPermition() && !finishGame) {
          playerL.useSuperPower(playerR.hero());
          moveCount++;
          update();
        }
      }
    });
  }

  public static void AL_R() {
    vr1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (turnR && !finishGame) {
          playerR.Heal();
          moveCount++;
          update();
        }
      }
    });
    vr2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (turnR && !finishGame) {
          playerR.Attack(playerL);
          moveCount++;
          update();
        }
      }
    });
    vr3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (turnR && playerR.spellPermition(1) && !finishGame) {
          playerR.spell(1, playerL.hero());
          moveCount++;
          update();
        }
      }
    });
    vr4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (turnR && playerR.spellPermition(2) && !finishGame) {
          playerR.spell(2, playerL.hero());
          moveCount++;
          update();
        }
      }
    });
    vr5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (turnR && playerR.spellPermition(3) && !finishGame) {
          playerR.spell(3, playerL.hero());
          moveCount++;
          update();
        }
      }
    });
    vr6.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (turnR && playerR.superPowerPermition() && !finishGame) {
          playerR.useSuperPower(playerL.hero());
          moveCount++;
          update();
        }
      }
    });
  }

  public static JPanel endPage() {
    JPanel panel = new JPanel();
    panel.setLayout(null);
    panel.setBackground(Color.decode(green));
    JLabel text = new JLabel(winner);
    text.setLayout(null);
    text.setBounds(50, 0, 800, 250);
    text.setFont(new Font("Arial", Font.PLAIN, 25));
    text.setForeground(Color.black);

    JButton exit = new JButton("Exit");
    exit.setLayout(null);
    exit.setBackground(Color.white);
    exit.setBounds(350, 200, 200, 30);

    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        frame.dispose();
      }
    });

    panel.add(text);
    panel.add(exit);
    return panel;
  }

  public static JPanel gamePage() {
    JPanel panel = new JPanel();
    panel.setBackground(Color.decode(green));
    panel.setLayout(new GridLayout(1, 2));

    JLabel left = new JLabel();
    left.setLayout(null);
    left.setBounds(0, 0, width / 2, height);
    left.setIcon(new ImageIcon("bg1.png"));

    JLabel vbarL = new JLabel();
    vbarL.setBounds(0, 0, 100, 465);
    vbarL.setLayout(new GridLayout(6, 1));

    JLabel hbarL = new JLabel();
    hbarL.setBounds(100, 0, 365, 100);
    hbarL.setLayout(new GridLayout(1, 3));

    vl1.setBackground(Color.black);
    vl1.setIcon(new ImageIcon("heart.png"));
    vl2.setBackground(Color.black);
    vl2.setIcon(new ImageIcon("fire.png"));
    vl3.setBackground(Color.black);
    vl3.setIcon(new ImageIcon("spell1.png"));
    vl4.setBackground(Color.black);
    vl4.setIcon(new ImageIcon("spell2.png"));
    vl5.setBackground(Color.black);
    vl5.setIcon(new ImageIcon("spell3.png"));
    vl6.setBackground(Color.black);
    vl6.setIcon(new ImageIcon("sp.png"));

    cl.setLayout(null);
    cl.setBounds(150, 200, 300, 300);

    hl1.setIcon(new ImageIcon("heart50.png"));
    hl1.setForeground(Color.white);
    hl2.setIcon(new ImageIcon("fire50.png"));
    hl2.setForeground(Color.white);
    hl3.setIcon(new ImageIcon("mana50.png"));
    hl3.setForeground(Color.white);
    hbarL.add(hl1);
    hbarL.add(hl2);
    hbarL.add(hl3);
    left.add(hbarL);

    vbarL.add(vl1);
    vbarL.add(vl2);
    vbarL.add(vl3);
    vbarL.add(vl4);
    vbarL.add(vl5);
    vbarL.add(vl6);
    left.add(vbarL);
    left.add(cl);

    JLabel right = new JLabel();
    right.setLayout(null);
    right.setBounds(0, 0, width / 2, height);
    right.setIcon(new ImageIcon("bg2.png"));

    JLabel vbarR = new JLabel();
    vbarR.setBounds(350, 0, 100, 465);
    vbarR.setLayout(new GridLayout(6, 1));

    vr1.setBackground(Color.black);
    vr1.setIcon(new ImageIcon("heart.png"));
    vr2.setBackground(Color.black);
    vr2.setIcon(new ImageIcon("fire.png"));
    vr3.setBackground(Color.black);
    vr3.setIcon(new ImageIcon("spell1.png"));
    vr4.setBackground(Color.black);
    vr4.setIcon(new ImageIcon("spell2.png"));
    vr5.setBackground(Color.black);
    vr5.setIcon(new ImageIcon("spell3.png"));
    vr6.setBackground(Color.black);
    vr6.setIcon(new ImageIcon("sp.png"));

    cr.setLayout(null);
    cr.setBounds(0, 200, 300, 300);

    JLabel hbarR = new JLabel();
    hbarR.setBounds(0, 0, 365, 100);
    hbarR.setLayout(new GridLayout(1, 3));

    hr1.setIcon(new ImageIcon("heart50.png"));
    hr1.setForeground(Color.white);
    hr2.setIcon(new ImageIcon("fire50.png"));
    hr2.setForeground(Color.white);
    hr3.setIcon(new ImageIcon("mana50.png"));
    hr3.setForeground(Color.white);
    hbarR.add(hr1);
    hbarR.add(hr2);
    hbarR.add(hr3);
    right.add(hbarR);

    vbarR.add(vr1);
    vbarR.add(vr2);
    vbarR.add(vr3);
    vbarR.add(vr4);
    vbarR.add(vr5);
    vbarR.add(vr6);

    right.add(vbarR);
    right.add(cr);

    panel.add(left);
    panel.add(right);
    AL_L();
    AL_R();
    return panel;
  }

  public static JPanel startPage() {
    JPanel panel = new JPanel();
    panel.setBackground(Color.decode(purple));
    panel.setLayout(null);
    panel.setLayout(new GridLayout(1, 3, 0, 0));

    JLabel right = new JLabel();
    JButton br1 = new JButton("Strength");
    br1.setBounds(100, 50, 200, 100);
    br1.setIcon(new ImageIcon("CH1R100.png"));
    br1.setBackground(Color.decode(yellow));

    JButton br2 = new JButton("Agility");
    br2.setBounds(100, 175, 200, 100);
    br2.setIcon(new ImageIcon("CH2R100.png"));
    br2.setBackground(Color.decode(yellow));

    JButton br3 = new JButton("Intelligence");
    br3.setBounds(100, 300, 200, 100);
    br3.setIcon(new ImageIcon("CH3R100.png"));
    br3.setBackground(Color.decode(yellow));

    br1.setFont(new Font("Arial", Font.PLAIN, 15));
    br1.setForeground(Color.black);
    br1.setHorizontalTextPosition(JButton.RIGHT);
    br1.setVerticalTextPosition(JButton.CENTER);

    br2.setFont(new Font("Arial", Font.PLAIN, 15));
    br2.setForeground(Color.black);
    br2.setHorizontalTextPosition(JButton.RIGHT);
    br2.setVerticalTextPosition(JButton.CENTER);

    br3.setFont(new Font("Arial", Font.PLAIN, 13));
    br3.setForeground(Color.black);
    br3.setHorizontalTextPosition(JButton.RIGHT);
    br3.setVerticalTextPosition(JButton.CENTER);

    JButton endr = new JButton("OK");
    endr.setBounds(0, 420, 100, 30);
    endr.setBackground(Color.white);

    right.add(br1);
    right.add(br2);
    right.add(br3);
    right.add(endr);

    JLabel left = new JLabel();
    JButton bl1 = new JButton("Strength");
    bl1.setBounds(100, 50, 200, 100);
    bl1.setIcon(new ImageIcon("CH1L100.png"));
    bl1.setBackground(Color.decode(yellow));

    JButton bl2 = new JButton("Agility");
    bl2.setBounds(100, 175, 200, 100);
    bl2.setIcon(new ImageIcon("CH2L100.png"));
    bl2.setBackground(Color.decode(yellow));

    JButton bl3 = new JButton("Intelligence");
    bl3.setBounds(100, 300, 200, 100);
    bl3.setIcon(new ImageIcon("CH3L100.png"));
    bl3.setBackground(Color.decode(yellow));

    bl1.setFont(new Font("Arial", Font.PLAIN, 15));
    bl1.setForeground(Color.black);
    bl1.setHorizontalTextPosition(JButton.LEFT);
    bl1.setVerticalTextPosition(JButton.CENTER);

    bl2.setFont(new Font("Arial", Font.PLAIN, 15));
    bl2.setForeground(Color.black);
    bl2.setHorizontalTextPosition(JButton.LEFT);
    bl2.setVerticalTextPosition(JButton.CENTER);

    bl3.setFont(new Font("Arial", Font.PLAIN, 13));
    bl3.setForeground(Color.black);
    bl3.setHorizontalTextPosition(JButton.LEFT);
    bl3.setVerticalTextPosition(JButton.CENTER);

    JButton endl = new JButton("OK");
    endl.setBounds(300, 420, 100, 30);
    endl.setBackground(Color.white);

    left.add(bl1);
    left.add(bl2);
    left.add(bl3);
    left.add(endl);

    bl1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        hero1(1);
        bl1.setBackground(Color.decode(green));
        bl2.setBackground(Color.decode(yellow));
        bl3.setBackground(Color.decode(yellow));
      }
    });

    bl2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        hero1(2);
        bl1.setBackground(Color.decode(yellow));
        bl2.setBackground(Color.decode(green));
        bl3.setBackground(Color.decode(yellow));
      }
    });

    bl3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        hero1(3);
        bl1.setBackground(Color.decode(yellow));
        bl2.setBackground(Color.decode(yellow));
        bl3.setBackground(Color.decode(green));
      }
    });

    br1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        hero2(1);
        br1.setBackground(Color.decode(green));
        br2.setBackground(Color.decode(yellow));
        br3.setBackground(Color.decode(yellow));
      }
    });

    br2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        hero2(2);
        br1.setBackground(Color.decode(yellow));
        br2.setBackground(Color.decode(green));
        br3.setBackground(Color.decode(yellow));
      }
    });

    br3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        hero2(3);
        br1.setBackground(Color.decode(yellow));
        br2.setBackground(Color.decode(yellow));
        br3.setBackground(Color.decode(green));
      }
    });

    endl.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        hero1Ok();
        if ((hero1Ok && hero2Ok) && (hero1 != 0 && hero2 != 0)) {
          switchStartPage2GamePage();
        }
      }
    });
    endr.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        hero2Ok();
        if ((hero1Ok && hero2Ok) && (hero1 != 0 && hero2 != 0)) {
          switchStartPage2GamePage();
        }

      }
    });

    // TODO
    panel.add(left);
    panel.add(right);
    return panel;
  }

}