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
import persistencia.Battle;
import persistencia.BattleDTO;
import persistencia.Pokemon;
import persistencia.Trainer;

/**
 *
 * @author xaviB
 */
public class StukemonServlet extends HttpServlet {
    
    @EJB
    StukemonEJB miEjb;

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
            out.println("<title>Servlet StukemonServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Stukemon at " + request.getContextPath() + "</h1>");
            out.println("<a href=\"AltaPokemon\">Alta Pokemon</a>");
            out.println("<br><a href=\"Batalla\">Batalla!</a>");
            out.println("<br><a href=\"AltaTrainer\">Alta Trainer</a>");
            out.println("<br><a href=\"BajaPokemon\">Baja Pokemon</a>");
            out.println("<br><a href=\"Gimnasio\">Gimnasio</a>");
            
            out.println("<br><br> --- Listas ---");
            out.println("<form  method=\"GET\">");
            out.println("<input type=\"submit\" name = \"lista\" value=\"listaPokemon\">");
            out.println("<input type=\"submit\" name = \"lista\" value=\"rankingTrainers\">");
            out.println("<input type=\"submit\" name = \"lista\" value=\"batallas\">");
            out.println("</form>");
            
            //listas
            //pokemon
            if ("listaPokemon".equals(request.getParameter("lista"))){
                out.println("<br><br> --- listando pokemons ---");
                List <Pokemon> lista = miEjb.SellectAllPokemonsOrdered();
                for (Pokemon p : lista){
                    out.println("<p>" + p.getName() + ": Level: " + p.getLevel() + ", life: " + p.getLife() );
                }                
            }
            
            //trainers
            if ("rankingTrainers".equals(request.getParameter("lista"))){
                out.println("<table border=\"1\" style=\"width:50%\">");
                out.println("<tr>");
                out.println("<th>Name</th>");
                out.println("<th>Points</th>");
                out.println("<th>Pokeballs</th>");
                out.println("<th>Potions</th>");
                out.println("<th>Pokemons</th>");
                out.println("<br><br> --- ranking trainers ---");
                List <Trainer> lista = miEjb.SellectTrainerRanking();
                for (Trainer t : lista){
                    out.println("<tr>");
                    out.println("<th> " + t.getName() + "</th>");
                    out.println("<th> " + t.getPoints()+ "</th>");
                    out.println("<th> " + t.getPokeballs()+ "</th>");
                    out.println("<th> " + t.getPotions()+ "</th>");
                    out.println("<th> " + t.getPokemonCollection().size()+ "</th>");
                }     
                out.println("</table>");
            }
            
            //batallas
            if ("batallas".equals(request.getParameter("lista"))){
                out.println("<br><br> --- Pokemons y batallas ---");
                
                List <BattleDTO> listaBatallas = miEjb.SellectAllBattles();
                
                out.println("<br><br> --- Hay "+listaBatallas.size()+" Pokemons con victorias registradas ---");
                out.println("<table border=\"1\" style=\"width:50%\">");
                out.println("<th>Winner</th>");
                out.println("<th>Victories</th>");
                
                for (BattleDTO p: listaBatallas){                    
                    out.println("<tr>");    
                    out.println("<th> " + p.getName() + "</th>");
                    out.println("<th> " + p.getVictories()+ "</th>");
                    
                }
                out.println("</table>");
                
                
            }
            
            out.println("                    /<br>"+
"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp_,.------....___,.' ',.-.<br>"+
"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ,-'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp_,.--\"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp|<br>"+
"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ,'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp _.-'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp.<br>"+
"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp/&nbsp&nbsp&nbsp ,&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ,'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp `<br>"+
"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp .&nbsp&nbsp&nbsp /&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp /&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ``.<br>"+
"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp |&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp .&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp \\.\\<br>"+
"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ____&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp|___._.&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp __&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp \\ `.<br>"+
"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp .'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp`---\"\"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ``\"-.--\"'`&nbsp&nbsp&nbsp\\&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp .&nbsp&nbsp&nbsp\\<br>"+
"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp.&nbsp&nbsp&nbsp,&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp__&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp `&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp .<br>"+
"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp`,'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ,-\"'&nbsp&nbsp&nbsp.&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp \\&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp |&nbsp&nbsp&nbsp&nbsp&nbsp&nbspL<br>"+
"&nbsp&nbsp&nbsp ,'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp_.'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp-._&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp/&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp|<br>"+
"&nbsp&nbsp&nbsp,`-.&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp,\".&nbsp&nbsp&nbsp `--'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp>.&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp,'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp |<br>"+
" . .'\\'&nbsp&nbsp&nbsp `-'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp __&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp,&nbsp&nbsp&nbsp,-.&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp /&nbsp&nbsp&nbsp`.__.-&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp,'<br>"+
" ||:, .&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ,'&nbsp&nbsp&nbsp;&nbsp&nbsp&nbsp/&nbsp&nbsp&nbsp/ \\ `&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp`.&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp.&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp.'/<br>"+
" j|:D&nbsp&nbsp&nbsp\\&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp`--'&nbsp&nbsp&nbsp' ,'_&nbsp&nbsp&nbsp. .&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp `.__, \\&nbsp&nbsp&nbsp , /<br>"+
"/ L:_&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp .&nbsp&nbsp&nbsp\"' :_;&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp`.'.'<br>"+
".&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp\"\"'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp\"\"\"\"\"'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspV<br>"+
" `.&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp .&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp`.&nbsp&nbsp&nbsp _,..&nbsp&nbsp&nbsp`<br>"+
"&nbsp&nbsp&nbsp `,_&nbsp&nbsp&nbsp .&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp.&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp_,-'/&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp.. `,'&nbsp&nbsp&nbsp __&nbsp&nbsp&nbsp`<br>"+
"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp) \\`._&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp___....----\"'&nbsp&nbsp&nbsp,'&nbsp&nbsp&nbsp .'&nbsp&nbsp&nbsp\\ |&nbsp&nbsp&nbsp '&nbsp&nbsp&nbsp\\&nbsp&nbsp&nbsp.<br>"+
"&nbsp&nbsp&nbsp /&nbsp&nbsp&nbsp `. \"`-.--\"'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp _,' ,'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp `---' |&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp`./&nbsp&nbsp&nbsp|<br>"+
"&nbsp&nbsp&nbsp.&nbsp&nbsp&nbsp _&nbsp&nbsp&nbsp`\"\"'--.._____..--\"&nbsp&nbsp&nbsp ,&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp |<br>"+
"&nbsp&nbsp&nbsp| .\" `. `-.&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp/-.&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp /&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp,<br>"+
"&nbsp&nbsp&nbsp| `._.'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp`,_&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;&nbsp&nbsp&nbsp/&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ,'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp.<br>"+
" .'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp/| `-.&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp. ,'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ,&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ,<br>"+
" '-.__ __ _,','&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'`-..___;-...__&nbsp&nbsp&nbsp ,.'\\ ____.___.'<br>"+
" `\"^--'..'&nbsp&nbsp&nbsp '-`-^-'\"--&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp`-^-'`.''\"\"\"\"\"`.,^.`.--'");
            
            
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
