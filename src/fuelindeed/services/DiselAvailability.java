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
 * Servlet implementation class DiselAvailability
 */
public class DiselAvailability extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiselAvailability() {
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
		float fdiselqty = Float.parseFloat(request.getParameter("fdiselqty"));
		String femail = FuelStationGetSet.getFemail();
		Connection con = ConnectDB.dbCon();
		try
		{
			PreparedStatement ps = con.prepareStatement("update fuelstation set fdiselqty=? where femail=?");
			ps.setFloat(1, fdiselqty);
			ps.setString(2, femail);
			int i = ps.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("DiselAvailability.jsp");
			}
			else
			{
				response.sendRedirect("diselavailability.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
