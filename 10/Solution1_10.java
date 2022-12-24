import java.io.*;
import java.util.*;

public class Solution1_10
{
	private static int currentTick = 1;
	private static int xRegister = 1;
	private static final ArrayList<Integer> signalStrengths = new ArrayList<>();

	private static void executeCommand(String command)
	{
		if (command.equals("noop"))
			tick();
		else if (command.startsWith("addx"))
		{
			int valueToAdd = Integer.parseInt(command.split(" ")[1]);
			tick();
			tick();
			xRegister += valueToAdd;
		}
	}

	private static void tick()
	{
		if (currentTick % 40 == 20)
			signalStrengths.add(currentTick * xRegister);
		currentTick++;
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader dataFile = new BufferedReader(new FileReader("10/data.txt"));

		String command = dataFile.readLine();
		while (command != null)
		{
			executeCommand(command);
			command = dataFile.readLine();
		}

		System.out.print("Signal strengths: ");
		signalStrengths.forEach(s -> System.out.print(s + " "));
		System.out.println();

		int sum = 0;
		for (Integer i : signalStrengths)
			sum += i;
		System.out.println("Sum of the signal strengths: " + sum);
	}
}