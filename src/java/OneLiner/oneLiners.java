package OneLiner;

import Utilities.LinerUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "oneLiners", urlPatterns = {"/oneLiners"})
public class oneLiners extends HttpServlet {

    ArrayList<String> oneLiners = new ArrayList();
    int linerCounter = 0;
    
    @Override
    public void init() throws ServletException {
        
        // Init routine to get file path and create utilities class
        
        ServletContext sc = getServletContext();
        String path = sc.getRealPath("/WEB-INF");
        path += System.getProperty("file.separator") + "lines.txt";
        LinerUtils lu = new LinerUtils(path);
        oneLiners = lu.getLines();
        
    }
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
            out.println("<title>Servlet oneLiners</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + oneLiners.get(linerCounter++) + "</h1>");
            out.println("<form method='post' action=oneLiners><input type='submit' value='Next Line'>");
            out.println("</body>");
            out.println("</html>");
            
            
        }
        
        // Reset line counter to 0 if it's greater than the size of the array list holding the text lines
        if (linerCounter  >= oneLiners.size())
            linerCounter=0;
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
