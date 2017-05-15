package game.model;

import java.awt.Component;
import java.awt.Container;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;

import game.GameWindow;
import game.Line;
import game.PlayArea;
import game.StopWatchLabel;

public class FileManager
  {

    static public ArrayList<ArrayList<Line>> matrix = new ArrayList<ArrayList<Line>>(16);
    static public ArrayList<ArrayList<Line>> save = new ArrayList<ArrayList<Line>>(16);
    static public ArrayList<Integer> savePosition = new ArrayList<Integer>(16);
    static public ArrayList<Integer> position = new ArrayList<Integer>(16);
    static public ArrayList<Integer> saveRotation = new ArrayList<Integer>(16);
    static public ArrayList<Integer> rotation = new ArrayList<Integer>(16);

    private String fileName;
    static public boolean trigger;
    public static String fileLocation = "./trunk/game/mazeFiles/";

    public FileManager()
      {
        // TODO:? make it a singleton?
      }

    public void readFile(String path) throws IOException
      {
        StopWatchLabel.getInstance().stop();
        
        int angle;

        ReadFile in = null;
        in = new ReadFile(path);

        int first = in.read(); // read the 4 hex values
        int second = in.read();
        int third = in.read();
        int fourth = in.read();

        // String fileIdentifier = in.readHex();

        try
          {
         
            /** ---------------------------------------------------------------------------------- **/
            /** ALREADY PLAYED CASE **/
            if (first == 0xca && second == 0xfe && third == 0xde && fourth == 0xed)
              {
              matrix.clear();
              
              position.clear();
              
              rotation.clear();
                in.readInt(); // read 16

                long timeFromFile = in.readLong();
                StopWatchLabel.getInstance().setDisplayTime(timeFromFile);
                StopWatchLabel.getInstance().setStartingTimeForReset(timeFromFile);

                int tilePos = in.readInt();
                while (tilePos != -1) // TODO: will do it for all 32 Tiles
                  {
                    angle = in.readInt();

                    ArrayList<Line> linesForTile = new ArrayList<Line>();
                    int numberOfLines = in.readInt();
                    for (int i = 0; i < numberOfLines; i++)
                      {
                        // read four coordinates
                        float[] lineCoordsArray = new float[4];
                        for (int coordNum = 0; coordNum < 4; coordNum++)
                          {
                            // reduces the float value so
                            // that the coordinates work on a reduced tile size
                            lineCoordsArray[coordNum] = (float) (in.readFloat());
                          }
                        Line tempLine = new Line(lineCoordsArray);
                        linesForTile.add(tempLine);
                      }

                    /**
                     * TODO: matrix.add(rotation, (ArrayList<Line>)
                     * linesForTile); or make a class TileInfo with setRotation
                     * and addLine methods
                     **/
                    position.add(tilePos);
                    rotation.add(angle);
                    matrix.add((ArrayList<Line>) linesForTile);

                    tilePos = in.readInt();
                  }

                Component parent = GameWindow.frame;
                Component[] menuAndPlayArea = ((Container) parent).getComponents();
                PlayArea playArea = (PlayArea) menuAndPlayArea[1];

                playArea.setup(position, matrix, rotation);
                playArea.modify(false);
                trigger = false;

                /** ---------------------------------------------------------------------------------- **/
                /** NEVER PLAYED CASE?????????????????????????????????????? **/
              } else if (first == 0xca && second == 0xfe && third == 0xbe && fourth == 0xef)
              {
                matrix.clear();
                
                position.clear();
                
                rotation.clear();
                in.readInt(); // read 16

                in.readLong(); // we're getting here 1462388069 somehow
                // so if never played I just set it to zero (cause we know it)
                StopWatchLabel.getInstance().setDisplayTime(0);
                StopWatchLabel.getInstance().setStartingTimeForReset(0);

                StopWatchLabel.getInstance().setDisplayTime(0);
                int tilePos = in.readInt();

                while (tilePos != -1) // TODO: will do it for all 32 Tiles
                  {

                    in.readInt(); // ignoring angle
                    ArrayList<Line> linesForTile = new ArrayList<Line>();
                    int numberOfLines = in.readInt();
                    for (int i = 0; i < numberOfLines; i++)
                      {
                        // read four coordinates
                        float[] lineCoordsArray = new float[4];
                        for (int coordNum = 0; coordNum < 4; coordNum++)
                          {
                            // reduces the float value so
                            // that the coordinates work on a reduced tile size
                            lineCoordsArray[coordNum] = (float) (in.readFloat());
                          }
                        Line tempLine = new Line(lineCoordsArray);
                        linesForTile.add(tempLine);
                      }

                    /**
                     * TODO: matrix.add(rotation, (ArrayList<Line>)
                     * linesForTile); or make a class TileInfo with setRotation
                     * and addLine methods
                     **/

                    Random rand = new Random();
                    int n = rand.nextInt(3) + 1;

                    rotation.add(n);
                    position.add(tilePos);
                    matrix.add((ArrayList<Line>) linesForTile);
                    tilePos = in.readInt();
                  }

                randomize(); 

                if (trigger == false)
                  {
                    Component parent = GameWindow.frame;
                    Component[] menuAndPlayArea = ((Container) parent).getComponents();
                    PlayArea playArea = (PlayArea) menuAndPlayArea[1];
                    playArea.setup(position, matrix, rotation);
                    playArea.modify(false);
                  }
                trigger = false;
              }
            /** ---------------------------------------------------------------------------------- **/
            else
              {
                JOptionPane.showMessageDialog(GameWindow.frame, "INVALID FILE FORMAT", "ERROR",
                    JOptionPane.INFORMATION_MESSAGE);
                //
              }
            /** ---------------------------------------------------------------------------------- **/
            in.close();

          } catch (FileNotFoundException e)
          {
            // TODO: popup menu letting type the name
            System.exit(0);
            e.printStackTrace();
          }
      }

    public void writeFile(String path) throws IOException
      {
        
        WriteFile out = null;

        out = new WriteFile(path);

        out.write(0xca); // reads the hex value
        out.write(0xfe); // reads the hex value
        out.write(0xde); // reads the hex value
        out.write(0xed); // reads the hex value
        try
          {
            out.writeData(16); // read 16
            out.writeData(StopWatchLabel.getInstance().getTime());

            for (int i = 0; i < save.size(); i++)
              {
                if (!save.get(i).isEmpty())
                  {
                    // System.out.println(i);
                    out.writeData(savePosition.get(i)); // tile position

                    out.writeData(saveRotation.get(i)); // tile rotation

                    out.writeData((save.get(i).size())); // number of lines
                    for (int j = 0; j < save.get(i).size(); j++)
                      {
                        out.writeData(save.get(i).get(j).getCoordinates()[0]);// x1
                        out.writeData(save.get(i).get(j).getCoordinates()[1]);// y1
                        out.writeData(save.get(i).get(j).getCoordinates()[2]);// x2
                        out.writeData(save.get(i).get(j).getCoordinates()[3]);// y2
                      }
                  }
              }
            out.close();

          } catch (FileNotFoundException e)
          {
            // System.exit(0);
            e.printStackTrace();

          }

      }

    public String getFileName()
      {
        return fileName;
      }

    public void setFileName(String newName)
      {
        fileName = newName;
      }

    public void randomize()
      {
        Collections.shuffle(position, new Random());
      }

  }
