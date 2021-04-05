/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.servlets;

import java.io.IOException;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.controller.InputVerifier;
import pl.polsl.entities.CalcHistoryRecord;
import pl.polsl.entities.Calculation;
import pl.polsl.exceptions.RangeException;
import pl.polsl.model.Calculator;
import pl.polsl.model.RectanglesMethodCalculator;
import pl.polsl.model.TrapezoidalMethodCalculator;
import pl.polsl.view.OutputManager;

/**
 *
 * @author Michał Opiełka
 * @version 1.0
 */
@WebServlet(name = "InputManager", urlPatterns = {"/InputManager"})
public class InputManager extends HttpServlet {

    /**
     * Begignning string
     */
    private String begin = "";
    /**
     * End string
     */
    private String end = "";
    /**
     * Function string
     */
    private String function = "";
    /**
     * Integration method string
     */
    private String method = "";
    /**
     * Result
     */
    private Double result = 0.0;

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        begin = request.getParameter("begin");
        end = request.getParameter("end");
        function = request.getParameter("function");
        method = request.getParameter("method");

        InputVerifier verifier = new InputVerifier(begin, end, function, method);
        OutputManager output = new OutputManager(response);

        if (verifier.isEverythingCorrect()) {
            switch (method) {
                case "1": {
                    Calculator calc = new TrapezoidalMethodCalculator(function, Integer.parseInt(begin), Integer.parseInt(end));
                    try {
                        calc.integrate();
                        result = calc.getResult();
                        insertRecordsToBase();
                        output.printResult(result, begin, end, function);
                    } catch (RangeException e) {
                        output.printRangeException(e.getMessage());
                    }
                    break;
                }
                case "2": {
                    Calculator calc = new RectanglesMethodCalculator(function, Integer.parseInt(begin), Integer.parseInt(end));
                    try {
                        calc.integrate();
                        result = calc.getResult();
                        insertRecordsToBase();
                        output.printResult(result, begin, end, function);
                    } catch (RangeException e) {
                        output.printRangeException(e.getMessage());
                    }
                    break;
                }
            }
        } else {
            output.printError();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Writes last calculation to data base
     *
     * @param object object
     */
    private void writeToDB(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pl.polsl_IntegralsCalculatorWebDB_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    /**
     * Inserts calculations results to database
     */
    private void insertRecordsToBase() {
        Calculation calculation = new Calculation();
        calculation.setBeginningValue(Integer.parseInt(begin));
        calculation.setEndValue(Integer.parseInt(end));
        calculation.setMathematicalFunction(function);
        calculation.setResult(result);
        writeToDB(calculation);

        CalcHistoryRecord record = new CalcHistoryRecord();
        record.setCalculation(calculation);
        Date date = new Date();
        date.getTime();
        record.setDate(date);
        writeToDB(record);
    }
}
