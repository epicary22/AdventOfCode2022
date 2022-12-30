import java.io.*;
import java.util.*;

public class Solution2_8
{
	public static HashMap<String, Integer[][]> getRowsAndColumns() throws IOException
	{
		Integer[][] heightRows = new Integer[99][99];
		Integer[][] heightColumns = new Integer[99][99];
		HashMap<String, Integer[][]> rowsAndColumns = new HashMap<>();
		rowsAndColumns.put("row", heightRows);
		rowsAndColumns.put("col", heightColumns);

		BufferedReader dataFile = new BufferedReader(new FileReader("d08/data.txt"));

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

	public static Integer scenicValueOf(ArrayList<Integer> array, Integer treeValue, boolean removeIndex0)
	{
		Integer scenicScore = 0;

		if (array.size() == 0)
		{
			return 0;
		}
		if (removeIndex0)
		{
			array.remove(0);
		}
		for (Integer i : array)
		{
			scenicScore++;
			if (i >= treeValue)
			{
				break;
			}	
		}
		return scenicScore;
	}

	public static Integer getTreeScenicValue(HashMap<String, Integer[][]> rowsAndColumns, int rowNum, int colNum)
	{
		Integer treeValue = rowsAndColumns.get("row")[rowNum][colNum];
		Integer[] row = rowsAndColumns.get("row")[rowNum];
		Integer[] col = rowsAndColumns.get("col")[colNum];

		ArrayList<Integer> left = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(row, 0, colNum)));
		Collections.reverse(left);
		ArrayList<Integer> right = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(row, colNum, 99)));
		ArrayList<Integer> up = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(col, 0, rowNum)));
		Collections.reverse(up);
		ArrayList<Integer> down = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(col, rowNum, 99)));

		boolean removeIndex0 = false;
		Integer totalScenicValue = 1;
		for (ArrayList<Integer> arrayToScan : new ArrayList[] {left, right, up, down})
		{
			totalScenicValue *= scenicValueOf(arrayToScan, treeValue, removeIndex0);
			removeIndex0 = !removeIndex0;
		}
		return totalScenicValue;
	}

	public static void main(String[] args) throws IOException
	{
		int totalScenicValue = -1;
		ArrayList<Integer> scenicValuesList = new ArrayList<>();

		HashMap<String, Integer[][]> rowsAndColumns = getRowsAndColumns();
		for (int row = 0; row < 99; row++)
		{
			for (int col = 0; col < 99; col++)
			{
				totalScenicValue = getTreeScenicValue(rowsAndColumns, row, col);
				scenicValuesList.add(totalScenicValue);
			}
		}
		scenicValuesList.sort(Collections.reverseOrder());
		System.out.println(scenicValuesList.get(0));
	}
}
