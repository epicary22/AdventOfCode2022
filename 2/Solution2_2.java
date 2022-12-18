import java.io.*;

public class Solution2_2
{
	
	static int myScore;
	static final int[][] SCORES = {
		// Los,   tie,   win;
		{3 + 0, 1 + 3, 2 + 6}, // Their Rock
		{1 + 0, 2 + 3, 3 + 6}, // Their paper
		{2 + 0, 3 + 3, 1 + 6}  // Their scissors
	};

	public static int toScore(String roundInput)
	{
		char yourPlay, myPlay;
		int roundScore;

		// Input: [A|B|C] [X|Y|Z];
		// Internal: X == A, Y == B, Z == C;
		// Internal: Y >> A, Z >> B, X >> C;
		// Internal: Z << A, X << B, Y << C;
		// Output: Loss = 0, Tie = 3, Win = 6;
		// Output: A|X = 1, B|Y = 2, C|Z = 3;

		yourPlay = roundInput.charAt(0);
		myPlay = roundInput.charAt(2);
		roundScore = SCORES[yourPlay - 'A'][myPlay - 'X'];

		return roundScore;
	}

	public static void main(String[] args) throws IOException
	{
		String currLine;

		BufferedReader dataFile = new BufferedReader(new FileReader("data.txt"));

		currLine = dataFile.readLine();
		while (currLine != null)
		{
			myScore += toScore(currLine);
			currLine = dataFile.readLine();
		}

		System.out.printf("Your total score will be: %d%n", myScore);
	}
}
