

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import java.sql.*;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteCustomers extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		 String z=request.getParameter("x");
		 
		 // conversion
		 int cid=Integer.parseInt(z);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/milk-collection","root","");
			PreparedStatement ps=con.prepareStatement("delete from employee_details where id="+cid);
			
			
			int input = JOptionPane.showConfirmDialog(null, "Waning:Are you sure to delete the user?");
			
			if(input==0)
			{
				int count=ps.executeUpdate();
				if(count>0)
				{
				out.print("<p style='backgroundcolor:green;'>User deleted succesfully</p>");
				request.getRequestDispatcher( "FetchCustomers").include(request, response);
			}
				}
			else if(input==1)
			{
				request.getRequestDispatcher( "FetchCustomers").include(request, response);
				
			}
			
			
		}
		catch(Exception e) {out.print(e);}
	}

}
