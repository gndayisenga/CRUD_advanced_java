
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FetchCustomers
 */
@WebServlet("/FetchCustomers")
public class FetchCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Employee e;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
	out.print("<head><style>table, th, td {  border: 1px solid black;}table { width: 60%;}</style></head>");
		String sql="select*from employee_details";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			 Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/milk-collection","root","");  
			Statement st=con.createStatement();
			ResultSet res=st.executeQuery(sql);
			out.print("<table>");
			out.print("<tr ><th >First name</th><th>Last name</th><th>ID</th><th>RSSB Number</th><th>Salary</th><th>Paydue</th><th>Update user</th><th>Delete User</th></tr>");
			while(res.next())
			{
				out.print("<tr><td>"+res.getString(2)+"</td><td> "+res.getString(3)+"</td><td>   "+res.getInt(4)+"</td><td>"+res.getInt(5)+" </td><td> "+res.getInt(6)+"</td><td>"+res.getInt(7)+"</td><td><a href='UpdateEmployee?x="+res.getInt(1)+"'>Update</a></td><td><a href='DeleteServlet?x="+res.getInt(1)+"'>delete</a></td></tr>");
			}
			out.print("</table>");
	
			
		}
		catch(Exception e) {}
	}
}
