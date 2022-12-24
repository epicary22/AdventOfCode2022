import java.io.*;
import java.util.*;

public class Solution1_9
{
	public static Coordinate tailPos = new Coordinate();
	public static Coordinate headPos = new Coordinate();
	public static Coordinate oldHeadPos = new Coordinate();
	// This can't be a set of Coordinates; Coordinates with the same values can coexist in the set.
	public static HashSet<ArrayList<Integer>> uniqueTailPositions = new HashSet<>();

	public static void executeMoveCommand(String command)
	{
		char direction = command.charAt(0);
		int distance = Integer.parseInt(command.split(" ")[1]);

		for (int step = 0; step < distance; step++)
		{
			oldHeadPos.setToCoordinate(headPos);
			stepHeadPos(direction);
			determineTailPos();
			uniqueTailPositions.add(tailPos.asArrayList());
		}
	}

	public static void stepHeadPos(char direction)
	{
      if (direction == 'L')
      {
          headPos.addToX(-1);
      }
      else if (direction == 'R')
      {
          headPos.addToX(1);
      }
      else if (direction == 'D')
      {
          headPos.addToY(-1);
      }
      else if (direction == 'U')
      {
          headPos.addToY(1);
      }
	}

	public static void determineTailPos()
	{
      if (!tailPos.inRangeOf1(headPos))
      {
          tailPos.setToCoordinate(oldHeadPos);
      }
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader dataFile = new BufferedReader(new FileReader("9/data.txt"));

		String command = dataFile.readLine();
		while (command != null)
		{
			executeMoveCommand(command);
			command = dataFile.readLine();
		}

		System.out.println("The tail has visited " + uniqueTailPositions.size() + " unique locations.");
	}
}
