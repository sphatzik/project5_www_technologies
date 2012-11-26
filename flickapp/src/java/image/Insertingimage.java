import java.io.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
 
/* @author sphatzik*/
public class Insertingimage extends HttpServlet {
    
    
    
    private static final String TMP_PATH = "/var/lib/tomcat6/webapps/flickapp/files/images";
    private static final String DESTINATION ="/files/images";
    private File tmpDir;
    private File destinationDir;
 
    public void init(ServletConfig config) throws ServletException {
            super.init(config);
            tmpDir = new File(TMP_PATH);
        if(!tmpDir.isDirectory()) {
            throw new ServletException(TMP_PATH + " is not a directory");
        }
        String realPath = getServletContext().getRealPath(DESTINATION);
        destinationDir = new File(realPath);
        if(!destinationDir.isDirectory()) {
        throw new ServletException(DESTINATION+" is not a directory");
    }
}
 
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println();
 
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory ();

       fileItemFactory.setSizeThreshold(1*1024*1024); //size limit

        fileItemFactory.setRepository(tmpDir);
 
        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
try {
     List items = uploadHandler.parseRequest(request);
        Iterator itr = items.iterator();
                        
         String caption = new String("");
    while(itr.hasNext()) {
            FileItem item = (FileItem) itr.next();

            
            /*Caption and Filename handling*/

            if(item.isFormField()) {
            String tempString = ("caption");
            if(tempString.equals(item.getFieldName())){ caption = item.getString(); }
            } 
            else  {
                HttpSession session = request.getSession(true);


                out.println("<img src=\"/flickapp" + DESTINATION + "/" + item.getName() + "\"/></br>");
                out.println("<h2>" + caption + "</h2>");
                out.println("</br><b>Return to the</b><a href='insertview.jsp'>Home Page</a></b>");
                out.println("</br><b>Go to the</b><a href='upload.jsp'>Archive page</a></b>");
                    
            File file = new File(destinationDir, item.getName());
            item.write(file);
                    
//Create xml File
        File xml = new File(destinationDir, item.getName() + ".xml");
        BufferedWriter output = new BufferedWriter(new FileWriter(xml));
            output.write("<img>\n");
            output.write("<filename>" + item.getName() + "</filename>\n");
            output.write("<caption>" + caption + "</caption>\n");
            output.write("</img>\n");
            output.close();
    }

 }
}
    catch(FileUploadException ex) {
        log("Error encountered while parsing the request",ex);
    }
    catch(Exception ex) {
        log("Error encountered while uploading file",ex);
    }
                
      out.close();
 
}
 
}


