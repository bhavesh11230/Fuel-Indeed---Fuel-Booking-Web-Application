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
 * Servlet implementation class FuelRegister
 */
public class FuelRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FuelRegister() {
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
		int fid = 0;
		String fname = request.getParameter("fname");
		String fopentime = request.getParameter("fopentime");
		String fclosetime = request.getParameter("fclosetime");
		String faddress = request.getParameter("faddress");
		String farea = request.getParameter("farea");
		String fcity = request.getParameter("fcity");
		String fpincode = request.getParameter("fpincode");
		String fpetrolqty = request.getParameter("fpetrolqty");
		String fpetrolrate = request.getParameter("fpetrolrate");
		String fdiselqty = request.getParameter("fdiselqty");
		String fdiselrate = request.getParameter("fdiselrate");
		String fcontact = request.getParameter("fcontact");
		String femail = request.getParameter("femail");
		String fpassword = request.getParameter("fpassword");
		String fstatus = "pending";
		Connection con = ConnectDB.dbCon();
		
		try
		{
			PreparedStatement ps = con.prepareStatement("insert into fuelstation values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, fid);
			ps.setString(2, fname);
			ps.setString(3, fopentime);
			ps.setString(4, fclosetime);
			ps.setString(5, faddress);
			ps.setString(6, farea);
			ps.setString(7, fcity);
			ps.setString(8, fpincode);
			ps.setString(9, fpetrolqty);
			ps.setString(10, fpetrolrate);
			ps.setString(11, fdiselqty);
			ps.setString(12, fdiselrate);
			ps.setString(13, fcontact);
			ps.setString(14, femail);
			ps.setString(15, fpassword);
			ps.setString(16, fstatus);
			
			int i = ps.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("fuelstationlogin.html");
			}
			else
			{
				response.sendRedirect("fuelstationregister.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
