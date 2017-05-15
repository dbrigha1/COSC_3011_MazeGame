package game;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

public class SidePanel extends JPanel
  {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    public SidePanel(int offset)
      {
        addTiles(offset);
      }

    public void addTiles(int offset)
      {
        this.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        for (int i = 0 + offset; i < 8 + offset; i++)
          {
            cons.gridy = i;
            cons.weightx = 1;
            cons.insets = new Insets(5, 5, 5, 5);
            cons.weighty = 0;
            Tile tile = new Tile("" + i);
            tile.setFont(new Font("Tahoma", Font.BOLD, 20));

            this.add(tile, cons);
          }

      }

  }
