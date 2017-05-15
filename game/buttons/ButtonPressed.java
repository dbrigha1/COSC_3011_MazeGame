package game.buttons;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import game.GameWindow;
import game.PlayArea;
import game.StopWatchLabel;
import game.model.FileManager;

public class ButtonPressed implements ActionListener
  {
    // public static String fileName;

    public static JTextField textField;

    @Override
    public void actionPerformed(ActionEvent e)
      {

        MenuButton currentButton = (MenuButton) e.getSource();

        if (currentButton.getName().equalsIgnoreCase("Quit"))
          {

            if (PlayArea.modified)

              {
                int choice = JOptionPane.showConfirmDialog(GameWindow.frame, "Would you like to save the game?",
                    "Warning", 0);
                if (choice == 1)
                  {
                    System.exit(0);
                  } else if (choice == 0)
                  {

                    MenuButton saveTemp = new MenuButton("Save");
                    saveTemp.doClick();
                    System.exit(0);
                  } else
                  {
                    // System.exit(0);
                  }
              } else
              {
                System.exit(0);
              }
          } else if (currentButton.getName().equalsIgnoreCase("File"))
          {

            GridBagConstraints c = new GridBagConstraints();
            JPopupMenu popup = new JPopupMenu();
            popup.setLayout(new GridBagLayout());
            textField = new JTextField("Enter file path", 30);
            JPanel saveAndLoadContainer = new JPanel(new GridBagLayout());
            MenuButton saveButton = new MenuButton("Save");
            saveButton.setPreferredSize(new Dimension(125, 60));
            MenuButton loadButton = new MenuButton("Load");
            loadButton.setPreferredSize(new Dimension(125, 60));
            new GridBagConstraints();
            c.gridy = 0;
            saveAndLoadContainer.add(saveButton, c);
            c.gridy = 1;
            saveAndLoadContainer.add(loadButton, c);
            c.gridy = 1;
            popup.add(saveAndLoadContainer, c);
            c.gridy = 0;
            c.gridx = 0;
            currentButton.add(popup);
            popup.show(currentButton, 0, 60);

          } else if (currentButton.getName().equalsIgnoreCase("Reset"))
          {

            // take the timer back
            StopWatchLabel timer = StopWatchLabel.getInstance();
            timer.stop();
            long timeFromFile = timer.getStartingTimeForReset();
            timer.setDisplayTime(timeFromFile);

            Container parent = GameWindow.frame;

            Component[] menuAndPlayArea = parent.getComponents();

            Component playArea = menuAndPlayArea[1];

            ((PlayArea) playArea).setup(FileManager.position, FileManager.matrix, FileManager.rotation);

            ((PlayArea) playArea).modify(false);

          } else if (currentButton.getName().equalsIgnoreCase("Save"))
          {

            int choice = 1;
            int isSaved = 0;
            do
              {
                FileManager data = new FileManager();
                String fileName;
                final JFileChooser fileSeeker = new JFileChooser();
                isSaved = fileSeeker.showSaveDialog(GameWindow.frame);

                if (isSaved == 0)
                  {
                    try
                      {

                        File directory = fileSeeker.getCurrentDirectory();
                        File file2 = fileSeeker.getSelectedFile();

                        String filePath = directory.getPath();
                        fileName = file2.getName();

                        if (file2.exists())
                          {
                            choice = JOptionPane.showConfirmDialog(GameWindow.frame, "Overwrite " + fileName + "?",
                                "Warning", 0);
                            if (choice == 0)
                              {

                                saveFile();
                                data.writeFile(filePath + "/" + fileName);
                              }
                            if (choice == 2)
                              {

                              }

                          } else
                          {
                            saveFile();
                            data.writeFile(filePath + "/" + fileName);
                            choice = 0;
                          }
                      } catch (IOException e1)
                      {
                        e1.printStackTrace();
                      }
                  } else
                  {
                    // do nothing
                  }
              } while (choice == 1 && isSaved == 0);
          }

        else if (currentButton.getName().equalsIgnoreCase("Load"))
          {
        	
            if (PlayArea.modified)
              {
                int choice = JOptionPane.showConfirmDialog(GameWindow.frame, "Would you like to save the game?",
                    "Warning", 0);
                if (choice == 0)
                  {
                    MenuButton saveTemp = new MenuButton("Save");
                    saveTemp.doClick();
                  } else if (choice == 1)
                  {
                    int isLoaded = 0;
                    int error = 0;
                    do
                      {
                        FileManager data = new FileManager();

                        String fileName;

                        final JFileChooser fileSeeker = new JFileChooser();
                        isLoaded = fileSeeker.showOpenDialog(GameWindow.frame);

                        if (isLoaded == 0)
                          {
                            try
                              {
                                File directory = fileSeeker.getCurrentDirectory();
                                File file2 = fileSeeker.getSelectedFile();
                                if (!file2.exists())
                                  {
                                    JOptionPane.showMessageDialog(GameWindow.frame, "File not found", "ERROR",
                                        JOptionPane.INFORMATION_MESSAGE);
                                    error = 0;
                                  } else
                                  {
                                    String filePath = directory.getPath();
                                    fileName = file2.getName();

                                    data.readFile(filePath + "/" + fileName);
//                                    PlayArea.justLoaded = true;
                                  

                                    error = 1;
                                  }
                              } catch (IOException e1)
                              {

                                e1.printStackTrace();
                              }

                          }

                        else
                          {
                            // nothing
                          }

                      } while (isLoaded == 0 && error == 0);

                  } else
                  {

                  }

              } else
              {
                int isLoaded = 0;
                int error = 0;
                do
                  {
                    FileManager data = new FileManager();

                    String fileName;

                    final JFileChooser fileSeeker = new JFileChooser();
                    isLoaded = fileSeeker.showOpenDialog(GameWindow.frame);

                    if (isLoaded == 0)
                      {
                        try
                          {
                            File directory = fileSeeker.getCurrentDirectory();
                            File file2 = fileSeeker.getSelectedFile();
                            if (!file2.exists())
                              {
                                JOptionPane.showMessageDialog(GameWindow.frame, "File not found", "ERROR",
                                    JOptionPane.INFORMATION_MESSAGE);
                                error = 0;
                              } else
                              {
                                String filePath = directory.getPath();
                                fileName = file2.getName();

                                data.readFile(filePath + "/" + fileName);
//                                PlayArea.justLoaded = true;
                                

                                error = 1;
                              }
                          } catch (IOException e1)
                          {

                            e1.printStackTrace();
                          }

                      }

                    else
                      {
                        // do nothing
                      }

                  } while (isLoaded == 0 && error == 0);

              }
          }

      }

    private void saveFile()
      {

        Component parent = GameWindow.frame;

        Component[] menuAndPlayArea = ((Container) parent).getComponents();

        Component playArea = (Component) menuAndPlayArea[1];

        ((PlayArea) playArea).saveFile();
        ((PlayArea) playArea).modify(false);

      }
  }
