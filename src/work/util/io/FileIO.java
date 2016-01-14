package work.util.io;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Mono on 2016/1/1.
 */
public class FileIO {

    public String readInChar(File file) {
        String result = "";
        try{
            StringBuilder str = new StringBuilder();
            FileReader reader = new FileReader(file); // FileDescriptor
            char[] buffer = new char[1024];
            while(reader.read(buffer)>0){ // StreamDecoder.read
                str.append(buffer);
            }
            result = str.toString();
            System.out.println(result);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    @Test
    public void testReadInChar() {
//        File file = new File("src/work/util/io/test.txt");
        File file = new File(this.getClass().getResource("/work/util/io/test.txt").getFile());
        if(file.exists())
            readInChar(file);
    }
}
