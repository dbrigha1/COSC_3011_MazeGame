package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.buttons.MenuButton;

// the menu class is a panel that contains the
// new game, reset, and exit buttons
// menu uses gridbaglayout

//this class has methods that specify where the buttons are placed
// and a method that styles the object.

public class Menu extends JPanel
  {
    private static final long serialVersionUID = 1L;

    public Menu()
      {
        setName("menu");
        menuStyle();
        addButtons();
        
      }

    public void addButtons()
      {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        MenuButton newGameButton = new MenuButton("File");
        add(newGameButton, constraints);
        constraints.insets = new Insets(0, 30, 0, 0);
        MenuButton resetButton = new MenuButton("Reset");
        add(resetButton, constraints);

        constraints.insets = new Insets(0, 10, 0, 0);
        MenuButton quitButton = new MenuButton("Quit");
        add(quitButton, constraints);

      }
    

    public void menuStyle()
      {
        setBackground(Color.gray);
      }
  }
