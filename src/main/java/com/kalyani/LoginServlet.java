package com.kalyani;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String userId=request.getParameter("userId");
		String Password=request.getParameter("password");
		//JDBC Connection
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection conn=DriverManager.getConnection("jdbc:Mysql://localhost:3306/kalyani","root","Kalyani@1502");
		    Statement st=conn.createStatement();
		    String query="select * from user where userid= '"+userId+"' and password='"+Password+"'";
		    ResultSet rs=st.executeQuery(query);
		    if(rs.next()) {
		    	out.print("<h1>"+userId+": Welcome to the Home Page </h1><br>");
		    	out.print("<h1>Login Successfully!</h1><br>");
		    }
		    else {
		    	out.print("<h1>"+userId+":Please enter correct userId and Password</h1><br>");
		    	out.print("<h1>Login Failed</h1><br>");
		    	
		    }
		    rs.close();
		    st.close();
		    conn.close();
		}
		catch(ClassNotFoundException e) {
			out.print("<h1>Login Failed! because of server exception</h1><br>");
			e.printStackTrace();
		}
		catch(SQLException e) {
			out.print("<h1>Login Failed! because of server exception</h1><br>");
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
