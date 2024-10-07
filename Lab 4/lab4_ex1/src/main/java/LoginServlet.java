


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final Map<String, String> users = new HashMap<String, String>();

    @Override
    public void init() throws ServletException {
        users.put("user","111");
        users.put("user1","111");
        users.put("user2","111");

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("index.jsp");
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username =request.getParameter("username");
        String password =request.getParameter("password");
        out.println("<title>Login Result</title>");

        if(users.containsKey(username) && users.get(username).equals(password)){
            out.println("<h2>Login success</h2>");
        }else {
            out.println("<h2>Login false</h2>");
        }


    }
}
