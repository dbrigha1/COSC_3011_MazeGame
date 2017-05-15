package game.buttons;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonHover implements MouseListener
  {

    Point currentLocation;
    Color colorBlue = new Color(50, 80, 250);
    Color colorLightBlue = new Color(142, 192, 228);
    // for quit button
    Color colorRed = new Color(242, 119, 101);
    Color colorStrongRed = new Color(240, 0, 0);

    @Override
    public void mouseClicked(MouseEvent e)
      {
        // TODO: save and load file handler here
        // MenuButton currentButton = ((MenuButton) e.getSource());
        // String buttonName = currentButton.getName();

        /**
         * something like this if(buttonName.equals("Load")) { FileManager data
         * = new FileManager(); data.readFile("default.mze"); }
         */
      }

    @Override
    public void mouseEntered(MouseEvent e)
      {
        MenuButton currentButton = ((MenuButton) e.getSource());
        String buttonName = currentButton.getName();

        switch (buttonName)
          {
          case "Quit":
            currentButton.setBackground(colorStrongRed);
            break;
          default: // reset, file, load, save buttons
            currentButton.setBackground(colorBlue);
            break;
          }

      }

    @Override
    public void mouseExited(MouseEvent e)
      {
        MenuButton currentButton = ((MenuButton) e.getSource());
        String buttonName = currentButton.getName();

        switch (buttonName)
          {
          case "Quit":
            currentButton.setBackground(colorRed);
            break;
          default: // reset, file, load, save buttons
            currentButton.setBackground(colorLightBlue);
            break;
          }

      }

    @Override
    public void mousePressed(MouseEvent e)
      {
      }

    @Override
    public void mouseReleased(MouseEvent e)
      {
      }

  }
