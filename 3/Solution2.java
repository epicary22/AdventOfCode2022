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

	public static int rucksacksToPriority(ArrayList<String> rucksacks)
	{
	}

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		String currLine;

		BufferedReader dataFile = new BufferedReader(new FileReader("data.txt"));

		currLine = dataFile.readLine();
		while (dataFile.readLine() != null)
		{
			ArrayList<String> rucksacks = new ArrayList<>(3);
			for (int i = 0; i < 3; i++)
			{
				rucksacks.add(currLine);
				currLine = dataFile.readLine();
			}
			priorityTotal += rucksacksToPriority(rucksacks);
		}

		System.out.printf("The total of the priorities of the badges is: %d.%n", priorityTotal);
	}
}

