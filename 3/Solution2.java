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

	public static int checkForBadge(char inputItem, ArrayList<String> rucksacks)
	{
		String ruckN = rucksacks.get(0);
		if (rucksacks.size() != 1)
		{
			ArrayList<String> otherRucksacks = rucksacks.subList(1, rucksacks.size());
		}
		else // Bottom of the list of rucksacks
		{
			ArrayList<String> otherRucksacks = null;
		}
		for (int i = 0; i < ruckN.length(); i++)
		{
			char ruckNItem = ruckN.charAt(i);
			if (ruckNItem == inputItem || ruckNItem == '_')
			{
				if (checkForBadge(ruckNItem, otherRucksacks))
				{
				}
			}
		}
	}

	public static int rucksacksToPriority(ArrayList<String> rucksacks)
	{
		String ruck1 = rucksacks.get(0);

		for (int i = 0; i < ruck1.length(); i++)
		{
			char ruck1Item = ruck1.charAt(i);
			ArrayList<String> otherRucksacks = rucksacks.subList(1, rucksacks.size());
			if (checkForBadge(ruck1Item, otherRucksacks))
			{
				return charToPriority(ruck1Item);
			}
		}
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

