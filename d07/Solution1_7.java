import java.io.*;
import java.util.Objects;

public class Solution1_7
{
    public static Directory getFileTree() throws IOException, InterruptedException
    {
        BufferedReader logLines = new BufferedReader(new FileReader("d07/data.txt"));
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

    public static String printDirSizes(Directory currentDirectory, String out)
    {
        out += String.format("%s %d%n", currentDirectory.getDirectoryPath(), currentDirectory.getCompleteByteTotal());
        for (String dirName : currentDirectory.getDirectoriesMap().keySet())
        {
            Directory subDirectory = currentDirectory.getDirectoriesMap().get(dirName);
            out = printDirSizes(subDirectory, out);
        }
        return out;
    }

    public static String formatDirectory(Directory fileTree)
    {
        String out = "/";
        out = printDirSizes(fileTree, out);
        return out;
    }

    public static Integer getSpecificDirectorySums() throws IOException
    {
        Integer directorySizeSum = 0;
        BufferedReader formattedFileTree = new BufferedReader(new FileReader("7/file_tree.txt"));
        String line = formattedFileTree.readLine();
        while (line != null)
        {
            if (!line.isEmpty())
            {
                String strDirSize = line.split(" ")[1];
                Integer directorySize = Integer.valueOf(strDirSize);
                if (directorySize <= 100_000)
                {
                    directorySizeSum += directorySize;
                }
            }
            line = formattedFileTree.readLine();
        }
        return directorySizeSum;
    }

    public static void main(String[] args) throws IOException, InterruptedException
    {
        int finalTotal = getSpecificDirectorySums();
        System.out.println("The final total of Bytes is: " + finalTotal);
    }
}
