import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;

public class Main {
    static List<String> pathList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        GameProgress gameProgress1 = new GameProgress(100, 100, 80, 5000);
        GameProgress gameProgress2 = new GameProgress(10, 100, 7, 500);
        GameProgress gameProgress3 = new GameProgress(50, 30, 17, 3000);

        String path = "D://IDEA/Games/savegames";

        saveGame("D://IDEA/Games/savegames/save1.dat", gameProgress1);
        saveGame("D://IDEA/Games/savegames/save2.dat", gameProgress2);
        saveGame("D://IDEA/Games/savegames/save3.dat", gameProgress3);
        pathList.add("D://IDEA/Games/savegames/save1.dat");
        pathList.add("D://IDEA/Games/savegames/save2.dat");
        pathList.add("D://IDEA/Games/savegames/save3.dat");
        zipFiles("D://IDEA/Games/savegames/zip.zip", pathList);

        for (String str : pathList) {
            deleteFiles(str);
        }
    }

    public static void saveGame(String filePath, GameProgress gameProgress) {
        try (ObjectOutputStream oOS = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oOS.writeObject(gameProgress);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void zipFiles(String zipPath, List<String> list) throws IOException {
        try (ZipOutputStream zOS = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (String file : list) {

                try (FileInputStream f = new FileInputStream(zipPath)) {
                    ZipEntry entry = new ZipEntry(zipPath);
                    zOS.putNextEntry(entry);
                    byte[] buffer = new byte[f.available()];
                    f.read(buffer);
                    zOS.write(buffer);
                    zOS.closeEntry();

                } catch (IOException ee) {
                    System.out.println(ee.getMessage());
                }
            }
        }
    }

    public static void deleteFiles(String Filepath) {
        File game = new File(Filepath);
        if (game.delete())
            System.out.println("Can be deleted");
        else
            System.out.println("Can't be deleted");
    }
}

