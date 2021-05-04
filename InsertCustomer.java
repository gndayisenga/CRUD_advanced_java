import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class InsertCustomer
 */
@WebServlet("/InsertCustomer1")
public class InsertCustomer extends HttpServlet {
	private int paydue=0;
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//out.print("connected");
		
		String name=request.getParameter("name");
		String lname=request.getParameter("lname");
		String empid=request.getParameter("empid");
		String rssb=request.getParameter("rssb");
		String salary=request.getParameter("salary");
		if(!(name.isEmpty()||lname.isEmpty()||empid.isEmpty()||rssb.isEmpty()||salary.isEmpty()))
				{		
		 int paydue=0;
		// conversion
		int cempid=Integer.parseInt(empid);
		int crssb=Integer.parseInt(rssb);
		int csalary=Integer.parseInt(salary);
		//int paydue = 0;
		if(csalary<=300000)
		{
			paydue= csalary+30000;
			//request.getRequestDispatcher("EditForm.html").include(request, response);
		}
		else if( csalary>300000)
		{
			paydue= Math.round(2*(csalary-30000));
		}
		
	//Connection con=null;
		
	try
	{
		Class.forName("com.mysql.jdbc.Driver"); 
		Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/milk-collection ","root",""); 
		PreparedStatement statement=con.prepareStatement(" insert into employee_details(name,LastName,NationId,Rssb,salary,	payDue)values(?,?,?,?,?,?)");  
		statement.setString(1, name);
		statement.setString(2, lname);
		statement.setInt(3,cempid);
		statement.setInt(4,crssb);
		statement.setInt(5,csalary);
		statement.setInt(6,paydue);
		int count=statement.executeUpdate();
		
		
	 
		if(count>0)
	  {
		out.print("data inserted ok"); 
	request.getRequestDispatcher("Employee.html").include(request, response);
	  //response.sendRedirect("FetchCustomers");
	  }
	  else
	  {
		out.print("data not inserted");  
	  }
		con.close();  
	}
	catch(Exception e) {out.print(e);}
				}
		else
		{
		out.print("Empty fields!!");	
		}
	}
	
	
		
	}
	
