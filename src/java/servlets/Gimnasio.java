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
public class Gimnasio extends HttpServlet {

    @EJB
    StukemonEJB miEjb;
    private int check = 0;

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

            //------------------------ mejorar vida ------------------------
            if ("Mejorar Vida".equals(request.getParameter("opcion")) || (check == 1)) {
                out.println("<br><br> --- Mejorar vida ---");

                out.println("<br><br> --- Estos entrenadores tienen pokemons y pociones, elige uno  ---");
                //listando entrenadores
                out.println("<form  method=\"GET\">");
                out.println(" <select name=\"trainer\" required>");

                //todos los trainers con pokemons y pociones
                List<Trainer> trainers = miEjb.SellectAllTrainers();

                for (Trainer t : trainers) {
                    if (t.getPotions() > 0 && t.getPokemonCollection().size() > 0) {
                        out.println("<option value=\"" + t.getName() + "\">" + t.getName() + "</option>");
                    }
                }
                check = 1;
                out.println("</select>");
                out.println("<input type=\"submit\" name = \"Pokemons\" value=\"Pokemons\">");
                out.println("</form>");

                //listando pokemons
                if ("Pokemons".equals(request.getParameter("Pokemons"))) {
                    Trainer t = miEjb.getTrainerByName(request.getParameter("trainer"));
                    out.println("<br><br> --- Elige pokemon ---");

                    out.println("<form  method=\"GET\">");

                    out.println(" <select name=\"pokemon\" required>");

                    for (Pokemon p : t.getPokemonCollection()) {
                        out.println("<option value=\"" + p.getName() + "\">" + p.getName() + "</option>");
                    }
                    out.println("</select>");
                    out.println("<input type=\"submit\" name = \"Upgrade\" value=\"Mejorar\">");
                    out.println("</form>");
                    //if (request.getParameter("trainer")).pokemons.size() < 0){
                    //out.println("No tiene pokemons !");
                    //}
                }
                
            //mejorando pokemon
                if ("Mejorar".equals(request.getParameter("Upgrade"))) {
                    Pokemon p = miEjb.getPokemonByName(request.getParameter("pokemon"));
                    miEjb.UpgradePokemon(p);
                    out.println("<br>" + request.getParameter("pokemon") + " ha conseguido 50 vida extra!");
                }
            
            
            
            
            }

            out.println("<br><br><a href=\"StukemonServlet\">Volver</a>");

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
