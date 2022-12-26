//import java.util.*;
//import java.io.*;
//
//public class Solution1_11
//{
//	public static final ArrayList<Monkey1> monkeysList = Monkey1.getMonkey1sList();
//
//	public static void main(String[] args) throws IOException
//	{
//		BufferedReader dataFile = new BufferedReader(new FileReader("11/data.txt"));
//
//		String currLine = dataFile.readLine();
//		ArrayList<String> monkeyCreationCommands = new ArrayList<>();
//		while (currLine != null)
//		{
//			if (currLine.isEmpty())
//			{
//				monkeysList.add(new Monkey1(monkeyCreationCommands, 1));
//				monkeyCreationCommands.clear();
//				currLine = dataFile.readLine();
//			}
//			monkeyCreationCommands.add(currLine);
//			currLine = dataFile.readLine();
//		}
//
//		monkeysList.forEach(Monkey1::printProperties);
//		for (int i = 1; i <= 20; i++)
//		{
//			System.out.println("== Round " + i + " ==");
//			monkeysList.forEach(Monkey1::inspectAllWorryValues);
//			monkeysList.forEach(Monkey1::printProperties);
//		}
//
//		ArrayList<Long> numInspectedItemsList = new ArrayList<>();
//		monkeysList.forEach(m -> numInspectedItemsList.add(m.getNumItemsInspected()));
//		numInspectedItemsList.sort(Collections.reverseOrder());
//		long monkeyBusiness = numInspectedItemsList.get(0) * numInspectedItemsList.get(1);
//
//		System.out.println("The total monkey business value is: " + monkeyBusiness + ".");
//	}
//}
