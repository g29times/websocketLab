/**
 * Copyright (c) 2014 Alibaba Cloud Computing
 */
package work.OAS;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 
 * @author jialan
 * @version $Id: DemoUtils.java, v 0.1 2015-5-12 上午11:28:15 jialan Exp $
 */
public class DemoUtils {

    @SuppressWarnings("resource")
    public static void writeFile(File file, long size) {
        Random random = new Random();
        final int chunkSize = 1024 * 1024;
        byte[] buffer = new byte[chunkSize];

        try {
            file.createNewFile();
            OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
            while (size > 0) {
                random.nextBytes(buffer);
                os.write(buffer, 0, (int) (size < chunkSize ? size : chunkSize));
                size -= chunkSize;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void prepareFile(File file, long sizeInMB) {
        if (!file.exists()) {
            writeFile(file, sizeInMB * 1024 * 1024);
        }
    }

}
