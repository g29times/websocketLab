package work.OAS;

import org.junit.Test;

import java.io.File;

/**
 * Created by Excuse on 2016/1/13.
 */
public class FileTest {

    @Test
    public void testFile() {
        File file = new File("src/work/OAS/TechBook.txt");
        System.out.println(file.exists());
    }

    public void deleteFile() {
        File file = new File("src/work/OAS/dest.txt");
        file.delete();
    }

}
