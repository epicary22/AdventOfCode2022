import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Solution2_10
{
	private static int currentTick = 1;
	private static int xRegister = 1;
	private static final ArrayList<Boolean> crtPixelsOn = new ArrayList<>();

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

	private static int getPixelToScan()
	{
		return currentTick % 40 - 1;
	}

	private static void addCrtPixel()
	{
		boolean pixelOn = Math.abs(getPixelToScan() - xRegister) <= 1;
		crtPixelsOn.add(pixelOn);
	}

	private static void tick()
	{
		addCrtPixel();
		currentTick++;
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader dataFile = new BufferedReader(new FileReader("d10/data.txt"));

		String command = dataFile.readLine();
		while (command != null)
		{
			executeCommand(command);
			command = dataFile.readLine();
		}

		for (int i = 0; i < 240; i++)
		{
			Boolean pixelOn = crtPixelsOn.get(i);
			if (pixelOn)
				System.out.print('#');
			else
				System.out.print('.');

			if (i % 40 == 39)
				System.out.println();
		}
	}
}