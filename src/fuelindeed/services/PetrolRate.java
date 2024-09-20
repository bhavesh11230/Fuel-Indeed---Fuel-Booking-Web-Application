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
 * Servlet implementation class PetrolRate
 */
public class PetrolRate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetrolRate() {
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
		float fpetrolrate = Float.parseFloat(request.getParameter("fpetrolrate"));
		String femail = FuelStationGetSet.getFemail();
		Connection con = ConnectDB.dbCon();
		try
		{
			PreparedStatement ps = con.prepareStatement("update fuelstation set fpetrolrate=? where femail=?");
			ps.setFloat(1, fpetrolrate);
			ps.setString(2, femail);
			int i = ps.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("PetrolRate.jsp");
			}
			else
			{
				response.sendRedirect("petrolrate.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
