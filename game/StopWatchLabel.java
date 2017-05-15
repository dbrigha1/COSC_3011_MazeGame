package game;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import javax.swing.JLabel;

public class StopWatchLabel extends JLabel
  {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static StopWatchLabel instance;
    private static long startedFrom;
    private static long currentTime = 0;
    private static long second;
    private static long minute;
    private static long hour;
    private static Timer timer;

    private StopWatchLabel()
      {
      }; // do not allow anyone to create it

    // make it a singleton as the time for the whole game is only one
    public static StopWatchLabel getInstance()
      {
        if (instance == null)
          { // lazy initialization of component
            instance = new StopWatchLabel();
            // set preferences only once
            instance.setOpaque(true);
            Dimension dimension = new Dimension(250, 101);
            instance.setMinimumSize(dimension);
            instance.setPreferredSize(dimension);

            // initialize timer
            int delay = 1000; // milliseconds
            // initialization of timeTicker
            TimerAction taskPerformer = instance.new TimerAction();
            
            timer = new Timer(delay, taskPerformer );
          }
        return instance;
      }

    public void setDisplayTime(long savedResult)
      {
        currentTime = savedResult;
        long temp = currentTime;
        hour = temp / 3600;
        temp -= hour * 3600;
        minute = temp / 60;
        temp -= minute * 60;
        second = temp;
        // TODO: show saved result
        // instance.setText("Timer: " + String.valueOf(currentTime));
        instance.setText(String.format("%02d", hour) + " : " + String.format("%02d", minute) + " : "
            + String.format("%02d", second));
      }

    // function saving the value from the file for reset purpose
    public void setStartingTimeForReset(long timeFromFile)
      {
        startedFrom = timeFromFile;
      }

    public long getStartingTimeForReset()
      {
        return startedFrom;
      }

    public void start()
      {
        // starting from currentTime
        timer.start();
      }

    public long getTime()
      { // get seconds
        return currentTime;
      }

    public void stop()
      {
        // could put it in getTime
        timer.stop();
      }
    class TimerAction implements ActionListener{
      public void actionPerformed(ActionEvent evt)
      {
        currentTime++;
        long temp = currentTime;
        hour = temp / 3600;
        temp -= hour * 3600;
        minute = temp / 60;
        temp -= minute * 60;
        second = temp;
        // System.out.println(currentTime);
        // display new current time every second

        instance.setText(String.format("%02d", hour) + " : " + String.format("%02d", minute)
            + " : " + String.format("%02d", second));
        // instance.setText("Timer: " +
        // String.valueOf(currentTime));
      }
    }
  }


