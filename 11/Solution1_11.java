import java.util.*;
import java.io.*;

public class Solution1_11
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader dataFile = new BufferedReader(new FileReader("11/data.txt"));

		String currLine = dataFile.readLine();
		ArrayList<String> createMonkeyCommands = new ArrayList<>();
		while (!currLine.isEmpty())
		{
			createMonkeyCommands.add(currLine);
			currLine = dataFile.readLine();
		}
		Monkey m = new Monkey(createMonkeyCommands);
	}
}
