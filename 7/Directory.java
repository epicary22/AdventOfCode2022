import java.util.*;

public class Directory {
    public ArrayList<Integer> fileSizesList = new ArrayList<>();
    public HashSet<Directory> directoriesSet = new HashSet<>();
    public HashMap<String, Directory> directoriesMap;
    public String name;
    public Directory containerDirectory;
    public String containerDirectoryName;
    public String dirPath;

    public Directory(String name)
    {
        this.name = name;
    }

    public Directory(String name, Directory containerDirectory)
    {
        this.name = name;
        this.containerDirectory = containerDirectory;
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
        return total;
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
        directory.setContainerDirectoryName(this.getDirectoryPath());
        this.directoriesSet.add(directory);
    }

    private void setContainerDirectoryName(String containerDirName)
    {
        this.containerDirectoryName = containerDirName;
        this.dirPath = containerDirName + "/" + this.getName();
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

    public String getContainerDirectoryName()
    {
        return this.containerDirectoryName;
    }

    public Directory getContainerDirectory()
    {
        return this.containerDirectory;
    }

    public HashMap<String, Directory> getDirectoriesMap()
    {
        HashMap<String, Directory> map = new HashMap<>();
        for (Directory dir : this.directoriesSet)
        {
            map.put(dir.getName(), dir);
        }
        return map;
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
        }
        System.out.println("Complete byte total: " + this.getCompleteByteTotal() + "\n");
    }
}
