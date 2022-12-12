import java.util.*;
import java.io.*;

public class Solution2_2
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
		String ruck1 = rucksacks.get(0);
		String ruck2 = rucksacks.get(1);
		String ruck3 = rucksacks.get(2);

		for (int i = 0; i < ruck1.length(); i++)
		{
			char ruck1Item = ruck1.charAt(i);
			for (int j = 0; j < ruck2.length(); j++)
			{
				char ruck2Item = ruck2.charAt(j);
				if (ruck1Item == ruck2Item)
				{
					for (int k = 0; k < ruck3.length(); k++)
					{
						char ruck3Item = ruck3.charAt(k);
						if (ruck1Item == ruck3Item)
						{
							return charToPriority(ruck1Item);
						}
					}
				}
			}
		}
		// Should never happen
		return 0;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		String currLine;

		BufferedReader dataFile = new BufferedReader(new FileReader("data.txt"));

		currLine = dataFile.readLine();
		while (currLine != null)
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

