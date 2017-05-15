package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.model.FileManager;
import game.Board;
import game.Tile;

// this class is a panel that contains the board and 
// side panels. 
// the position of the board and side panels are dependent
// on the order in which they are called in the constructor
// this could be an area for improvement. 

public class PlayArea extends JPanel
  {
    /**
     *
     */
    // static public boolean justLoaded = false;
    private static final long serialVersionUID = 1L;
    public static boolean modified = false;
    public static boolean won = false;

    PlayArea()
      {
        addMouseListener(new MouseTileActionListener());
        setLayout(new GridBagLayout());
        addWestSidePanel();
        addBoard();
        addStopWatchLabel();
        addEastSidePanel();
        // playAreaStyle();
        setName("playArea");

      }

    private void addWestSidePanel()
      {
        GridBagConstraints constraint = new GridBagConstraints();
        SidePanel tilePanelWest = new SidePanel(0);

        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(0, 150, 0, 150);

        tilePanelWest.setName("tilePanelWest");
        tilePanelWest.setBackground(Color.gray);

        add(tilePanelWest, constraint);

      }

    private void addEastSidePanel()
      {
        GridBagConstraints constraint = new GridBagConstraints();
        SidePanel tilePanelEast = new SidePanel(8);

        constraint.gridx = 2;
        constraint.gridy = 0;
        constraint.insets = new Insets(0, 150, 0, 150);

        tilePanelEast.setName("tilePanelEast");
        tilePanelEast.setBackground(Color.gray);

        add(tilePanelEast, constraint);
      }

    // addBoard() constructs the board and adds it to the object.
    public void addBoard()
      {

        Board boardPanel = new Board();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 0;
        boardPanel.setBackground(Color.blue);
        add(boardPanel, gbc);

      }

    public void addStopWatchLabel()
      {
        StopWatchLabel TimeLabel = StopWatchLabel.getInstance();

        // TODO: put it under the menu, play with weights
        GridBagConstraints timeLabelPosition = new GridBagConstraints();
        timeLabelPosition.insets = new Insets(-300, 0, 0, 0);
        timeLabelPosition.gridx = 1;
        timeLabelPosition.gridy = 1;

        // TODO: pull previous time value from FileReader
        // and set it here

        TimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Font font = new Font("Tahoma", Font.BOLD, 30);
        TimeLabel.setFont(font);
        TimeLabel.setForeground(Color.WHITE);
        TimeLabel.setBackground(Color.GRAY);

        TimeLabel.setVisible(true);
        add(TimeLabel, timeLabelPosition);
      }

    public final void saveFile()
      {
        FileManager.save.clear();
        FileManager.savePosition.clear();
        FileManager.saveRotation.clear();

        for (int i = 0; i < 16; i++)
          {
            FileManager.save.add(Tile.mazes[i].getMazeLines());
            FileManager.savePosition.add(Tile.mazes[i].getMazePosition());
            // System.out.println("position: " +
            // Tile.mazes[i].getMazePosition());
            FileManager.saveRotation.add(Tile.mazes[i].getMazeRotation());
            // System.out.println("rotation: " +
            // Tile.mazes[i].getMazeRotation());
          }

      }

    public void modify(boolean mod)
      {
        modified = mod;
      }

    public void setup(ArrayList<Integer> pos, ArrayList<ArrayList<Line>> lines, ArrayList<Integer> rotation)
      {
        clear();

        for (int i = 0; i < 16; i++)
          {
            Maze maze = new Maze(i, pos.get(i), lines.get(i), rotation.get(i));

            Component[] layout = getComponents();
            Component temp2 = layout[2];
            layout[2] = layout[1];
            layout[1] = temp2;

            for (int j = 0; j < layout.length; j++)
              {

                Component[] tiles = ((Container) layout[j]).getComponents();
                for (int k = 0; k < tiles.length; k++)
                  {

                    if (Integer.valueOf(((Tile) tiles[k]).getId()) == maze.getMazePosition())
                      {

                        Tile.mazes[maze.getMazeId()] = maze;
                        ((Tile) tiles[k]).add(maze);
                        ((Tile) tiles[k]).revalidate();
                        ((Tile) tiles[k]).repaint();
                      }

                  }
              }
          }
      }

    public void clear()
      {

        Component[] layout = getComponents();
        Component temp2 = layout[2];
        layout[2] = layout[1];
        layout[1] = temp2;
        for (int i = 0; i < layout.length; i++)
          {
            Component[] tiles = ((Container) layout[i]).getComponents();
            for (int j = 0; j < tiles.length; j++)
              {
                ((Container) tiles[j]).removeAll();
                ((Container) tiles[j]).revalidate();
                ((Container) tiles[j]).repaint();
              }
          }
      }

    public void checkWin()
      {
        won = true;
        for (int i = 0; i < 16; i++)
          {
            if (Tile.mazes[i].getMazeId() + 16 != Tile.mazes[i].getMazePosition())
              {
                // System.out.println("Maze ID: " + Tile.mazes[i].getMazeId() +
                // " Maze Position: " + Tile.mazes[i].getMazePosition());
                won = false;
              }
            if (Tile.mazes[i].getMazeRotation() != 0)
              {
                // System.out.println("Maze Rotation: " +
                // Tile.mazes[i].getMazeRotation());
                won = false;
              }
          }
        if (won)
          {
            StopWatchLabel.getInstance().stop();
            long currentTime = StopWatchLabel.getInstance().getTime();
            long temp = currentTime;
            long hour = temp / 3600;
            temp -= hour * 3600;
            long minute = temp / 60;
            temp -= minute * 60;
            long second = temp;

            JOptionPane.showMessageDialog(GameWindow.frame,
                "Completion time:\n" + String.format("%02d", hour) + " hours " + String.format("%02d", minute)
                    + " minutes " + String.format("%02d", second) + " seconds ",
                "YOU WON!", JOptionPane.INFORMATION_MESSAGE);
          }
      }
  }
