package work.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Excuse on 2015/12/21.
 */
public class PageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        if(req.getParameter("query") != null) {
            resp.setContentType("text/plain;charset=UTF-8");
            // 特别注意此处 print
            out.print(session.getAttribute("area"));
        } else if(req.getParameter("getPlugin")!=null) {
//            String getPlugin = req.getParameter("getPlugin");
            Object plugins = session.getAttribute("plugin");
            resp.setContentType("application/json;charset=UTF-8");
            out.print(plugins);
        } else { // contentManage
            resp.setContentType("application/json;charset=UTF-8");
            StringBuffer respStr = new StringBuffer();
            respStr.append("{");
            respStr.append("\"title\":\"").append(session.getAttribute("title")).append("\",");
            respStr.append("\"body\":\"").append(session.getAttribute("body")).append("\",");
            respStr.append("\"info\":\"").append(session.getAttribute("info")).append("\",");
            respStr.append("\"method\":\"").append(session.getAttribute("method"));
            respStr.append("\"}");

//            System.out.println("-----------------");
//            String s="{\"content\":7, \"method\":8}";
//            Object obj = JSONValue.parse(s);
//            System.out.println(obj);
//            System.out.println(respStr.toString());
//            System.out.println(JSONValue.parse(respStr.toString()));
//            System.out.println("-----------------");

            out.print(JSONValue.parse(respStr.toString()));
//            out.println(JSONValue.parse("{\"title\":123,\"body\":456,\"info\":0}"));
//            out.println("{\"title\":123,\"body\":456}");

//            out.println(session.getAttribute("content"));
//            out.println(session.getAttribute("method"));
        }
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // 通用模块 插件
        String plugin = req.getParameter("setPlugin");
        if(plugin!=null)
            session.setAttribute("plugin", plugin);

        //
        String title = req.getParameter("title");
        String body = req.getParameter("body");
        String info = req.getParameter("info");
        String area = req.getParameter("area");
        String method = req.getParameter("method");

        /**
         * 文章处理逻辑
         * 1 后端前台编辑页面提供文章的标题、内容
         * 2 传给servlet
         * 3 保存到数据库
         * 4 寻找前端的展示页面并携带参数跳转
         * 5 以后打开前端页面时 仅作为模板 以id区分内容的传送 通过ajax控制内容展现
         * 6 数据传输格式 建议JSON
         */

        // text/html
//        resp.setContentType("text/plain;charset=UTF-8");
//        // 模拟数据库获取数据
//        PrintWriter writer = resp.getWriter();
//        try {
//            if(content!=null && "1".equals(content)) {
//                writer.println("article 1");
//            } else if(content!=null && "2".equals(content)) {
//                writer.println("article 2");
//            } else
//                writer.println(content);
//        } finally {
//            writer.close();
//        }

        session.setAttribute("title", title);
        session.setAttribute("body", body);
        session.setAttribute("info", info);
        session.setAttribute("area", area);
        session.setAttribute("method", method);

        // 演示重定向 注意地址 302
//        resp.sendRedirect("/6th_WS/html/single.html");
//        System.out.println(getServletConfig().getServletContext() == getServletContext());

        // 演示转发 注意地址 200
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/html/single.html");
//        dispatcher.forward(req, resp);
    }

    /**
     * Simple JSON
     *  http://tntxia.iteye.com/blog/1887621
     * @param args
     */
    public static void main(String[] args) {
    }
}
