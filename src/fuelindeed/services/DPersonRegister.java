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
 * Servlet implementation class DPersonRegister
 */
public class DPersonRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DPersonRegister() {
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
		int did = 0;
		int fid=FStationIDGetSet.getFid();
		System.out.println(fid);
		String dname = request.getParameter("dname");
		String dcity = request.getParameter("dcity");
		String dcontact = request.getParameter("dcontact");
		String daddress = request.getParameter("daddress");
		String dpincode = request.getParameter("dpincode");
		String demail = request.getParameter("demail");
		String dpassword = request.getParameter("dpassword");
		Connection con = ConnectDB.dbCon();
		
		try
		{
			PreparedStatement ps = con.prepareStatement("insert into dperson values (?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, did);
			ps.setInt(2, fid);
			ps.setString(3, dname);
			ps.setString(4, dcity);
			ps.setString(5, dcontact);
			ps.setString(6, daddress);
			ps.setString(7, dpincode);
			ps.setString(8, demail);
			ps.setString(9, dpassword);
			
			int i = ps.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("FuelStationTask.jsp");
			}
			else
			{
				response.sendRedirect("dpersonregister.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
