import java.io.*;
import java.util.*;

public class Solution2_8
{
	// TODO Change this

	public static HashMap<String, int[][]> getRowsAndColumns() throws IOException
	{
		int[][] heightRows = new int[99][99];
		int[][] heightColumns = new int[99][99];
		HashMap<String, int[][]> rowsAndColumns = new HashMap<>();
		rowsAndColumns.put("row", heightRows);
		rowsAndColumns.put("col", heightColumns);

		BufferedReader dataFile = new BufferedReader(new FileReader("data.txt"));

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

	public static int[] reverseArray(int[] array)
	{
		if (array.length != 0)
		{
			int[] reverse = new int[array.length];
			for (int i = 0; i < array.length - 1; i++)
			{
				reverse[i] = array[array.length - 1 - i];
			}
			return reverse;
		}
		return array;
	}

	// TODO Somehow this method is wrong, change it
	public static int scenicValueOf(int[] array, int treeValue, boolean removeIndex0)
	{
		int scenicScore = 0;
		System.out.println(array.length);

		if (array.length == 0)
		{
			return 0;
		}
		if (removeIndex0)
		{
			array[0] = -1;
		}
		// val 5, {-1, 2, 4, 3, 7}
		// val 5, {3, 2, 2, 1}
		for (int i = 0; i < array.length; i++)
		{
			scenicScore++;
			if (array[i] >= treeValue)
			{
				break;
			}	
		}
		return scenicScore;
	}

	// TODO Change this
	public static int getTreeScenicValue(HashMap<String, int[][]> rowsAndColumns, int rowNum, int colNum)
	{
		int treeValue = rowsAndColumns.get("row")[rowNum][colNum];
		int[] row = rowsAndColumns.get("row")[rowNum];
		int[] col = rowsAndColumns.get("col")[colNum];

		// TODO fix whatever this is
		int[] left = Arrays.copyOfRange(row, 0, colNum);
		left = reverseArray(left);
		int[] right = Arrays.copyOfRange(row, colNum, 99);
		int[] up = Arrays.copyOfRange(col, 0, rowNum);
		up = reverseArray(up);
		int[] down = Arrays.copyOfRange(col, rowNum, 99);

		boolean removeIndex0 = false;
		int totalScenicValue = 1;
		for (int[] arrayToScan : new int[][] {left, right, up, down})
		{
			System.out.print(scenicValueOf(arrayToScan, treeValue, removeIndex0) + " ");
			totalScenicValue *= scenicValueOf(arrayToScan, treeValue, removeIndex0);
			removeIndex0 = !removeIndex0;
		}
		System.out.println();
		return totalScenicValue;
	}

	// TODO Change this
	public static void main(String[] args) throws IOException
	{
		int totalScenicValue = -1;

		HashMap<String, int[][]> rowsAndColumns = getRowsAndColumns();
		for (int row = 1; row < 2; row++)
		{
			for (int col = 1; col < 2; col++)
			{
				totalScenicValue = getTreeScenicValue(rowsAndColumns, row, col);
			}
		}
	}
}
