import java.io.*;

public class Solution2
{
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		int lineNum;
		int calorieGroupTotal;
		int[] topCalorieCounts;
		int[] topLineNums;
		String currLine;
		int topCalorieSum;

		BufferedReader dataFile = new BufferedReader(new FileReader("data.txt"));

		lineNum = 0;
		calorieGroupTotal = 0;
		topLineNums = new int[3];
		topCalorieCounts = new int[3];
		currLine = dataFile.readLine();

		while (currLine != null)
		{
			if (currLine.length() != 0) // Current line HAS a number
			{
				calorieGroupTotal += Integer.parseInt(currLine);
			}
			else // Current line DOES NOT HAVE a number
			{
				// for loop scans DOWN the placements of the calorie counts (1st, 2nd, ...)
				for (int place = 0; place < 3; place++)
				{
					if (calorieGroupTotal > topCalorieCounts[place])
					{
						topCalorieCounts[place] = calorieGroupTotal;
						topLineNums[place] = lineNum;
						break;
					}
				}
				calorieGroupTotal = 0;
			}
			lineNum++;

			currLine = dataFile.readLine();
		}

		dataFile.close();

		topCalorieSum = 0;
		for (int place = 0; place < 3; place++)
		{
			topCalorieSum += topCalorieCounts[place];
			System.out.printf(
					"Place %d: %d calories @ line %d%n",
					place + 1,
					topCalorieCounts[place],
					topLineNums[place]
			);
		}

		System.out.printf("Sum of top 3 calorie counts: %d%n", topCalorieSum);
	}
}

