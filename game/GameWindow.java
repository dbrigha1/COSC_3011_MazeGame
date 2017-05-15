/* @author Ben, Jake, Nikita, Dylan, Paul
 * Date: Feb 01, 2017
 *
 * $Author$
 * $Date$
 * $Revision$
 *
 * This is the actual "game". May/will have to make some major changes.
 * This is just a "hollow" shell.
 *
 * When you get done, I should see the buttons at the top in the "play" area
 * (not a pull-down menu). The only one that should do anything is Quit.
 *
 * Should also see something that shows where the 4x4 board and the "spare"
 * tiles will be when we get them stuffed in.
 * REMEMBER Dr. Buckner doesn't want the lines longer than 80 characters long.
 */
package game;

import javax.swing.*; // doesn't this bring in the entire swing library so no need line 20?
import java.awt.*; // doesn't this bring in all awt so next 2 lines redundant?

public class GameWindow extends JFrame
  {

    public static final long serialVersionUID = 1;
    public static JPanel frame = new JPanel(new GridBagLayout());

    public GameWindow()
      {
        super("Group C Maze"); // names the OS window chrome across the top
        addMouseListener(new MouseTileActionListener());
        this.addComponents();
        frame.setName("frame");
        this.setUpWindow();

      }

    public void setUpWindow()
      {
        try
          {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
          } catch (Exception e)
          {
            e.printStackTrace();
          }

        this.setSize(1000, 1050);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(212, 223, 230)); // baby
                                                                       // blue
        this.add(frame);
        this.setVisible(true);
        frame.setBackground(Color.gray);

      }

    // this is where the different components of the game window are
    // called
    public void addComponents()
      {
        this.addMenu();
        this.addPlayArea();
      }

    // the addMenu and addPlayArea methods (below) are used to specify
    // where in the gameWindow they belong
    // The "frame" panel is using gridbaglayout

    public void addMenu()
      {
        // the menu object is a panel that includes the new game, reset,
        // and exit buttons
        Menu menu = new Menu();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(50, 0, 0, 0);
        frame.add(menu, gbc);
      }

    public void addPlayArea()
      {
        // the playArea is a panel that includes the 4X4 board and the side
        // panels
        PlayArea playArea = new PlayArea();
        GridBagConstraints playAreaPosition = new GridBagConstraints();
        playAreaPosition.gridx = 0;
        playAreaPosition.gridy = 0;
        playArea.setBackground(Color.gray);
        playArea.setName("playArea");
        // playArea.saveConfig();
        frame.add(playArea, playAreaPosition);
      }

  };
