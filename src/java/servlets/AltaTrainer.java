
package servlets;

import beans.StukemonEJB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.Trainer;

/**
 *
 * @author xaviB
 */
public class AltaTrainer extends HttpServlet {
    
    @EJB StukemonEJB miEjb;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>-- Alta de entrenadores --</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>-- Alta de entrenadores -- " + request.getContextPath() + "</h1>");
            
            //recogemos las variables del formulario
            String name = request.getParameter("name");
            int pokeballs = Integer.parseInt(request.getParameter("pokeballs"));
            int potions = Integer.parseInt(request.getParameter("potions"));
            int points = 0;
//           out.println(name+" "+pokeballs+ " "+potions);
            Trainer t = new Trainer(name, pokeballs, potions, points);
            //if de si ya existe
            if(miEjb.insertarTrainer(t)){
                out.println("<p>Dado de alta!</p>");                
            }   else    {
                out.println("<p>Ya existía!</p>");  
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
