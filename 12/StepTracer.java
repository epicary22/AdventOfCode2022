import java.util.*;

public class StepTracer
{
	private final ElevationMap elevationMap;
	private final MapPoint rootStartingPoint;
	private final ArrayList<HashSet<MapPoint>> minStepsToPoints = new ArrayList<>();

	public StepTracer(int startX, int startY, String filename)
	{
		this.elevationMap = new ElevationMap(filename);
		this.rootStartingPoint = elevationMap.getMapPoint(startX, startY);
		this.calculateMinStepsToPoints();
	}

	public void printMinStepsToPoints()
	{
		int stepCount = 0;
		for (HashSet<MapPoint> stepSet : this.minStepsToPoints)
		{
			System.out.println("Step " + stepCount + ":");
			for (MapPoint mp : stepSet)
			{
				System.out.println(mp.getPropertiesString());
			}
			System.out.println();
			stepCount++;
		}
	}

	public void printMinStepsMaps()
	{
		HashSet<MapPoint> allReachedPoints = new HashSet<>();
		for (HashSet<MapPoint> stepPoints : this.minStepsToPoints)
		{
			try
			{
				Thread.sleep(334);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			allReachedPoints.addAll(stepPoints);
			printMapPoints(allReachedPoints);
		}
	}

	public void printMapPoints(HashSet<MapPoint> mapPoints)
	{
		for (int i = 0; i < 10; i++)
			System.out.println();

		for (int row = 0; row < elevationMap.getHeight(); row++)
		{
			for (int col = 0; col < elevationMap.getWidth(); col++)
			{
				boolean pointAtThisPos = false;
				for (MapPoint mp : mapPoints)
				{
					if (mp.getX() == col && mp.getY() == row)
					{
						System.out.print(mp.getHeight());
						pointAtThisPos = true;
						break;
					}
				}
				if (!pointAtThisPos)
					System.out.print(' ');
			}
			System.out.println();
		}
	}

	private void calculateMinStepsToPoints()
	{
		this.minStepsToPoints.add(new HashSet<>(Set.of(this.rootStartingPoint)));
		while (this.minStepsToPoints.get(this.minStepsToPoints.size() - 1).size() != 0)
		{
			addNextStepOfPoints();
		}
	}

	private void addNextStepOfPoints()
	{
		HashSet<MapPoint> newPoints = new HashSet<>();
		for (MapPoint oldPoint : this.minStepsToPoints.get(this.minStepsToPoints.size() - 1))
		{
			for(MapPoint possibleNewPoint : elevationMap.getAdjacentPoints(oldPoint))
			{
				if (oldPoint.canStepTo(possibleNewPoint) && !this.hasBeenSteppedTo(possibleNewPoint))
					newPoints.add(possibleNewPoint);
			}
		}
		this.minStepsToPoints.add(newPoints);
	}

	private boolean hasBeenSteppedTo(MapPoint mp)
	{
		for (HashSet<MapPoint> alreadySteppedSet : this.minStepsToPoints)
		{
			for (MapPoint alreadySteppedToPoint : alreadySteppedSet)
			{
				if (mp.equals(alreadySteppedToPoint))
					return true;
			}
		}
		return false;
	}

	public int getMinStepsToEndPoint()
	{
		int stepCount = 0;
		for (HashSet<MapPoint> stepPoints : this.minStepsToPoints)
		{
			for (MapPoint mp : stepPoints)
			{
				if (mp.getPointType() == MapPoint.SpecialtyPoint.END)
					return stepCount;
			}
			stepCount++;
		}
		return -1;
	}
}
