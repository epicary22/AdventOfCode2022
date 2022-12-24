import java.util.*;

public class Coordinate
{
    private Integer xPos = 0;
    private Integer yPos = 0;

    public Coordinate()
    {
    }

//    public Coordinate(Integer xPos, Integer yPos)
//    {
//        this.xPos = xPos;
//        this.yPos = yPos;
//    }

    public void addToX(Integer increment)
    {
        this.xPos += increment;
    }

    public void addToY(Integer increment)
    {
        this.yPos += increment;
    }

//    public void setX(Integer value)
//    {
//        this.xPos = value;
//    }
//
//    public void setY(Integer value)
//    {
//        this.yPos = value;
//    }
//
//    public void setXAndY(ArrayList<Integer> position)
//    {
//        this.xPos = position.get(0);
//        this.yPos = position.get(1);
//    }

    public Integer getX()
    {
        return this.xPos;
    }

    public Integer getY()
    {
        return this.yPos;
    }

    public ArrayList<Integer> asArrayList()
    {
        return new ArrayList<>(List.of(this.xPos, this.yPos));
    }

    public boolean inRangeOf1(Coordinate c)
    {
        return (Math.abs(this.xPos - c.getX()) <= 1 && Math.abs(this.yPos - c.getY()) <= 1);
    }

    public ArrayList<Integer> distanceFrom(Coordinate c)
    {
        return new ArrayList<>(List.of(c.getX() - this.getX(), c.getY() - this.getY()));
    }

    public void setX(Integer xPos)
    {
        this.xPos = xPos;
    }

    public void setY(Integer yPos)
    {
        this.yPos = yPos;
    }

    public void setToCoordinate(Coordinate c)
    {
        this.xPos = c.getX();
        this.yPos = c.getY();
    }

    public void setToArrayList(ArrayList<Integer> pos)
    {
        this.xPos = pos.get(0);
        this.yPos = pos.get(1);
    }
}
