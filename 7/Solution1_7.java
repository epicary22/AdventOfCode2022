public class Solution1_7
{
    public static void main(String[] args)
    {
        Directory d = new Directory("ROOT");
        Directory e = new Directory("e");
        Directory f = new Directory("f");
        Directory g = new Directory("g");
        for (Directory dir : new Directory[] {d, e, f, g})
        {
            for (int i = 0; i < 5; i++)
            {
                dir.addFileSize(i);
            }
        }
        d.addDirectory(e);
        e.addDirectory(f);
        e.addDirectory(g);
        d.printContents();
    }
}
