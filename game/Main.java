/**
 /**
 * @author Kim Buckner
 * Date: Feb 01, 2017
 *
 *
 * $Author$
 * $Date$
 * $Revision$
 *
 * A starting point for the COSC 3011 programming assignment
 * Probably need to fix a bunch of stuff, but this compiles and runs.
 *
 */
//the menu class contains the new game, reset and exit buttons.
//this class, once initialized shouldn't have to be modified or changed
//during the game. 

package game;

import java.io.IOException;

import javax.swing.*;

import game.buttons.MenuButton;
import game.model.FileManager;

//import java.awt.*;
public class Main
  {

    public static void main(String[] args) throws IOException
      {
        // reads the data from the file and allows manipulation
        FileManager data = new FileManager();
        String fileLocation = "./trunk/game/mazeFiles/";
        boolean fileFound = true;
        FileManager.trigger = true;
        try
          {
            data.readFile(fileLocation + "default.mze");
          } catch (Exception FileNotFoundException)
          {
            fileFound = false;
            FileManager.trigger = false;
            new GameWindow();
            JOptionPane.showMessageDialog(GameWindow.frame, "Default file not found, Please enter file name", "ERROR",
                JOptionPane.INFORMATION_MESSAGE);
            MenuButton loadTemp = new MenuButton("Load");
            loadTemp.doClick();
          }

        if (fileFound)
          {
            new GameWindow();
          }
        // data.randomize();
        // This is the play area

        try
          {
            // The 4 that installed on Linux here
            // May have to test on Windows boxes to see what is there.
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            // This is the "Java" or CrossPlatform version and the default
            // UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            // Linux only
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            // really old style Motif
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
          } catch (UnsupportedLookAndFeelException e)
          {
            // handle possible exception
          } catch (ClassNotFoundException e)
          {
            // handle possible exception
          } catch (InstantiationException e)
          {
            // handle possible exception
          } catch (IllegalAccessException e)
          {
            // handle possible exception
          }

      }

  };
