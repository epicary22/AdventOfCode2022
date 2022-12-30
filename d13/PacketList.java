import java.util.*;

public class PacketList
{
	private final ArrayList<PacketList> itemsList = new ArrayList<>();

	public PacketList(String packetListString)
	{
		this.interpretPacketListString(packetListString);
	}

	private void interpretPacketListString(String packetListString)
	{
		int currentListLayer = 0;
		String layerString = "";
		// [1,[2, 3]]
		// Ofc char 0 will be '[';
		for (char packetListChar : packetListString.toCharArray())
		{
			switch (packetListChar)
			{
				case '[':
					currentListLayer++;
					break;
				case ']':
					currentListLayer--;
					break;
				default:
					if (currentListLayer == 1)
						layerString += packetListChar;
					break;
			}
		}
		System.out.println(layerString);
	}

	public ArrayList<PacketList> getPacketList()
	{
		return this.itemsList;
	}
}
