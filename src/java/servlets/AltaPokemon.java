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
 * @author xaviB alka de potemons
 */
public class AltaPokemon extends HttpServlet {

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
            out.println("<title>-- Alta de Pokemon -- </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>-- Alta de Pokemon -- " + request.getContextPath() + "</h1>");

            out.println("<form  method=\"POST\">");
            out.println("Name: <input type=\"text\" name=\"name\" required>");
            out.println("Type:");
            out.println(" <select name=\"type\" required>");
            out.println("        <option value=\"fuego\">fuego</option>");
            out.println("        <option value=\"agua\">agua</option>");
            out.println("        <option value=\"planta\">planta</option>");
            out.println("        <option value=\"electrico\">electrico</option>");
            out.println("</select>");
            out.println("Ability <input type=\"text\" name=\"ability\" required>");
            out.println("<br>");
            out.println("Attack <input type=\"number\" name=\"attack\" required>");
            out.println("Defense <input type=\"number\" name=\"defense\" required>");
            out.println("<br>");
            out.println("Speed <input type=\"number\" name=\"speed\" required>");
            out.println("Life <input type=\"number\" name=\"life\" required>");
            out.println("Level: 0 (noob)");
            out.println("<br>");

            out.println("Select trainer:");
            //seleccionar trainer
            out.println(" <select name=\"trainer\" required>");

            //todos los trainers
            List<Trainer> trainers = miEjb.SellectAllTrainers();

            for (Trainer t : trainers) {
                if (t.getPokemonCollection().size() < 6) {
                    out.println("<option value=\""+ t.getName() +"\">"+t.getName()+"</option>");
                }
            }

            out.println("</select>");

            //submit
            out.println("<input type=\"submit\" name = \"alta\" value=\"alta\">");
            out.println("</form> <br>");

            // Miramos si han pulsado el botón
            if ("alta".equals(request.getParameter("alta"))) {
                //out.println("entra");
                //control de salida
                List<Pokemon> listaPokemons = miEjb.SellectAllPokemons();
                boolean existe = false;
                String nombre = request.getParameter("name");
                out.println(nombre);
                for (Pokemon p : listaPokemons) {
                    if (p.getName().equals(nombre)) {
                        existe = true;   //ya existe
                    }
                }

                //insertamos si no existía, construimos pokemon e insertamos
                //ublic Pokemon(String name, String type, String ability, int attack, int defense, int speed, int life, int level) 
                String name = request.getParameter("name");
                String type = request.getParameter("type");
                String ability = request.getParameter("ability");
                int attack = Integer.parseInt(request.getParameter("attack"));
                int defense = Integer.parseInt(request.getParameter("defense"));
                int speed = Integer.parseInt(request.getParameter("speed"));
                int life = Integer.parseInt(request.getParameter("life"));

                Pokemon p = new Pokemon(name, type, ability, attack, defense, speed, life, 0);
                
                // traerte el entrenador por nombre (el nombre lo recoges de parametro)
                Trainer t = miEjb.getTrainerByName(request.getParameter("trainer"));
                out.println(t);
                if (!existe) {
                    p.setTrainer(t);//añadimos el trainer
                    miEjb.insertarPokemon(p);   //insertamos 
                    out.println("<br>Nuevo pokemon registrado!"); 
                }   else out.println("<br>Ya existía!");    // no insertamos

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
