package fuelindeed.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fuelindeed.db.ConnectDB;

/**
 * Servlet implementation class AdminLogin
 */
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String aemail = request.getParameter("aemail");
		String apassword = request.getParameter("apassword");
		
		Connection con = ConnectDB.dbCon();
		try
		{
			PreparedStatement ps = con.prepareStatement("select * from admin where aemail=? and apassword=?");
			ps.setString(1, aemail);
			ps.setString(2, apassword);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				response.sendRedirect("adminmenu.html");
			}
			else
			{
				response.sendRedirect("adminlogin.html");
			}
		}
		catch(Exception e)
		{
			
		}
	}

}
