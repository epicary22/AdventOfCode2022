import java.io.*;
import java.util.Objects;

public class Solution1_7
{
    public static Directory getFileTree() throws IOException, InterruptedException
    {
        BufferedReader logLines = new BufferedReader(new FileReader("7/data.txt"));
        Directory root = new Directory("");
        Directory currentDirectory = root;

        // Skip line 1
        logLines.readLine();

        String logLine = logLines.readLine();
        while (logLine != null)
        {
            String[] commandParts = logLine.split(" ");
            if (commandParts[0].equals("$"))
            {
                if (commandParts[1].equals("ls"))
                {
                    //
                }
                else if (commandParts[1].equals("cd"))
                {
                    String dirName = commandParts[2];
                    if (dirName.equals(".."))
                    {
                        currentDirectory = currentDirectory.getContainerDirectory();
                    }
                    else
                    {
                        currentDirectory = currentDirectory.getDirectoriesMap().get(dirName);
                    }
                }
            }
            else
            {
                if (commandParts[0].equals("dir"))
                {
                    String dirName = commandParts[1];
                    currentDirectory.addDirectory(new Directory(dirName, currentDirectory));
                }
                else
                {
                    Integer fileSize = Integer.valueOf(commandParts[0]);
                    currentDirectory.addFileSize(fileSize);
                }
            }
            logLine = logLines.readLine();
        }

        return root;
    }

    public static void main(String[] args) throws IOException, InterruptedException
    {
        Directory fileTree = getFileTree();
        fileTree.printContents();
    }
}
