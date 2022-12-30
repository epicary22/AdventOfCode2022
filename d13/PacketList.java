import java.util.*;

public class PacketList
{
	private final ArrayList<PacketList> itemsList = new ArrayList<>();
	private int individualIntValue;
	private boolean isIndividualInt;
	private boolean isEmpty;


	public PacketList(String packetListString)
	{
		System.out.println("new PacketList initializing...");
		this.interpretPacketListString(packetListString);
		System.out.println("new PacketList initialized!");
	}

	public PacketList(int individualInteger)
	{
		this.individualIntValue = individualInteger;
		this.isIndividualInt = true;
		System.out.println("new INT PacketList initialized! " + this.individualIntValue);
	}

	public PacketList()
	{
		this.isEmpty = true;
		System.out.println("new EMPTY PacketList initialized!");
	}

	private void interpretPacketListString(String packetListString)
	{
		int currentListLayer = 0;
		String layerString = "";
		boolean listHasEnded = false;
		// [1,[2, 3]]
		// Ofc char 0 will be '[';
		String newInteger = "";
		for (int charIndex = 0; charIndex < packetListString.length(); charIndex++)
		{
			char packetListChar = packetListString.charAt(charIndex);
			switch (packetListChar)
			{
				case '[':
					if (currentListLayer == 1)
						itemsList.add(new PacketList(packetListString.substring(charIndex)));
					currentListLayer++;
					break;
				case ']':
					if (currentListLayer == 1)
					{
						listHasEnded = true;
						if (!newInteger.isEmpty())
						{
							this.itemsList.add(new PacketList(Integer.parseInt(newInteger)));
							newInteger = "";
						}
						break;
					}
					if (packetListString.startsWith("[]"))
						itemsList.add(new PacketList());
					currentListLayer--;
					break;
				case ',':
					if (currentListLayer == 1 && !newInteger.isEmpty())
					{
						this.itemsList.add(new PacketList(Integer.parseInt(newInteger)));
						newInteger = "";
					}
					break;
				default:
					if (currentListLayer == 1)
						newInteger += packetListChar;
					break;
			}
			if (listHasEnded)
				break;
		}
	}

	public ArrayList<PacketList> getListOfPacketLists()
	{
		return this.itemsList;
	}

	public int getIndividualIntValue()
	{
		if (this.isIndividualInt)
			return this.individualIntValue;
		else
			return -1;
	}
}
