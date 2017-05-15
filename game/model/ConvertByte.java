package game.model;

//Methods obained from the URL Doctor Buckner provided

import java.nio.ByteBuffer;

public class ConvertByte
  {

    public static int convertToInt(byte[] array)
      {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        return buffer.getInt();
      }

    public static long convertToLong(byte[] array)
      {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        return buffer.getLong();
      }

    public static float convertToFloat(byte[] array)
      {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        return buffer.getFloat();
      }

    public static byte[] convertToByteArray(int value)
      {
        byte[] bytes = new byte[4];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putInt(value);
        return buffer.array();
      }

    public static byte[] convertToByteArray(float value)
      {
        byte[] bytes = new byte[4];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putFloat(value);
        return buffer.array();
      }

    public static byte[] convertToByteArray(long value)
      {

        byte[] bytes = new byte[8];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putLong(value);
        return buffer.array();
      }

    // code below is from stackoverflow
    // http://stackoverflow.com/questions/15429257/how-to-convert-byte-array-to-hexstring-in-java
    public static String bytesToHex(byte[] in)
      {
        final StringBuilder builder = new StringBuilder();
        for (byte b : in)
          {
            builder.append(String.format("%02x", b));
          }
        return builder.toString();
      }

    // http://stackoverflow.com/questions/140131/convert-a-string-representation-of-a-hex-dump-to-a-byte-array-using-java
    public static byte[] hexStringToByteArray(String s)
      {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2)
          {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
          }
        return data;
      }

  }
