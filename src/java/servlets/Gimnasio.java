
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

/**
 *
 * @author xaviB
 */
public class Gimnasio extends HttpServlet {

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
            out.println("<title>Gimnasio</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>-- Gimnasio --" + request.getContextPath() + "</h1>");
            
            out.println("<form  method=\"GET\">");
            out.println("<input type=\"submit\" name = \"opcion\" value=\"Mejorar Vida\">");
            out.println("<input type=\"submit\" name = \"opcion\" value=\"Conseguir Pociones\">");
            out.println("</form>");
            
            //mejorar vida
            if ("Mejorar Vida".equals(request.getParameter("opcion"))){
                out.println("<br><br> --- Mejorar vida ---");
                
                out.println("<br><br> --- Elige entrenador ---");
                //listando entrenadores
                out.println("<form  method=\"GET\">");
                out.println("</form>");
                
                
                List <Pokemon> lista = miEjb.SellectAllPokemonsOrdered();
                for (Pokemon p : lista){
                    out.println("<p>" + p.getName() + ": Level: " + p.getLevel() + ", life: " + p.getLife() );
                }                
            }
            
            
            
            
            
            
            
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
