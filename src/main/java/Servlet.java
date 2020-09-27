import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class Servlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String richestUser = null;
        Integer sumOfAllAccounts = null;
        try {
            sumOfAllAccounts = AccountDao.returningSumOfAllAccounts();
            richestUser = UserDao.returningRichestUser();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (request.getParameter("button1") != null) {
            request.setAttribute("user", richestUser);
        } else if (request.getParameter("button2") != null) {
            request.setAttribute("sum", sumOfAllAccounts);
        }

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}