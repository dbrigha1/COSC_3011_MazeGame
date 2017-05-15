package game;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Container;

import javax.swing.SwingUtilities;

public class MouseTileActionListener implements MouseListener
  {

    Container component;
    static Container previous = new Maze();
    static Tile tile;
    static boolean infoTileClicked = false;

    public void mousePressed(MouseEvent evt)
      {
        Container parent = GameWindow.frame;

        Component[] menuAndPlayArea = parent.getComponents();

        PlayArea playArea = (PlayArea) menuAndPlayArea[1];
        component = (Container) evt.getSource();

        if (SwingUtilities.isLeftMouseButton(evt))
          {

            if (component.getName() == "MazeTile" && !infoTileClicked)
              {
                component.setBackground(Color.YELLOW);

                infoTileClicked = true;

                previous = component; // remember previous
                tile = (Tile) previous.getParent();

              } else
              { // when clicked out of SIDE tiles
                previous.setBackground(Color.ORANGE);
                if (component.getName() != "Tile")
                  previous.setBackground(Color.ORANGE);

                // click to a empty tile after an info tile
                if (infoTileClicked && (component.getName() == "Tile"))
                  {

                    tile.removeAll();
                    tile.revalidate();
                    tile.repaint();
                    ((Maze) previous).setMazePosition(Integer.valueOf(((Tile) component).getId()));
                    component.add(previous);
                    component.revalidate();
                    component.repaint();
                    // //if we are moving the Tiles at the first time
                    // //then starting the timer
                    // //need to make sure we do it only once (but not
                    // necessary)
                    StopWatchLabel.getInstance().start();

                    ((PlayArea) playArea).modify(true);
                    ((PlayArea) playArea).checkWin();
                  }
                infoTileClicked = false;
                // if (component instanceof Tile)
                // ((Tile) component).setClicked(false);
              }
          } else if (SwingUtilities.isRightMouseButton(evt) && (component.getName() == "MazeTile"))
          {
            ((PlayArea) playArea).modify(true);
            ((Maze) component).changeMazeRotation();
            StopWatchLabel.getInstance().start();
            ((PlayArea) playArea).checkWin();
          }
      }

    @Override
    public void mouseClicked(MouseEvent arg0)
      {
        // TODO Auto-generated method stub

      }

    @Override
    public void mouseEntered(MouseEvent arg0)
      {
        // TODO Auto-generated method stub

      }

    @Override
    public void mouseExited(MouseEvent arg0)
      {
        // TODO Auto-generated method stub

      }

    @Override
    public void mouseReleased(MouseEvent arg0)
      {
        // TODO Auto-generated method stub

      }
  }
