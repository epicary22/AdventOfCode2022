import java.io.*;

public class Solution1_13
{
	public static void main(String[] args)
	{
		try
		{
			BufferedReader dataFile = new BufferedReader(new FileReader("d13/data.txt"));
			PacketList p = new PacketList(dataFile.readLine());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
