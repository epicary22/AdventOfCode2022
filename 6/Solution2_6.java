import java.io.*;
import java.util.*;

public class Solution2_6
{

	public static int markerIndex;
	public static final int MARKER_SIZE = 14;

	public static boolean isMarker(String inputString)
	{
		HashSet<Character> differentChars = new HashSet<Character>();
		for (int c = 0; c < MARKER_SIZE; c++)
		{
			differentChars.add(Character.valueOf(inputString.charAt(c)));
		}
		if (differentChars.size() == MARKER_SIZE)
		{
			return true;
		}
		return false;
	}

	public static int findMarkerIndex(String data)
	{
		for (int m = MARKER_SIZE; m < data.length(); m++)
		{
			String possibleMarker = data.substring(m - MARKER_SIZE, m);
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

