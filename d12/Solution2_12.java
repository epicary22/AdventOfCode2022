import java.util.*;

public class Solution2_12
{
	public static void main(String[] args)
	{
		ArrayList<Integer> distances = new ArrayList<>();
		for (int startingY = 0; startingY <= 4; startingY++)
			distances.add(new StepTracer(0, startingY, "d12/data.txt").getMinStepsToEndPoint());
		Collections.sort(distances);
		System.out.println("The shortest distance from any 'a' point to 'E' is " + distances.get(0));
	}
}
