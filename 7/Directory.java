import java.util.*;

public class Directory {
    public ArrayList<Integer> fileSizesList = new ArrayList<>();
    public HashSet<Directory> directoriesSet = new HashSet<>();
    public String name;
    public String containerDirectory;
    public String dirPath;

    public Directory(String name)
    {
        this.name = name;
    }

    public Directory(String name, String containerDirectory)
    {
        this.name = name;
        this.containerDirectory = containerDirectory;
        this.dirPath = containerDirectory + name;
    }

    public Integer getCompleteByteTotal()
    {
        // This directory's bytes
        Integer total = this.getCurrentDirectoryByteTotal();
        // Subdirectories' bytes
        for (Directory directory : this.directoriesSet)
        {
            total += directory.getCompleteByteTotal();
        }
        return total;  // TODO get this counter thing done
    }

    public Integer getCurrentDirectoryByteTotal()
    {
        Integer total = 0;
        for (Integer fileSize : this.fileSizesList) {
            total += fileSize;
        }
        return total;
    }

    public void addFileSize(Integer fileSize)
    {
        this.fileSizesList.add(fileSize);
    }

    public void addDirectory(Directory directory)
    {
        directory.setContainerDirectory(this.getDirectoryPath());
        this.directoriesSet.add(directory);
    }

    private void setContainerDirectory(String containerDir)
    {
        this.containerDirectory = containerDir;
        this.dirPath = containerDir + "/" + this.getName();
    }

    public String getName()
    {
        return this.name;
    }

    public String getDirectoryPath()
    {
        if (this.dirPath != null)
        {
            return this.dirPath;
        }
        else
        {
            return this.getName();
        }
    }

    public boolean containsSubdirectories()
    {
        return directoriesSet.isEmpty();
    }

    public void printContents()
    {
        System.out.println("Filesizes in " + this.getDirectoryPath() + ":");
        for (Integer fileSize : fileSizesList)
        {
            System.out.println(fileSize);
        }
        System.out.println("Total: " + this.getCurrentDirectoryByteTotal());
        System.out.println("Directories in " + this.getDirectoryPath() + ":");
        for (Directory directory : directoriesSet)
        {
            System.out.println(directory.getDirectoryPath());
//            directory.printContents();
//            System.out.println("end " + directory.getName() + ";");
        }
        System.out.println("Complete byte total: " + this.getCompleteByteTotal());
    }
}
