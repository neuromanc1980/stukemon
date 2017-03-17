/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.StukemonEJB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class Batalla extends HttpServlet {
    
    @EJB 
    StukemonEJB miEjb;
    
    Trainer entrenador1, entrenador2;
    Pokemon pokemon1, pokemon2; 
    boolean fail = true;
    boolean ko = false;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Batalla</title>");  
            
            //listando entrenadores
            
            List<Trainer> trainers = miEjb.SellectAllTrainers();
            List<Pokemon> pokemons1 = null;
            List<Pokemon> pokemons2 = null;
            Pokemon attack1 = null;
            Pokemon attack2 = null;
            
                out.println(" -- Entrenador 1 --"); 
                
                out.println("<form  method=\"GET\">");
                out.println(" <select name=\"trainer1\" required>");
                                 
                for (Trainer t : trainers) { 
                        if (t.getPokemonCollection().size()>0)
                        {
                        out.println("<option value=\"" + t.getName() + "\">" + t.getName() + "</option>");
                        }
                }                
                out.println("</select>");
                                
                out.println("<br> -- Entrenador 2 --<br>");                 
                
                out.println("<form  method=\"GET\">");
                out.println(" <select name=\"trainer2\" required>");
                 
                
                for (Trainer t : trainers) { 
                        if (t.getPokemonCollection().size()>0)
                        {
                        out.println("<option value=\"" + t.getName() + "\">" + t.getName() + "</option>");
                        }
                }
                
                out.println("</select>");
                out.println("<br><br><input type=\"submit\" name = \"entrenador\" value=\"Elige\">");
                out.println("</form>");
            
            if ("Elige".equals(request.getParameter("entrenador"))) {
                    
                    entrenador1 = miEjb.getTrainerByName(request.getParameter("trainer1"));
                    entrenador2 = miEjb.getTrainerByName(request.getParameter("trainer2"));
                    
                                        
                    if (entrenador1.getName() == entrenador2.getName()){
                        out.println("<br>Elige dos entrenadores diferentes!");
                        fail = true;
                    }
                    
                    else fail = false;
                   
                }
            
            if (fail == false){

                out.println("<br> -- Pokemon 1 --"); 
                
                
                
                out.println("<form  method=\"GET\">");
                out.println(" <select name=\"pokemon1\" required>");
                                 
                for (Pokemon p : entrenador1.getPokemonCollection()) {                    
                        
                    out.println("<option value=\"" + p.getName() + "\">" + p.getName() + "</option>");
                }                
                out.println("</select>");
                
                out.println("<br> -- Pokemon 2 --"); 
                
                out.println("<form  method=\"GET\">");
                out.println(" <br><select name=\"pokemon2\" required>");
                                 
                for (Pokemon p : entrenador2.getPokemonCollection()) {                    
                        
                    out.println("<option value=\"" + p.getName() + "\">" + p.getName() + "</option>");
                }                
                out.println("</select>");
                
                out.println("</select>");
                out.println("<br><br><input type=\"submit\" name = \"luchar\" value=\"Que lluevan las ostias!\">");
                out.println("</form>");
            }
            
            
            if ("Que lluevan las ostias!".equals(request.getParameter("luchar"))) {
                
                pokemon1 = miEjb.getPokemonByName(request.getParameter("pokemon1"));
                pokemon2 = miEjb.getPokemonByName(request.getParameter("pokemon2"));
                
                //comprobar velocidad
                if (pokemon1.getSpeed() == pokemon2.getSpeed()){
                    int tmp = (int)(Math.random()*1)+1;
                    if (tmp == 1)   pokemon1.setSpeed(pokemon1.getSpeed()+1);
                    if (tmp == 2)   pokemon2.setSpeed(pokemon2.getSpeed()+1);
                }
                
                if (pokemon1.getSpeed() > pokemon2.getSpeed()){
                    
                     attack1 = pokemon1;
                     attack2 = pokemon2;                    
                }   else {
                     attack2 = pokemon1;
                     attack1 = pokemon2; 
                }
                
                //pokemon1 ataca
                    int daño = attack1.getAttack() + 2*attack1.getLevel() 
                            - attack2.getDefense()/3; 
                    attack2.setLife(attack2.getLife()-daño);
                    
                    out.println("<br>"+attack1.getName() + " ataca y hace "+daño+" puntos de daño, dejando a "+attack2.getName()
                    +" con "+ attack2.getLife() + " puntos de vida!");
                    
                    if (attack2.getLife() <= 0){
                        out.println("<br>"+attack2.getName() + " aqueda inconsciente! <br>"+attack1.getName()
                    +" gana!");
                        ko = true;
                    }   else    {
                        //pokemon2 ataca
                        daño = attack2.getAttack() + 2*attack2.getLevel() 
                            - attack1.getDefense()/3; 
                        attack1.setLife(attack1.getLife()-daño);
                        out.println("<br>"+attack2.getName() + " ataca y hace "+daño+" puntos de daño, dejando a "+attack1.getName()
                    +" con "+ attack1.getLife() + " puntos de vida!");
                        
                        
                        if (attack1.getLife() <= 0){
                        out.println("<br>"+attack1.getName() + " aqueda inconsciente! <br>"+attack2.getName()
                    +" gana!");  
                        ko = true;
                    }
                                          
                }
                    
            if (ko == false){
                
                if (attack1.getLife() > attack2.getLife()){
                             
                    out.println("<br><br>"+attack1.getName() + " Gana por puntos!!!!!!! <br>");
                    
                    out.println("<br>"+attack1.getName() + " tiene " + attack1.getLife() + " puntos de vida restantes <br>");
                    out.println("<br>"+attack2.getName() + " tiene " + attack2.getLife() + " puntos de vida restantes <br>");
                    
                }   else {
                    
                    out.println("<br><br>"+attack2.getName() + " Gana por puntos!!!!!!!! <br>");
                    out.println("<br>"+attack2.getName() + " tiene " + attack2.getLife() + " puntos de vida restantes <br>");
                    out.println("<br>"+attack1.getName() + " tiene " + attack1.getLife() + " puntos de vida restantes <br>");
                }
                
            }       
                
                
            }
            
            out.println("              _.--\"\"`-..<br>" +
                        "            ,'          `.<br>" +
                        "          ,'          __  `.<br>" +
                        "         /|          \" __   \\<br>" +
                        "        , |           / |.   .<br>" +
                        "        |,'          !_.'|   |<br>" +
                        "      ,'             '   |   |<br>" +
                        "     /              |`--'|   |<br>" +
                        "    |                `---'   |<br>" +
                        "     .   ,                   |                       ,\".<br>" +
                        "      ._     '           _'  |                    , ' \\ `<br>" +
                        "  `.. `.`-...___,...---\"\"    |       __,.        ,`\"   L,|<br>" +
                        "  |, `- .`._        _,-,.'   .  __.-'-. /        .   ,    \\<br>" +
                        "-:..     `. `-..--_.,.<       `\"      / `.        `-/ |   .<br>" +
                        "  `,         \"\"\"\"'     `.              ,'         |   |  ',,<br>" +
                        "    `.      '            '            /          '    |'. |/<br>" +
                        "      `.   |              \\       _,-'           |       ''<br>" +
                        "        `._'               \\   '\"\\                .      |<br>" +
                        "           |                '     \\                `._  ,'<br>" +
                        "           |                 '     \\                 .'|<br>" +
                        "           |                 .      \\                | |<br>" +
                        "           |                 |       L              ,' |<br>" +
                        "           `                 |       |             /   '<br>" +
                        "            \\                |       |           ,'   /<br>" +
                        "          ,' \\               |  _.._ ,-..___,..-'    ,'<br>" +
                        "         /     .             .      `!             ,j'<br>" +
                        "        /       `.          /        .           .'/<br>" +
                        "       .          `.       /         |        _.'.'<br>" +
                        "        `.          7`'---'          |------\"'_.'<br>" +
                        "       _,.`,_     _'                ,''-----\"'<br>" +
                        "   _,-_    '       `.     .'      ,\\<br>" +
                        "   -\" /`.         _,'     | _  _  _.|<br>" +
                        "    \"\"--'---\"\"\"\"\"'        `' '! |! /<br>" +
                        "                            `\" \" -' ");

            
            
            
            
            
            
            out.println("<br><br><br><a href=\"StukemonServlet\">Volver</a>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Batalla at " + request.getContextPath() + "</h1>");
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
