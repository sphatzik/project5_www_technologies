import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;



public class sessionCounter implements HttpSessionListener {
  private int totalSessionCount = 0;
  private int currentSessionCount = 0;
  private int maxSessionCount = 0;
  private ServletContext context = null;

  public void sessionCreated(HttpSessionEvent event) {
   totalSessionCount++;
   currentSessionCount++;
   if (currentSessionCount > maxSessionCount) {
     maxSessionCount = currentSessionCount;
   }
   if (context == null) {
    storeInServletContext(event);
   }
}

public void sessionDestroyed(HttpSessionEvent event) {
  currentSessionCount--;
}

/** The total number of sessions created. */

public int getTotalSessionCount() {
  return(totalSessionCount);
}

/** The number of sessions currently in memory. */

public int getCurrentSessionCount() {
  return(currentSessionCount);
}
   /** The largest number of sessions ever in memory
    * at any one time.
    */

   public int getMaxSessionCount() {
    return(maxSessionCount);
  }

  // Register self in the servlet context so that
  // servlets and JSP pages can access the session counts.

  private void storeInServletContext(HttpSessionEvent event) {
    HttpSession session = event.getSession();
    context = session.getServletContext();
    context.setAttribute("counter", this);
  }
}