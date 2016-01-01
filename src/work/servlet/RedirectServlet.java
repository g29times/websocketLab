package work.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Mono on 2015/12/19.
 */
public class RedirectServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        downloadFile(request, response,
                RedirectServlet.class.getResource("../download.txt").getPath());
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        downloadFile(request, response,
                RedirectServlet.class.getResource("../download.txt").getPath());
    }

    public static void main(String[] args) {
        InputStream inStream = RedirectServlet.class.getResourceAsStream("download.txt");

        String filePath = RedirectServlet.class.getResource("../download.txt").getPath();
        System.out.println(filePath);
        File file = new File(filePath);
        Reader reader = null;
        try {
            char[] cbuff = new char[30];

            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(file));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(cbuff)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == cbuff.length)
                        && (cbuff[cbuff.length - 1] != '\r')) {
                    System.out.print(cbuff);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (cbuff[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(cbuff[i]);
                        }
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    private void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileToDownload) throws IOException {
        final int BYTES = 1024;
        int length = 0;

        ServletOutputStream outStream = response.getOutputStream();
        ServletContext context = getServletConfig().getServletContext();

        String mimeType = context.getMimeType(fileToDownload);
        response.setContentType(mimeType != null ? mimeType : "text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileToDownload + "\"");

        InputStream in = RedirectServlet.class.getResourceAsStream("download.txt");
        // context.getResourceAsStream("/" + fileToDownload);

        byte[] bbuf = new byte[BYTES];

        while ((in != null) && ((length = in.read(bbuf)) != -1)) {
            outStream.write(bbuf, 0, length);
        }

        outStream.flush();
        outStream.close();
    }
}
