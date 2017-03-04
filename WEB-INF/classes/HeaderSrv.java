
package com.nt;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class HeaderSrv extends HttpServlet {
	public void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		//display header information in corresponding servelt components
		PrintWriter writer=response.getWriter();
		response.setContentType("text/html");
		writer.println("<center><font color="+"#1A878F"+" size=7><i>Nayan Technologies</font></center><br>");
        writer.println("<hr>");
    }
}