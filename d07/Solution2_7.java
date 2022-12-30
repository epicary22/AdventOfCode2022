import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution2_7
{

    public static final Integer MIN_SPACE = 30_000_000;
    public static final Integer STORAGE_SIZE = 70_000_000;

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

    public static Integer findBestDirectoryToDelete() throws IOException
    {
        Integer bestDirectorySize = Integer.MAX_VALUE;
        BufferedReader formattedFileTree = new BufferedReader(new FileReader("7/file_tree.txt"));
        String line = formattedFileTree.readLine();
        Integer min_size = MIN_SPACE - (STORAGE_SIZE - Integer.parseInt(line.split(" ")[1]));
        System.out.println(min_size);
        while (line != null)
        {
            if (!line.isEmpty())
            {
                String strDirSize = line.split(" ")[1];
                Integer directorySize = Integer.valueOf(strDirSize);
                if (directorySize >= min_size)
                {
                    if (directorySize < bestDirectorySize)
                    {
                        bestDirectorySize = directorySize;
                    }
                }
            }
            line = formattedFileTree.readLine();
        }
        return bestDirectorySize;
    }

    public static void main(String[] args) throws IOException, InterruptedException
    {
        int bestDirectorySize = findBestDirectoryToDelete();
        System.out.println("The best directory size is: " + bestDirectorySize +
                ", leaving you with " + (70_000_000 - 40268565 + bestDirectorySize) + "B.");
    }
}
