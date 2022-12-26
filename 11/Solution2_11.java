import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Solution2_11
{
	public static final ArrayList<Monkey> monkeysList = Monkey.getMonkeysList();

	public static void main(String[] args) throws IOException
	{
		BufferedReader dataFile = new BufferedReader(new FileReader("11/data2.txt"));

		String currLine = dataFile.readLine();
		ArrayList<String> monkeyCreationCommands = new ArrayList<>();
		while (currLine != null)
		{
			if (currLine.isEmpty())
			{
				monkeysList.add(new Monkey(monkeyCreationCommands, 2));
				monkeyCreationCommands.clear();
				currLine = dataFile.readLine();
			}
			monkeyCreationCommands.add(currLine);
			currLine = dataFile.readLine();
		}

		monkeysList.forEach(Monkey::printProperties);
		for (int i = 1; i <= 10000; i++)
		{
			monkeysList.forEach(Monkey::inspectAllWorryValues);
//			if (List.of(1, 20, 100, 1000, 2000, 5000, 10000).contains(i))
//			{
//				System.out.println("== Round " + i + " ==");
//				monkeysList.forEach(Monkey::printProperties);
//			}
			if (i % 100 == 0)
				System.out.println(i);
		}

		ArrayList<Integer> numInspectedItemsList = new ArrayList<>();
		monkeysList.forEach(m -> numInspectedItemsList.add(m.getNumItemsInspected()));
		numInspectedItemsList.sort(Collections.reverseOrder());
		Integer monkeyBusiness = numInspectedItemsList.get(0) * numInspectedItemsList.get(1);

		System.out.println("The total monkey business value is: " + monkeyBusiness + ".");
	}
}
