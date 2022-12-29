import java.awt.*;
import java.util.List;

public class Solution1_12
{
	public static void main(String[] args)
	{
		StepTracer s = new StepTracer(0, 0, "12/data.txt");
		System.out.println("It takes " + s.getMinStepsToEndPoint() + " steps to get from 'S' to 'E'.");

//		for (MapPoint mp : elevationMap.getAdjacentPoints(90, 39).values())
//		{
//			System.out.printf("(%d, %d) \t\t%c%n", mp.getX(), mp.getY(), mp.getHeight());
//		}

//		MapPoint a = new MapPoint(0, 1, 'a');
//		MapPoint b = new MapPoint(1, 1, 'b');
//		System.out.println(a.canStepTo(b));
	}
}
