package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import game.model.FileManager;

public class Tile extends JPanel
  {

    private static final long serialVersionUID = 1L;
    private String id;
    static public Maze[] mazes = new Maze[16];
    private String name;

    public Tile(String newID)
      {
        id = newID;
        setLayout(new GridBagLayout());
        addMouseListener(new MouseTileActionListener());
        addMazes();
        setOpaque(true);
        setMinimumSize(new Dimension(101, 101));
        setPreferredSize(new Dimension(101, 101));
        setBackground(Color.WHITE);
        setName("Tile");
        setBorder(BorderFactory.createLineBorder(Color.gray, 1));
      }

    public void addMazes()
      {
        // this is for when the game is initially setup
        for (int i = 0; i < FileManager.position.size(); i++)
          {
            Maze maze = new Maze(i, FileManager.position.get(i), FileManager.matrix.get(i),
                FileManager.rotation.get(i));

            if (Integer.valueOf(getId()) == maze.getMazePosition())
              {
                add(maze);
                mazes[maze.getMazeId()] = maze;
                
              }

          }
      }

    public String getName()
      {
        return name;
      }

    public void setName(String newName)
      {
        name = newName;
      }

    public String getId()
      {
        return id;
      }

    public void setId(String newId)
      {
        id = newId;
      }

  }
