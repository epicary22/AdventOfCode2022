import java.util.*;
import java.io.*;

public class Solution1
{

	private static int priorityTotal; 

	public static int charToPriority(char item)
	{
		if ('a' < item && item <= 'z')
		{
			return (int) item - 96;
		}
		else
		{
			return (int) item - 64 + 26;
		}
	}

	public static int rucksackToPriority(String rucksack)
	{
		int halfLength = rucksack.length() / 2;
		String firstCompt = rucksack.substring(0, halfLength);
		String secondCompt = rucksack.substring(halfLength);

		for (int i = 0; i < halfLength; i++)
		{
			char currChar = secondCompt.charAt(i);
			if (firstCompt.contains(String.valueOf(currChar)))
			{
				return charToPriority(currChar);
			}
		}

		// Never should happen
		return 0;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		String currLine;

		BufferedReader dataFile = new BufferedReader(new FileReader("data.txt"));

		currLine = dataFile.readLine();
		while (currLine != null)
		{
			priorityTotal += rucksackToPriority(currLine);
			currLine = dataFile.readLine();
		}

		System.out.printf("The total of the priorities of the items is: %d.%n", priorityTotal);
	}
}

