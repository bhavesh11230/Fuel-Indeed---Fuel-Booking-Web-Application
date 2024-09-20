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
 * Servlet implementation class DPersonLogin
 */
public class DPersonLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DPersonLogin() {
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
		String demail = request.getParameter("demail");
		String dpassword = request.getParameter("dpassword");
		
		Connection con = ConnectDB.dbCon();
		try
		{
			DPersonEmailGetSet.setDemail(demail);
			PreparedStatement ps = con.prepareStatement("select * from dperson where demail=? and dpassword=?");
			ps.setString(1, demail);
			ps.setString(2, dpassword);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				DPersonEmailGetSet.setId();
				response.sendRedirect("DPersonTask.jsp");
			}
			else
			{
				response.sendRedirect("dpersonlogin.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
