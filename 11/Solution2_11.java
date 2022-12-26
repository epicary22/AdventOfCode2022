import java.io.*;
import java.util.*;

public class Solution2_11
{
	public static final ArrayList<Monkey> monkeysList = Monkey.getMonkeysList();

	public static void main(String[] args) throws IOException
	{
		BufferedReader dataFile = new BufferedReader(new FileReader("11/data.txt"));

		String currLine = dataFile.readLine();
		ArrayList<String> monkeyCreationCommands = new ArrayList<>();
		while (currLine != null)
		{
			if (currLine.isEmpty())
			{
				monkeysList.add(new Monkey(monkeyCreationCommands));
				monkeyCreationCommands.clear();
				currLine = dataFile.readLine();
			}
			monkeyCreationCommands.add(currLine);
			currLine = dataFile.readLine();
		}

		monkeysList.forEach(Monkey::initializeItemValues);
		for (int round = 1; round <= 10000; round++)
			monkeysList.forEach(Monkey::inspectAllItems);
		monkeysList.forEach(Monkey::printProperties);

		ArrayList<Integer> numInspectedItemsList = new ArrayList<>();
		monkeysList.forEach(m -> numInspectedItemsList.add(m.getNumItemsInspected()));
		numInspectedItemsList.sort(Collections.reverseOrder());
		long monkeyBusiness = (long) numInspectedItemsList.get(0) * numInspectedItemsList.get(1);

		System.out.println("The total monkey business value is: " + monkeyBusiness + ".");
	}
}
