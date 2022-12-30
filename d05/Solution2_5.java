import java.io.*;
import java.util.*;

public class Solution2_5
{

	public static ArrayList<ArrayList<String>> getCrates(BufferedReader dataFile) throws IOException
	{
		ArrayList<ArrayList<String>> crates = new ArrayList<>(9);
		for (int i = 0; i < 9; i++)
		{
			crates.add(new ArrayList<String>());
		}

		String currLine = "TEMP";
		for (int row = 0; row < 8; row++)
		{
			currLine = dataFile.readLine();
			for (int i = 0; i < 9; i++)
			{
				int column = i * 4 + 1;
				char crateName = currLine.charAt(column);
				if (crateName != ' ')
				{
					crates.get(i).add(0, String.valueOf(crateName));
				}
			}
		}

//		int j = 0;
//		for (ArrayList<String> crateStack : crates)
//		{
//			System.out.print("\n" + j + " ");
//			for (String crate : crateStack)
//			{
//				System.out.print(crate + " ");
//			}
//			j++;
//		}

		return crates;
	}

	public static void runCrateInstructions(ArrayList<ArrayList<String>> crates, BufferedReader instructions) throws IOException
	{
		String currLine = instructions.readLine();
		int i = 0;
		while (currLine != null)
		{
			String[] instruction = currLine.split(" ");
			int sliceHeight = Integer.parseInt(instruction[1]);
			ArrayList<String> fromColumn = crates.get(Integer.parseInt(instruction[3]) - 1);
			ArrayList<String> toColumn = crates.get(Integer.parseInt(instruction[5]) - 1);
			int fromColumnSliceIndex = fromColumn.size() - sliceHeight;

			List<String> crateStack = fromColumn.subList(fromColumnSliceIndex, fromColumn.size());
			// Collections.reverse(crateStack);

			// System.out.printf("%s%nfrom: %s%nslice: %s%nto: %s%n", currLine, String.join(" ", fromColumn), String.join(" ", crateStack), String.join(" ", toColumn));

			toColumn.addAll(crateStack);
			crateStack.clear();

			currLine = instructions.readLine();
		}
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader dataFile = new BufferedReader(new FileReader("d05/data.txt"));

		// Reads crate lines
		ArrayList<ArrayList<String>> crates = getCrates(dataFile);
		// Skip empty lines
		for (int l = 0; l < 2; l++)
		{
			dataFile.readLine();
		}
		// Read instructions and execute
		runCrateInstructions(crates, dataFile);

		for (ArrayList<String> crateStack : crates)
		{
			for (String crate : crateStack)
			{
				System.out.printf("%s ", crate);
			}
			System.out.println();
		}

		String finalMessage = "";
		for (ArrayList<String> crateStack : crates)
		{
			finalMessage += crateStack.get(crateStack.size() - 1);
		}

		System.out.printf("The final message is: %s.%n", finalMessage);
	}
}
