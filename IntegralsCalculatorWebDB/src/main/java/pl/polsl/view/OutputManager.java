/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.entities.CalcHistoryRecord;

/**
 *
 * @author Michał Opiełka
 * @version 1.0
 */
public class OutputManager {

    /**
     * Response
     */
    HttpServletResponse response;
    /**
     * Writer
     */
    PrintWriter out;

    /**
     * Constructor
     *
     * @param response server response
     * @throws IOException IOException
     */
    public OutputManager(HttpServletResponse response) throws IOException {
        this.response = response;
        out = response.getWriter();
    }

    /**
     * Prints result of integration
     *
     * @param result result of the integration
     * @param begin beginning of the integration
     * @param end end of the integration
     * @param function integrated function
     */
    public void printResult(double result, String begin, String end, String function) {
        DecimalFormat df = new DecimalFormat("###.####");
        out.println("<html>\n"
                + "<body>\n"
                + "<h3>Result of integration: </h3>"
                + "Function: " + function + "<br>"
                + "From: " + begin + "<br>"
                + "To: " + end + "<br>"
                + "Result: " + df.format(result) + "<br>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Prints error
     */
    public void printError() {
        out.println("<html>\n"
                + "<body>\n"
                + "<h1>You've entered a null value or data is incorrect</h1>"
                + "\n");
    }

    /**
     * Prints exception message
     *
     * @param message exception message
     */
    public void printRangeException(String message) {
        out.println("<html>\n"
                + "<body>\n"
                + "<h1>Exception</h1>"
                + "<h2>" + message + "</h2>"
                + "\n");
    }

    /**
     * Prints previous calculations
     *
     * @param rows number of rows to print
     * @param recordsList list of records to print
     */
    public void printPreviousCalculations(int rows, List<CalcHistoryRecord> recordsList) {
        out.println("<html>\n"
                + "<body>\n"
                + "<h3>Last calculations</h3>"
                + "<table border=\"1px solid black\">\n"
                + "<tr>\n"
                + "<th>Begin</th>\n"
                + "<th>End</th>\n"
                + "<th>Function</th>\n"
                + "<th>Result</th>\n"
                + "<th>Date</th>\n"
                + "</tr>\n");
        int i = 0;

        DecimalFormat df = new DecimalFormat("###.####");
        for (CalcHistoryRecord record : recordsList) {
            if (i < rows) {
                out.println("<tr>\n");
                out.println("<td>" + record.getCalculation().getBeginningValue() + "</td>\n");
                out.println("<td>" + record.getCalculation().getEndValue() + "</td>\n");
                out.println("<td>" + record.getCalculation().getMathematicalFunction() + "</td>\n");
                out.println("<td>" + df.format(record.getCalculation().getResult()) + "</td>\n");
                Date date = record.getDate();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String formatedDate = formatter.format(date);
                out.println("<td>" + formatedDate + "</td>\n");
                out.println("</tr>\n");
                i++;
            } else {
                break;
            }
        }
        out.println("</table>\n");
        out.println("</body>\n");
        out.println("</html>\n");
    }
}
