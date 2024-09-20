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
 * Servlet implementation class ChangeStationPassword
 */
public class ChangeStationPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeStationPassword() {
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
		String fpassword = request.getParameter("opassword");
		String npassword = request.getParameter("npassword");
		Connection con = ConnectDB.dbCon();
		try
		{
			PreparedStatement ps = con.prepareStatement("update fuelstation set fpassword=? where fpassword=? and femail=?");
			ps.setString(1, npassword);
			ps.setString(2, fpassword);
			ps.setString(3, femail);
			int i = ps.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("FuelStationTask.jsp");
			}
			else
			{
				response.sendRedirect("changestationpassword.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
