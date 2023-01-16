import java.io.File;
import java.util.Date;

/**
 * Для поиска и переноса DICOM файлов в первый каталог.
 */

public class Main {
    static Date date = new Date();
    static long startProgram = date.getTime();

    public static void main(String[] args) throws InterruptedException {
        File path = new File("C:\\Users\\admin\\Desktop\\Path\\");
        getFilesMas(path, String.valueOf(path));
    }

    public static void getFilesMas(File fileList, String path) throws InterruptedException {
        while (true) {
            File[] files = fileList.listFiles();
            for (File file : files) {
                boolean lastModified = file.lastModified() > startProgram;
                if (file.isDirectory() && lastModified) {
                    System.out.println("Directory: " + file.getName());
                    getFilesMas(file, path);
                } else {
                    if (file.getName().endsWith(".dcm") && lastModified) {
                        String[] fileName = String.valueOf(file).split("\\\\");
                        File copyFile = new File(path + "\\" + fileName[5] + "\\" + fileName[5] + ".dcm");
                        boolean sucses = file.renameTo(copyFile);
                        if (sucses) {
                            System.out.println("File is copy in: " + copyFile);

                        } else {
                            System.out.println("Error!");
                        }
                    }
                }
            }
            Thread.sleep(900000);
        }
    }
}