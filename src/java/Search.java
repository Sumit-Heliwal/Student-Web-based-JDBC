/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sumit
 */
@WebServlet(urlPatterns = {"/Search"})
public class Search extends HttpServlet {
 Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
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
   
            		  Class.forName("oracle.jdbc.driver.OracleDriver");
        con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521","sumit","1234");
        pst=con.prepareStatement("Select * from student where roll=?");
        
      
                              int r=Integer.parseInt(request.getParameter("roll"));
                             pst.setInt(1,r);
                         
                           rs=pst.executeQuery();
                        if(rs.next())
                        {
                            int r0= rs.getInt(1);
                             String n=rs.getString(2);
                             String ad=rs.getString(3);
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Search</title>");            
            out.println("</head>");
           
                       out.println("<body bgcolor='#aa22ff'>");
        out.println("<form name='update' action='http://localhost:8084/StudentJDBC/Update'>");
        out.println("<table align='center' cellpadding='10px'>");
            out.println("<tr><td>Roll</td><td><input type='text' name='roll' value="+r0+" ></td></tr>");
            out.println("<tr><td>Name</td><td><input type='text' name='Name' value="+n+"></td></tr>");
            out.println("<tr><td>Address</td><td><input type='text' name='address' value="+ad+"></td></tr>");
            out.println("<tr><td colspan='2' align='center'></td></tr>");
                  out.println("<tr><td colspan=2 align='center'><input type='Submit' value='Update'></td></tr>");
             out.println("</table>");
         out.println("</form>");
       
        out.println("<form name='home' action='http://localhost:8084/StudentJDBC/HomePage.html'>");
            out.println("<table align='center' cellpadding='10px'>");
                out.println("<tr><td><input type='Submit' value='Home'></td></tr>");
            out.println("</table>");
            out.println("</form>");
            out.println("</body>");
            
            out.println("<body>");
            out.println("<h1 align='Center'>||Search Sucessful|| </h1>");
            out.println("</body>");
                        out.println("</html>");
                        }
     else
                        {out.println("<html>");
            out.println("<head>");
            out.println("<title>Search</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Search Failed</h1>");
            out.println("<a href=http://localhost:8084/StudentJDBC/Search.html>Try Again</a>");
            out.println("</body>");
            out.println("</html>");
                        }
        } catch (SQLException | ClassNotFoundException ex) {
         Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
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
