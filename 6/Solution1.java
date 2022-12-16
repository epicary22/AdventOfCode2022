import java.io.*;
import java.util.*;

public class Solution1
{

	public static int markerIndex;

	public static boolean isMarker(String inputString)
	{
		HashSet<Character> differentChars = new HashSet<Character>();
		for (int c = 0; c < 4; c++)
		{
			differentChars.add(Character.valueOf(inputString.charAt(c)));
		}
		if (differentChars.size() == 4)
		{
			return true;
		}
		return false;
	}

	public static int findMarkerIndex(String data)
	{
		for (int m = 4; m < data.length(); m++)
		{
			String possibleMarker = data.substring(m - 4, m);
			if (isMarker(possibleMarker))
			{
				return m;
			}
		}
		return 0;
	}

	public static void main(String[] args)
	{
		try
		{
			BufferedReader dataFile = new BufferedReader(new FileReader("data.txt"));
			String dataLine = dataFile.readLine();
			markerIndex = findMarkerIndex(dataLine);
			System.out.println("Marker index: " + markerIndex);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}

