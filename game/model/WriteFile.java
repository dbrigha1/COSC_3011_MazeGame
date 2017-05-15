package game.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile extends FileOutputStream
  {

    public WriteFile(String name) throws FileNotFoundException
      {
        super(name);
      }

    public void writeData(float value) throws IOException
      {
        write(ConvertByte.convertToByteArray(value));
      }

    public void writeData(int value) throws IOException
      {
        write(ConvertByte.convertToByteArray(value));
      }

    public void writeData(long value) throws IOException
      {
        write(ConvertByte.convertToByteArray(value));
      }

    public void writeData(String value) throws IOException
      {
        write(ConvertByte.hexStringToByteArray(value));
      }

  }
