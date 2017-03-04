package com.nt;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

/**
   This utility class is written to load the Employee profile picture from the database
*/
public class ProfSrv extends HttpServlet {
	
	private Connection con;
	private PreparedStatement ps;
	FileOutputStream fos=null;
	
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");

		
		//try loading the oracle driver
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","qwaszx");
			ps=con.prepareStatement("select * from emp_profile where empno=?");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
        
		try{

			//get servlet context to fetch the path of profile pic in filesystem
			ServletContext sc=getServletContext();

			//get Employee No. to be searched from html form
            int no=Integer.parseInt(req.getParameter("tno"));
			ps.setInt(1,no);
			ResultSet rs=ps.executeQuery();
		   fos=new FileOutputStream(sc.getInitParameter("path")+"output.jpg");
		   if(rs.next()){
			   pw.println("<center><h3>Employee no:"+rs.getInt(1)+"</h3></center>");
			   fos.write(rs.getBytes(2));
		   }
		   
        }
		catch(Exception e){
			e.printStackTrace();
		}

		//closing all resources
        try{
			if(fos!=null)
				fos.close();
		}
		catch(Exception e){
			e.printStackTrace();
        }
        try{
			if(ps!=null)
				ps.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			if(con!=null)
				con.close();
		}
		catch(Exception e){
			e.printStackTrace();

       
		}
	}

}


