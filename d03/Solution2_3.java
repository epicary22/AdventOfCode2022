import java.util.*;
import java.io.*;

public class Solution2_3
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

	public static boolean checkForBadge(char inputItem, ArrayList<String> rucksacks)
	{
		String ruckN = rucksacks.get(0);
		ArrayList<String> otherRucksacks = null;
		if (rucksacks.size() != 1)
		{
			System.out.print(rucksacks.size());
			otherRucksacks = new ArrayList(rucksacks.subList(1, rucksacks.size()));
		}
		for (int i = 0; i < ruckN.length(); i++)
		{
			char ruckNItem = ruckN.charAt(i);
			if (ruckNItem == inputItem)
			{
				if (otherRucksacks == null)
				{
					return true;
				}
				else if (checkForBadge(ruckNItem, otherRucksacks))
				{
					return true;
				}
			}
			else
			{
				return false;
			}
		}
		// Should never happen
		return false;
	}

	public static int rucksacksToPriority(ArrayList<String> rucksacks)
	{
		String ruck1 = rucksacks.get(0);

		for (int i = 0; i < ruck1.length(); i++)
		{
			char ruck1Item = ruck1.charAt(i);
			ArrayList<String> otherRucksacks = new ArrayList<>(rucksacks.subList(1, rucksacks.size()));
			if (checkForBadge(ruck1Item, otherRucksacks))
			{
				return charToPriority(ruck1Item);
			}
		}
		// Should never happen
		return 0;
	}

	public static void main(String[] args) throws IOException
	{
		String currLine;

		BufferedReader dataFile = new BufferedReader(new FileReader("d03/data.txt"));

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

