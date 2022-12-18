import java.io.*;

public class Solution1_1
{
	
	public static void main(String[] args) throws IOException
	{
		int lineNum;
		int calorieGroupTotal;
		int greatestCalorieCount;
		int greatestLineNum;
		String currLine;

		BufferedReader dataFile = new BufferedReader(new FileReader("data.txt"));

		lineNum = 0;
		calorieGroupTotal = 0;
		greatestLineNum = 0;
		greatestCalorieCount = 0;
		currLine = dataFile.readLine();

		while (currLine != null)
		{
			if (currLine.length() != 0)
			{
				calorieGroupTotal += Integer.parseInt(currLine);
			}
			else
			{
				if (calorieGroupTotal > greatestCalorieCount)
				{
					greatestCalorieCount = calorieGroupTotal;
					greatestLineNum = lineNum;
				}
				calorieGroupTotal = 0;
			}
			lineNum++;

			currLine = dataFile.readLine();
		}

		dataFile.close();

		System.out.printf(
				"The greatest calorie count group ended @ line %d with %d calories.%n", 
				greatestLineNum, 
				greatestCalorieCount
		);
	}
}

