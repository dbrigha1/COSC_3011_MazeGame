//setup by Dylan

package game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class Board extends JPanel
  {

    private static final long serialVersionUID = 1L;

    public Board()
      {
        setLayout(new GridBagLayout());
        addTiles();
        setStyle();
      }

    public void addTiles()
      {

        GridBagConstraints constraint = new GridBagConstraints();

        for (int i = 16; i < 32; i++)
          {
            constraint.gridx = i % 4;
            Tile tile = new Tile("" + i);
            constraint.gridy = (int) Math.floor(i / 4);
            constraint.weighty = 1;
            constraint.weightx = 1;
            constraint.fill = GridBagConstraints.NONE;
            add(tile, constraint);
          }
      }

    public void setStyle()
      {
        setBackground(Color.BLACK);
      }
  }