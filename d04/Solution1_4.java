import java.io.*;
import java.util.*;

public class Solution1_4
{
	private static int numOverlaps;

	public static boolean sectionsOverlapping(String line)
	{
		// 1 beg, 1 end, 2 beg, 2 end
		ArrayList<String> sections = new ArrayList<>(4);
		for (String sectionGroup : line.split(","))
		{
			sections.addAll(Arrays.asList(sectionGroup.split("-")));
		}

		int sectionsStart1 = Integer.parseInt(sections.get(0));
		int sectionsEnd1 = Integer.parseInt(sections.get(1));
		int sectionsStart2 = Integer.parseInt(sections.get(2));
		int sectionsEnd2 = Integer.parseInt(sections.get(3));

		if (sectionsStart1 <= sectionsStart2 && sectionsEnd1 >= sectionsEnd2)
		{
			return true;
		}
		else if (sectionsStart2 <= sectionsStart1 && sectionsEnd2 >= sectionsEnd1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader dataFile = new BufferedReader(new FileReader("d04/data.txt"));

		String currLine;
		while ((currLine = dataFile.readLine()) != null)
		{
			if (sectionsOverlapping(currLine))
			{
				numOverlaps++;
			}
		}

		System.out.printf("The number of totally overlapping groups is: %d.%n", numOverlaps);
	}
}
