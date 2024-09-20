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
 * Servlet implementation class FuelLogin
 */
public class FuelLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FuelLogin() {
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
		String femail = request.getParameter("femail");
		String fpassword = request.getParameter("fpassword");
		String fstatus = "approved";
		
		Connection con = ConnectDB.dbCon();
		try
		{
			PreparedStatement ps = con.prepareStatement("select * from fuelstation where femail=? and fpassword=? and fstatus=?");
			ps.setString(1, femail);
			ps.setString(2, fpassword);
			ps.setString(3, fstatus);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				FuelStationGetSet.setFemail(femail);
				FuelStationGetSet.setId();
				response.sendRedirect("FuelStationTask.jsp");
			}
			else
			{
				response.sendRedirect("fuelstationlogin.html");
			}
		}
		catch(Exception e)
		{
			
		}
	}

}
