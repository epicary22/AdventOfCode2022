import java.awt.Point;

public class MapPoint
{
	private final Point pos;
	private final char height;
	private final SpecialtyPoint pointType;
	public enum SpecialtyPoint {START, END, NORMAL, NONEXISTENT}

	public MapPoint(int xPos, int yPos, char height)
	{
		this.pos = new Point();
		this.pos.setLocation(xPos, yPos);
		switch (height)
		{
			case 'S':
				this.pointType = SpecialtyPoint.START;
				this.height = 'a';
				break;
			case 'E':
				this.pointType = SpecialtyPoint.END;
				this.height = 'z';
				break;
			case '~':
				this.pointType = SpecialtyPoint.NONEXISTENT;
				this.height = '~';
				break;
			default:
				this.pointType = SpecialtyPoint.NORMAL;
				this.height = height;
		}
	}

	public Point getPos()
	{
		return this.pos;
	}

	public int getX()
	{
		return (int) this.pos.getX();
	}

	public int getY()
	{
		return (int) this.pos.getY();
	}

	public char getHeight()
	{
		return this.height;
	}

	public SpecialtyPoint getPointType()
	{
		return this.pointType;
	}

	public boolean equals(MapPoint mp)
	{
		return (this.getPos().equals(mp.getPos()));
	}

	public int heightDifference(MapPoint mp)
	{
		return (mp.getHeight()  - this.getHeight());
	}

	public boolean canStepTo(MapPoint mp)
	{
		return (this.heightDifference(mp) <= 1);
	}

	public String getPropertiesString()
	{
		return String.format("(%d, %d)\t\t%c %s", this.getX(), this.getY(), this.getHeight(), this.pointType.toString());
	}
}
