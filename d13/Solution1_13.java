import java.io.*;

public class Solution1_13
{
	public static void main(String[] args)
	{
		try
		{
			BufferedReader dataFile = new BufferedReader(new FileReader("d13/data2.txt"));
			for (int i = 0; i < 21; i++)
				dataFile.readLine();
			PacketList p = new PacketList(dataFile.readLine());
			for (PacketList subP : p.getListOfPacketLists())
			{
				System.out.println(subP.getIndividualIntValue());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
