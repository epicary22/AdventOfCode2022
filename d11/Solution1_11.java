import java.util.*;
import java.io.*;

public class Solution1_11
{
	public static final ArrayList<Monkey> monkeysList = Monkey.getMonkeysList();

	public static void main(String[] args) throws IOException
	{
		BufferedReader dataFile = new BufferedReader(new FileReader("d11/data.txt"));

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
		monkeysList.forEach(Monkey::printProperties);
		for (int i = 1; i <= 20; i++)
		{
			System.out.println("== Round " + i + " ==");
			monkeysList.forEach(Monkey::inspectAllItems);
			monkeysList.forEach(Monkey::printProperties);
		}

		ArrayList<Integer> numInspectedItemsList = new ArrayList<>();
		monkeysList.forEach(m -> numInspectedItemsList.add(m.getNumItemsInspected()));
		numInspectedItemsList.sort(Collections.reverseOrder());
		long monkeyBusiness = (long) numInspectedItemsList.get(0) * numInspectedItemsList.get(1);

		System.out.println("The total monkey business value is: " + monkeyBusiness + ".");
	}
}
