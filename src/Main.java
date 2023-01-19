import java.io.File;
import java.nio.file.Files;
import java.util.Date;

/**
 * Для поиска и переноса DICOM файлов в первый каталог.
 */

public class Main {

    static File path = new File("D:\\flura");

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start script transcend file.");
        while (true) {
            getFilesMas();
            Thread.sleep(60000);
        }
    }

    public static void getFilesMas(){
        try{
            if (!path.exists()) {
                path.mkdir();
            }
            File[] files = path.listFiles();
            for (File file : files) {
                String[] fileName = String.valueOf(file).split("\\\\");
                File[] checkFileInDir = file.listFiles();
                if (checkFileInDir != null && checkFileInDir.length > 1) {
                    for (File check : checkFileInDir) {
                        if (check.isFile()) {
                            System.out.println("Patient: " + fileName[2] + " ready for copy");
                        }
                    }
                } else {
                    getFile(file);
                }
            }
        } catch (Exception e){
            System.out.println("Path is not exist! Maybe you delete it!");
        }
    }

    public static void getFile(File fileList) throws NullPointerException {
        File[] files = fileList.listFiles();
        try {
            for (File file : files) {
                String[] fileName = String.valueOf(file).split("\\\\");
                if (file.isDirectory()) {
                    System.out.println("Check directory: " + file);
                    getFile(file);
                } else {
                    if (fileName[3].equals(file.getName())) {
                        continue;
                    }
                    if (file.getName().endsWith(".dcm")) {
                        File copyFile = new File(path + "\\" + fileName[2] + "\\" + fileName[2] + ".dcm");
                        boolean sucses = file.renameTo(copyFile);
                        if (sucses) {
                            System.out.println("Patient: " + fileName[2] + " ready for copy in server");
                            System.out.println("File is copy in: " + copyFile);
                        } else {
                            System.out.println("Error!");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}