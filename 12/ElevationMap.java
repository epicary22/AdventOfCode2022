import java.io.*;
import java.util.*;

public class ElevationMap
{
	private final ArrayList<ArrayList<MapPoint>> elevationMap = new ArrayList<>();
	private int width;
	private int height;

	public ElevationMap(String filename)
	{
		try
		{
			BufferedReader dataFile = new BufferedReader(new FileReader(filename));
			String currLine = dataFile.readLine();
			int scanX = 0;
			int scanY = 0;
			while (currLine != null)
			{
				elevationMap.add(new ArrayList<>());
				for (char height : currLine.toCharArray())
				{
					elevationMap.get(scanY).add(new MapPoint(scanX, scanY, height));
					scanX++;
				}
				scanX = 0;
				scanY++;
				currLine = dataFile.readLine();
			}

			this.height = this.elevationMap.size();
			this.width = this.elevationMap.get(0).size();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public HashSet<MapPoint> getAdjacentPoints(MapPoint currentPt)
	{
		MapPoint upPt, rightPt, downPt, leftPt;
		HashSet<MapPoint> adjacentPoints = new HashSet<>();
		if (currentPt.getY() >= this.height - 1)
			upPt = new MapPoint(-1, -1, '~');
		else
			upPt = this.elevationMap.get(currentPt.getY() + 1).get(currentPt.getX());
		if (currentPt.getX() >= this.width - 1)
			rightPt = new MapPoint(-1, -1, '~');
		else
			rightPt = this.elevationMap.get(currentPt.getY()).get(currentPt.getX() + 1);
		if (currentPt.getY() <= 0)
			downPt = new MapPoint(-1, -1, '~');
		else
			downPt = this.elevationMap.get(currentPt.getY() - 1).get(currentPt.getX());
		if (currentPt.getX() <= 0)
			leftPt = new MapPoint(-1, -1, '~');
		else
			leftPt = this.elevationMap.get(currentPt.getY()).get(currentPt.getX() - 1);
		// Java 8 is whiny about Set.of();
		// return new HashSet<>(Set.of(upPt, rightPt, downPt, leftPt));
		for (MapPoint mp : new MapPoint[] {upPt, rightPt, downPt, leftPt})
			adjacentPoints.add(mp);
		return adjacentPoints;
	}

	public MapPoint getMapPoint(int x, int y)
	{
		return this.elevationMap.get(y).get(x);
	}

	public void printElevationMap()
	{
		System.out.print("\t");
		for (int x = 0; x < this.width; x++)
			System.out.print(x / 10);
		System.out.print("\n\t");
		for (int x = 0; x < this.width; x++)
		{
			System.out.print(x % 10);
		}

		for (int y = 0; y < this.height; y++)
		{
			System.out.print("\n" + y + "\t");
			for (MapPoint mp : this.elevationMap.get(y))
				System.out.print(mp.getHeight());
		}
	}

	public int getHeight()
	{
		return this.height;
	}

	public int getWidth()
	{
		return this.width;
	}
}
