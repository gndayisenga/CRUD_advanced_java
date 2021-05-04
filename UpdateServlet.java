import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.swing.JOptionPane;

import java.sql.*;
/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	public int paydue=0;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out =response.getWriter();
		response.setContentType("text/html");
		String update_id=request.getParameter("id");
		String update_name=request.getParameter("name");
		String update_lname=request.getParameter("lname");
		String update_nid=request.getParameter("nId");
		String update_rssb=request.getParameter("rssb");
		String update_salary=request.getParameter("salary");
		//String update_x4=request.getParameter("x4");
		
		// conversion
		int cid=Integer.parseInt(update_id);
		int cupdate_nid=Integer.parseInt(update_nid);
		int cupdate_rssb=Integer.parseInt(update_rssb);
		int cupdate_salary=Integer.parseInt(update_salary);
		
		if(cupdate_salary<=300000)
		{
			paydue= cupdate_salary+30000;
			//request.getRequestDispatcher("EditForm.html").include(request, response);
		}
		else if( cupdate_salary>300000)
		{
			paydue= Math.round(2*(cupdate_salary-30000));
		}	
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn =DriverManager.getConnection("JDBC:mysql://localhost:3306/milk-collection","root","");
			PreparedStatement ps=conn.prepareStatement("update employee_details set name=?,LastName=?,NationId=?,Rssb=?,salary=?,payDue=? where id=?");
			ps.setString(1, update_name);
			ps.setString(2, update_lname);
			ps.setInt(3, cupdate_nid);
			ps.setInt(4, cupdate_rssb);
			ps.setInt(5, cupdate_salary);
			ps.setInt(6, paydue);
			ps.setInt(7, cid);
			int status =ps.executeUpdate();
			if(status>0)
			{
				//JOptionPane.showMessageDialog(null, "Data Updated succesffully");
				out.print("<p>updated</p><br>");
				//out.print("<a href='FetchCustomers'>Back to update list</a>");
				request.getRequestDispatcher("Employee.html").include(request, response);
			}
			else
			{
				//JOptionPane.showMessageDialog(null, "Not updated ");
				//out.print("not updated");
			}
			conn.close();
		}
		catch(Exception e) {out.print(e);}
		
	}

}
