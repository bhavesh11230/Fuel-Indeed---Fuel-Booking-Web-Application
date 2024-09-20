package fuelindeed.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fuelindeed.db.ConnectDB;

/**
 * Servlet implementation class DiselRate
 */
public class DiselRate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiselRate() {
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
		float fdiselrate = Float.parseFloat(request.getParameter("fdiselrate"));
		String femail = FuelStationGetSet.getFemail();
		Connection con = ConnectDB.dbCon();
		try
		{
			PreparedStatement ps = con.prepareStatement("update fuelstation set fdiselrate=? where femail=?");
			ps.setFloat(1, fdiselrate);
			ps.setString(2, femail);
			int i = ps.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("DiselRate.jsp");
			}
			else
			{
				response.sendRedirect("diselrate.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
