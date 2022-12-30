import java.util.*;

public class PacketPair
{
	private PacketList right;
	private PacketList left;

	public PacketPair(String leftString, String rightString)
	{
		this.left = new PacketList(leftString);
		this.right = new PacketList(rightString);
	}

	public boolean inCorrectOrder()
	{
		ArrayList<PacketList> rightPacketLists = this.right.getListOfPacketLists();
		ArrayList<PacketList> leftPacketLists = this.left.getListOfPacketLists();

		for (int index = 0; index < 999; index++)
		{
			// TODO Man I don't like this day. Too much bruteforce.
		}
		return false;
	}
}
