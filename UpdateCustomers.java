

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateEmployee
 */
@WebServlet("/UpdateEmployee")
public class UpdateCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int testnumber=5;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
	  String z=request.getParameter("x");
	   int cx=Integer.parseInt(z);	
	String query="select * from employee_details where id="+cx;
	
	try {
		Class.forName("com.mysql.jdbc.Driver");  
		 Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/milk-collection","root","");  
		Statement st=con.createStatement();
		ResultSet res=st.executeQuery(query);
		if(res.next())
		{
			out.print("<h2>Update employee</h2>");
		out.print("<table>");
		//out.print("<form action='/UpdateServlet' method='post'>");
		out.print("<form action='UpdateServlet' method='Post'>");
		out.print("<tr><td></td><td><input type='hidden' name='id' value="+res.getInt(1)+"></td></tr>");
		out.print("<tr><td>Name</td><td><input type='text' name='name' value="+res.getString(2)+"></td></tr>");
		out.print("<tr><td>Last name</td><td><input type='text' name='lname' value="+res.getString(3)+"></td></tr>");
		out.print("<tr><td>Employee Id</td><td><input type='number' name='nId' value="+res.getInt(4)+"></td></tr>");
		out.print("<tr><td>RSSB</td><td><input type='number' name='rssb' value="+res.getInt(5)+"></td></tr>");
		out.print("<tr><td>Salary:</td><td><input type='number' name='salary' value="+res.getInt(6)+"></td></tr>");
		out.print("<tr><td></td><td><input type='Submit' value='Save update'></td>");
		out.print("</form>");
		out.print("</table>");				
		}
		
		
	}
	catch(Exception e) {out.print(e);}
	}

}
