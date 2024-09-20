<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="fuelindeed.services.*" %>
<%@ page import="fuelindeed.db.ConnectDB" %>
<%@ page import="fuelindeed.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
               Connection con = ConnectDB.dbCon();
               	try 
               	{
               		String uemail=UserGetSet.getUemail();
               		int code = Integer.parseInt(request.getParameter("code"));
               		int uid = UserGetSet.getId();
               		String status = "delivered";
               		PreparedStatement ps = con.prepareStatement("update bookings set status=? where code=?");
               		 ps.setString(1, status);
               		ps.setInt(2, code);
                    int i = ps.executeUpdate();
                    if(i>0) 
                    {
                    	response.sendRedirect("DPersonTask.jsp");
                    }
                    else
                    {
                    	response.sendRedirect("codeenter.html");
                    }
                } 
               	catch(Exception e) 
               	{
                    e.printStackTrace();
                }
            %>
</body>
</html>