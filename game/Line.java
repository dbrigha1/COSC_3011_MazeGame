package game;

public class Line
  {

    private float[] coordinates = new float[4];

    public Line(float[] coordinates)
      {
        this.coordinates = coordinates;
      }

    public float[] getCoordinates()
      {
        return coordinates;
      }
  }
