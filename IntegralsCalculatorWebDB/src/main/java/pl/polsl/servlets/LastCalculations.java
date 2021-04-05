/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.controller.InputVerifier;
import pl.polsl.entities.CalcHistoryRecord;
import pl.polsl.view.OutputManager;

/**
 *
 * @author Michał Opiełka
 * @version 1.0
 */
@WebServlet(name = "LastCalculations", urlPatterns = {"/LastCalculations"})
public class LastCalculations extends HttpServlet {

    /**
     * List for last calculations
     */
    private List<CalcHistoryRecord> recordsList = new ArrayList<>();
    /**
     * Ouptut manager
     */
    OutputManager output;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String rowCount = request.getParameter("rowsCount");
        InputVerifier verifier = new InputVerifier();
        output = new OutputManager(response);
        int rows = 0;
        if (verifier.ifRowCountIsCorrect(rowCount)) {
            rows = Integer.parseInt(rowCount);
        } else {
            output.printError();
        }

        getRecords();
        output.printPreviousCalculations(rows, recordsList);
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
     * Method gets records from database
     */
    private void getRecords() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pl.polsl_IntegralsCalculatorWebDB_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            TypedQuery<CalcHistoryRecord> q = em.createQuery("SELECT c FROM CalcHistoryRecord c", CalcHistoryRecord.class);
            recordsList = q.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
