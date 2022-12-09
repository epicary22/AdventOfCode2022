import java.io.*;
import java.util.*;

public class Solution2_2
{
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		int calorieGroupTotal;
		int[] calorieGroupTotals;
		int totalsIndex;
		String currLine;
		int topCalorieSum;

		BufferedReader dataFile = new BufferedReader(new FileReader("data.txt"));

		calorieGroupTotal = 0;
		calorieGroupTotals = new int[512];
		totalsIndex = 0;
		currLine = dataFile.readLine();

		while (currLine != null)
		{
			if (currLine.length() != 0) // Current line HAS a number
			{
				calorieGroupTotal += Integer.parseInt(currLine);
			}
			else // Current line DOES NOT HAVE a number
			{
				calorieGroupTotals[totalsIndex] = calorieGroupTotal;
				totalsIndex++;
				calorieGroupTotal = 0;
			}

			currLine = dataFile.readLine();
		}

		dataFile.close();

		Arrays.sort(calorieGroupTotals);

		topCalorieSum = 0;
		for (int place = 1; place < 4; place++)
		{
			topCalorieSum += calorieGroupTotals[512 - place];
			System.out.printf(
					"Place %d: %d calories%n",
					place,
					calorieGroupTotals[512 - place]
			);
		}

		System.out.printf("Sum of top 3 calorie counts: %d%n", topCalorieSum);
	}
}

