import java.io.*;
import java.util.*;

public class Solution2_9
{
	public static Coordinate tailPos = new Coordinate();
	public static Coordinate headPos = new Coordinate();
	public static Coordinate oldHeadPos = new Coordinate();
	public static ArrayList<Coordinate> knotPositions = new ArrayList<>(10);
	// This can't be a set of Coordinates; Coordinates with the same values can coexist in the set.
	public static HashSet<ArrayList<Integer>> uniqueTailPositions = new HashSet<>();

	public static void printKnotPositions()
	{
		for (int line = 20; line > -20; line--)
		{
			System.out.print(line + "\t");
			for (int col = -20; col < 20; col++)
			{
				boolean knotPos = false;
				for (Coordinate c : knotPositions)
				{
					if (c.getX() == col && c.getY() == line)
					{
						System.out.print("o");
						knotPos = true;
						break;
					}
				}
          if (!knotPos)
          {
              System.out.print(".");
          }
			}
			System.out.println();
		}
	}

	public static void executeMoveCommand(String command)
	{
		char direction = command.charAt(0);
		int distance = Integer.parseInt(command.split(" ")[1]);

		for (int step = 0; step < distance; step++)
		{
			oldHeadPos.setToCoordinate(headPos);
			stepHeadPos(direction);
			for (int k = 1; k <= 9; k++)
			{
				determineKnotPos(k);
			}
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

	public static void determineKnotPos(int index)
	{
		if (index > 0)
		{
			Coordinate currentKnot = knotPositions.get(index);
			Coordinate aheadKnot = knotPositions.get(index - 1);
			if (!aheadKnot.inRangeOf1(currentKnot))
			{
				ArrayList<Integer> distance = currentKnot.distanceFrom(aheadKnot);
				ArrayList<Integer> moveDistance = new ArrayList<>(List.of(0, 0));
				for (int i = 0; i <= 1; i++)
				{
            if (distance.get(i) == 0)
            {
                moveDistance.set(i, 0);
            }
            else if (distance.get(i) < 0)
            {
                moveDistance.set(i, -1);
            }
            else if (distance.get(i) > 0)
            {
                moveDistance.set(i, 1);
            }
				}
				currentKnot.addToX(moveDistance.get(0));
				currentKnot.addToY(moveDistance.get(1));
			}
		}
	}

	public static void main(String[] args) throws IOException
	{
		knotPositions.add(headPos);
      for (int i = 1; i <= 8; i++)
      {
          knotPositions.add(new Coordinate());
      }
		knotPositions.add(tailPos);

		BufferedReader dataFile = new BufferedReader(new FileReader("d09/data.txt"));

		String command = dataFile.readLine();
		while (command != null)
		{
//            System.out.println(command);
			executeMoveCommand(command);
			command = dataFile.readLine();
		}

		System.out.println("The tail (9) has visited " + uniqueTailPositions.size() + " unique locations.");
	}
}
