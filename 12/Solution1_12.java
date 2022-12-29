import java.awt.*;
import java.util.List;

public class Solution1_12
{
	public static void main(String[] args)
	{
		StepTracer s = new StepTracer(0, 20, "/home/epicary22/AdventOfCode2022/12/data.txt");
//		s.printMinStepsToPoints();
		s.printMinStepsMaps();
		System.out.println("It takes " + s.getMinStepsToEndPoint() + " steps to get from 'S' to 'E'.");
	}
}
