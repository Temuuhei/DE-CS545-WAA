package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/calc")
public class CalculatorServlet extends HttpServlet {
    private final List<List<String>> table = new ArrayList<>();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //show calculator page
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>SIMPLE CALCULATOR<br><br><br></head>");
        out.println("<body>");
        out.println("<form method = 'post' action = 'calc'>");
        out.println("enter the first number:<br>");
        out.println("<input type = 'text' name='number1'><br><br>");
        out.println("enter the second number:<br>");
        out.println("<input type = 'text' name='number2'><br><br>");
        out.println("enter the operation:<br><br>");
        out.println("<input type ='radio' name = 'op' value = '+'>add<br>");
        out.println("<input type = 'radio' name = 'op' value = '-'>sub<br>");
        out.println("<input type = 'radio' name = 'op' value = '*'>mul<br>");
        out.println("<input type = 'radio' name = 'op' value = '/'>div<br><br>");
        out.println("<input type = 'submit' name = 'result' value = 'submit'><br>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //show result page

        Integer num1 = Integer.parseInt(request.getParameter("number1"));
        Integer num2 = Integer.parseInt(request.getParameter("number2"));
        String op =  request.getParameter("op");
        int result = 0;

        switch (op) {
            case "+" : result = num2 + num1;
                break;
            case "-": result = num1 - num2;
                break;
            case "*": result = num1 * num2;
                break;
            case "/": result = num1 / num2;
        }
        List<String> sub1 = new ArrayList<>();
        sub1.add(String.valueOf(num1));
        sub1.add(String.valueOf(num2));
        sub1.add(op);
        sub1.add(String.valueOf(result));
        table.add(sub1);

        System.out.println("Response post :  " + num1 + "  " + num2 + " op " + op);
        System.out.println("table : " + table.toString());
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>SIMPLE CALCULATOR<br><br><br></head>");
        out.println("<body>");
        out.println("<form method = 'post' action = 'calc'>");
        out.println("enter the first number:<br>");
        out.println("<input type = 'text' name='number1'><br><br>");
        out.println("enter the second number:<br>");
        out.println("<input type = 'text' name='number2'><br><br>");
        out.println("enter the operation:<br><br>");
        out.println("<input type ='radio' name = 'op' value = '+'>add<br>");
        out.println("<input type = 'radio' name = 'op' value = '-'>sub<br>");
        out.println("<input type = 'radio' name = 'op' value = '*'>mul<br>");
        out.println("<input type = 'radio' name = 'op' value = '/'>div<br><br>");
        out.println("<input type = 'submit' name = 'result' value = 'submit'><br>");
        out.println("<h1>The result is " + num1 + " "+ op + " " + num2 + " = " + result + "</h1>");
        if (table.size() > 0) {
            out.println("<table border=1>");
            out.println("<tr>");
            out.println("<th>first</th>");
            out.println("<th>operation</th>");
            out.println("<th>second</th>");
            out.println("<th>result</th>");
            out.println("</tr>");
            for (List<String> strings : table) {
                out.println("<tr>");
                out.println("<td style=\"text-align: center; vertical-align: middle;\">" + strings.get(0) + "</td>");
                out.println("<td style=\"text-align: center; vertical-align: middle;\">" + strings.get(2) + "</td>");
                out.println("<td style=\"text-align: center; vertical-align: middle;\">" + strings.get(1) + "</td>");
                out.println("<td style=\"text-align: center; vertical-align: middle;\">" + strings.get(3) + "</td>");
                out.print("</tr>");
            }
            out.println("</table>");
        }
        out.println("</body>");
        out.println("</html>");
        out.flush();
    }

}
