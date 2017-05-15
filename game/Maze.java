package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Maze extends JLabel
  {
    /**
    	 * 
    	 */
    private static final long serialVersionUID = 1L;

    private int mazePosition;
    private int mazeId;
    private int mazeRotation;
    private ArrayList<Line> lines;

    Maze()
      {
        super();
      }

    Maze(int id, int tilePos, ArrayList<Line> mazeLines, int rotate)
      {
        addMouseListener(new MouseTileActionListener());
        setBackground(Color.ORANGE);
        setOpaque(true);
        setMinimumSize(new Dimension(101, 101));
        setPreferredSize(new Dimension(101, 101));
        this.setVisible(true);
        setName("MazeTile");
        mazeId = id;
        mazePosition = tilePos;
        lines = mazeLines;
        mazeRotation = rotate;
      }

    public void paintComponent(Graphics g)
      {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

        g2.rotate(mazeRotation * 90 * Math.PI / 180, this.getBounds().width / 2, this.getBounds().height / 2);
        
//        Font font = new Font("Tahoma", Font.BOLD, 18);
//        
//        this.setFont(font);
//        this.setText(mazeId + " : " + mazeRotation + " : " + mazePosition);

        ArrayList<Line> temp = getMazeLines(); // the actual maze

        for (Line l : temp)
          {
            int temp1 = (int) l.getCoordinates()[0];
            int temp2 = (int) l.getCoordinates()[1];
            int temp3 = (int) l.getCoordinates()[2];
            int temp4 = (int) l.getCoordinates()[3];
            g2.drawLine(temp1, temp2, temp3, temp4);

          }

      }

    public void setMazePosition(int value)
      {
        mazePosition = value;
      }

    public int getMazePosition()
      {
        return mazePosition;
      }

    public void setMazeId(int value)
      {
        mazeId = value;
      }

    public int getMazeId()
      {
        return mazeId;
      }

    public void setMazeLines(ArrayList<Line> value)
      {
        lines = value;
      }

    public ArrayList<Line> getMazeLines()
      {
        return lines;
      }

    public void setMazeRotation(int value)
      {
        mazeRotation = value;
      }

    public void changeMazeRotation()
      {
        mazeRotation++;
        mazeRotation = mazeRotation % 4;
        repaint();
      }

    public int getMazeRotation()
      {
        return mazeRotation;
      }

  }
