


/**
 *
 * @author sphatzik
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.util.*;




public class showSession extends HttpServlet {

    
    public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String title = "Session Monitoring";
    String heading;
    Integer accessCount = new Integer(0);;
    if (session.isNew()) {
      heading = "Welcome, Newcomer";
    } else {
      heading = "Welcome Back";
      Integer oldAccessCount =
        // Use getAttribute, not getValue, in version
        // 2.2 of servlet API.
        (Integer)session.getValue("accessCount"); 
      if (oldAccessCount != null) {
        accessCount =
          new Integer(oldAccessCount.intValue() + 1);
      }
    }
    // Use putAttribute in version 2.2 of servlet API.
    

    session.putValue("accessCount", accessCount); 
      
    out.println(ServletUtilities.headWithTitle(title) +
                "<H1 ALIGN=\"CENTER\">" + heading + "</H1>\n" +
                "<H2>Information on Your Session:</H2>\n" +
                "<TABLE BORDER=1 ALIGN=CENTER>\n" +
                "<TR BGCOLOR=\"#FFAD00\">\n" +
                "  <TH>Info Type<TH>Value\n" +
                "<TR>\n" +
                "  <TD>ID\n" +
                "  <TD>" + session.getId() + "\n" +
                "<TR>\n" +
                "  <TD>Creation Time\n" +
                "  <TD>" + new Date(session.getCreationTime()) + "\n" +
                "<TR>\n" +
                "  <TD>Time of Last Access\n" +
                "  <TD>" + new Date(session.getLastAccessedTime()) + "\n" +
                "<TR>\n" +
                "  <TD>Number of Previous Accesses\n" +
                "  <TD>" + accessCount + "\n" +
                "</TABLE>\n"+
                "</BODY></HTML>");

  }

  public void doPost(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }


    }




