package com.nt.servlet;

/*03/08/2022
 * Example App
 * -----------
 *SessionApp1-HttpSessionWithCookies
 *How to see the cookies are there:- In the browser go to settings -> Cookies and site permissions -> Manage and delete cookies and site data -> see all cookies and site data -> search for localhost -> and see the details  
 *
 * 04/08/2022
 * ==========
 * Disad
 * Q) if we give session idle time period by using both programatic approach 
 * 
 * What happens if restart the server in the middle of the session?
 * 
 * Ans) w.r.t Tomcat 10 server
 *      =>The session will not be continued if u restart server in the middle of browsing
 *        session because all the session objs and their data will be destroyed once the server is down...
 *        
 *     but if we enable session persistence across the Tomcat restarts then the session will be
 *     continued..though we restart the server for multiple times. for this we need to perform some uncommenting activity in 
 *     context.xml file
 *     
 *     <!-- Uncomment this to enable session persistence across Tomcat restarts -->
     
    <Manager pathname="SESSIONS.ser" />
    
    This uncommenting makes the server to perform serilization of Session objs data and session ids to
    Sessions.ser file when the server is about to shutdown..Once the server is restarted the server perform Deserialization on Session.ser
    file to create the Session objs having old data and Session ids..
    This process makes us to continue the session across the multiple Restarts of the Tomcat Server..
    
  note:: This session persistence feature is available only in Tomcat server..not in other servers..  
 *        
 * */
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw=res.getWriter();
		//set response content type
		res.setContentType("text/html");
		
		//read form data(form1/req1 data)
		String name=req.getParameter("pname");
		String fname=req.getParameter("fname");
		String ms=req.getParameter("ms");
		//create HttpSession object
		HttpSession ses=req.getSession();
		
		//add form1/req1 data to Session obj as session attribute values
		ses.setAttribute("name",name);
		ses.setAttribute("fname",fname);
		ses.setAttribute("ms",ms);
		
		//session's idle timeout period
		ses.setMaxInactiveInterval(60);
		//generate dynamic form page based on the marital status
		if(ms.equalsIgnoreCase("single")) {
			pw.println("<h1 style='color:red;text-align:center'>Person Registration(Bachelor life)---shadi.com</h1>)");
			pw.println("<form action='secondurl'method='post'>");
			pw.println("<table border='0'bgcolor='cyan'align='center'>");
			pw.println("<tr><td> when do u want to marry?</td>");
			pw.println("<td><input type='text' name='f2t1'></td></tr>");
			pw.println("<tr><td>why do you want to marry?</td>");
			pw.println("<td><input type='text' name='f2t2'></td></tr>");
			pw.println("<tr><td colspan='2'><input type='submit' value='submit'></td></tr>");
			pw.println("</table>");
			pw.println("</form>");
		}
		else {
			pw.println("<h1 style='color:red;text-align:center'>Person Registration(Married life)---shadi.com</h1>)");
			//when this servlet is executed the form goes to browser that means this 'secondurl' goes to browser
			//when the dynamic form is submitted again the url comes back to brawser i.e the url is traveling along with 
			//the request and response 
			pw.println("<form action='secondurl'method='post'>");
			pw.println("<table border='0'bgcolor='cyan'align='center'>");
			pw.println("<tr><td> spouse name::</td>");
			pw.println("<td><input type='text' name='f2t1'></td></tr>");
			pw.println("<tr><td> No.of children::</td>");
			pw.println("<td><input type='text' name='f2t2'></td></tr>");
			pw.println("<tr><td colspan='2'><input type='submit' value='submit'></td></tr>");
			pw.println("</table>");
			pw.println("</form>");
		}//else
		
		pw.println("<br><b>Session id::"+ses.getId()+"</b>");
		//close stream
		pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}//doPost(-,-)

}//class
