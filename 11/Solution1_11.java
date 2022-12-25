import java.util.*;
import java.io.*;

public class Solution1_11
{
	public static final ArrayList<Monkey> monkeys = new ArrayList<>();

	public static void main(String[] args) throws IOException
	{
		BufferedReader dataFile = new BufferedReader(new FileReader("11/data.txt"));

		String currLine = dataFile.readLine();
		ArrayList<String> createMonkeyCommands = new ArrayList<>();
		for (int i = 0; i < 8; i++)
		{
			createMonkeyCommands.clear();
			while (currLine != null && !currLine.isEmpty())
			{
				createMonkeyCommands.add(currLine);
				currLine = dataFile.readLine();
			}
			monkeys.add(new Monkey(createMonkeyCommands));
			currLine = dataFile.readLine();
		}

		monkeys.forEach(Monkey::printProperties);
	}
}
