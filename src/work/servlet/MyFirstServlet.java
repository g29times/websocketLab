package work.servlet;

import listeners.ContextListener;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionListener;

@WebServlet(name = "MyFirstServlet", urlPatterns = {"/MyFirstServlet"})
public class MyFirstServlet extends HttpServlet {

    private static final long serialVersionUID = -1915463532411657451L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException
    {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean success = validateUser(username, password);

        try {
            // Write some content
            out.print("<html>");
            out.print("<head>");
            out.print("<title>LoginServlet</title>");
            out.print("</head>");
            out.print("<body>");

            if(success) {
                out.print("<h2>welcome</h2>");
                out.print("<a href="
                        + getServletConfig().getInitParameter("redirectURL")
                        + ">welcome</a>");
            } else {
                out.print("<h2>please check your password</h2>");
            }

            out.print("</body>");
            out.print("</html>");
        } finally {
            out.close();
        }
    }

    private boolean validateUser(String username, String password) {
        return true;
    }

    //This method will access some external system as database to get user name, and his personalized message
    private Map<String, String> getData()
    {
        Map<String, String> data = new HashMap<String, String>();
        data.put("username", "Guest");
        data.put("message", "Welcome to my world !!");
        return data;
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // 转发请求
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/NextServlet");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        // 读取xml配置文件
        System.out.println(getServletConfig());
        System.out.println(getServletConfig().getServletContext());
        return "MyFirstServlet";
    }

    // TODO 监听 Cookie downloadFile
}
