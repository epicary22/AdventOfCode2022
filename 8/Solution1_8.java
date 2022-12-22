import java.io.*;
import java.util.*;

public class Solution1_8
{
	public static int visibleTreeCount;

	public static HashMap<String, int[][]> getRowsAndColumns() throws IOException
	{
		int[][] heightRows = new int[99][99];
		int[][] heightColumns = new int[99][99];
		HashMap<String, int[][]> rowsAndColumns = new HashMap<>();
		rowsAndColumns.put("row", heightRows);
		rowsAndColumns.put("col", heightColumns);

		BufferedReader dataFile = new BufferedReader(new FileReader("8/data.txt"));

		for (int row = 0; row < 99; row++)
		{
			String currLine = dataFile.readLine();
			for (int col = 0; col < 99; col++)
			{
				int treeHeight = currLine.charAt(col) - '0';
				heightRows[row][col] = treeHeight;
				heightColumns[col][row] = treeHeight;
			}
		}

		return rowsAndColumns;
	}

	public static int highestValInArray(int[] array, boolean removeIndex0)
	{
		if (array.length == 0)
		{
			return -1;
		}
		if (removeIndex0)
		{
			array[0] = -1;
		}
		Arrays.sort(array);
		return array[array.length - 1];
	}

	public static boolean scanRowAndColumn(HashMap<String, int[][]> rowsAndColumns, int rowNum, int colNum)
	{
		int treeValue = rowsAndColumns.get("row")[rowNum][colNum];
		int[] row = rowsAndColumns.get("row")[rowNum];
		int[] col = rowsAndColumns.get("col")[colNum];

		int[] left = Arrays.copyOfRange(row, 0, colNum);
		int[] right = Arrays.copyOfRange(row, colNum, 99);
		int[] up = Arrays.copyOfRange(col, 0, rowNum);
		int[] down = Arrays.copyOfRange(col, rowNum, 99);

		boolean firstIndexRemoved = false;
		for (int[] arrayToScan : new int[][] {left, right, up, down})
		{
			if (treeValue > highestValInArray(arrayToScan, firstIndexRemoved))
			{
				return true;
			}
			firstIndexRemoved = !firstIndexRemoved;
		}
		return false;
	}

	public static void main(String[] args) throws IOException
	{
		HashMap<String, int[][]> rowsAndColumns = getRowsAndColumns();
		for (int row = 0; row < 99; row++)
		{
			for (int col = 0; col < 99; col++)
			{
				if (scanRowAndColumn(rowsAndColumns, row, col))
				{
					visibleTreeCount += 1;
				}
			}
		}

		System.out.println(visibleTreeCount);
	}
}
