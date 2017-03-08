/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.StukemonEJB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.Pokemon;
import persistencia.Trainer;

/**
 *
 * @author xaviB
 */
public class BajaPokemon extends HttpServlet {
    
    @EJB
    StukemonEJB miEjb;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BajaPokemon</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>---  Baja Pokemon ---</h1>");
            
            out.println("<form> <br>");
            
            out.println("Qual soltamos en el bosque?:");
            
            out.println(" <select name=\"pokemon\" required>");

            //hacemos lista de pokemons
            List <Pokemon> lista = miEjb.SellectAllPokemons();

            for (Pokemon p : lista) {                
                    out.println("<option value=\""+ p.getName() +"\">"+p.getName()+"</option>");
                }

            out.println("</select>");
            
            //submit
            out.println("<input type=\"submit\" name = \"baja\" value=\"baja\">");
            out.println("</form> <br>");
            
            // Miramos si han pulsado el submit
            if ("baja".equals(request.getParameter("baja"))) {
                //borramos
                miEjb.bajaPokemon(request.getParameter("pokemon"));
                    out.println("Corre en libertad, "+request.getParameter("pokemon"));
                
                } 
            
            out.println("<br><a href=\"StukemonServlet\">Volver</a>");
            
            
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
    }

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
        processRequest(request, response);
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

}
