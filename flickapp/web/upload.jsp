<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ page language="java" %>
<%@ page import="java.io.*" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.*" %>
<%@ page import="org.w3c.dom.*" %>
<%@ page import="javax.xml.parsers.DocumentBuilder" %>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>flick-app</title>
<script>
function show_main_photo(imgName) {
     var curImage = document.getElementById('currentImg');
     var thePath = 'files/images/';
     var theSource = thePath + imgName;
     curImage.src = theSource;
     curImage.alt = imgName;
     curImage.title = imgName;
}
</script>
</head>
<body>
<table>
    </br><b></b><a href='insertview.jsp'><------hOmE_pAgE</a></b>
<tr>
<td>
<div align="center">
<img id="currentImg" src="" />
</div>

<div id="gallery">

<%
String path = new String("/var/lib/tomcat6/webapps/flickapp/files/images");
String srv_path = new String("/flickapp/files/images");

File gallery_dir = new File(path);
File[] files_list = gallery_dir.listFiles();

for (int i = 0; i < files_list.length; i++) {

Arrays.sort( files_list, new Comparator()
{
    public int compare(Object o1, Object o2) {

    if (((File)o1).lastModified() > ((File)o2).lastModified()) {
        return -1;
    }   
    else if (((File)o1).lastModified() < ((File)o2).lastModified()) {
        return +1;
    } 
    else{
        return 0;
    }
}

});

     if (files_list[i].isFile()) {

        String file_name = files_list[i].getName();
        int pos = file_name.lastIndexOf('.');
        String file_ext = file_name.substring(pos+1);

if(file_ext.equals("xml")){

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(path + "/" + file_name);

        NodeList nodeListFN = doc.getElementsByTagName("filename");
        Element elementsFN = (Element)nodeListFN.item(0);
        String filename = elementsFN.getChildNodes().item(0).getNodeValue();

        NodeList nodeListC = doc.getElementsByTagName("caption");
        Element elementsC = (Element)nodeListC.item(0);
        String caption = elementsC.getChildNodes().item(0).getNodeValue();

        out.println("<table class=\"thumbnail\">");
        out.println(" <tr><td align=\"center\">" + caption + "</td></tr>");
        out.println(" <tr><td class=\"thumbnail_cell\"><img onclick=\"show_main_photo('" + filename + "');\" src=\"" + srv_path + "/" + filename + "\" alt=\"" + filename + "\" /></td></tr>");
        out.println("</table>");
      }
    }
 }
%>
</div>  
</td>
</tr>
</table>
</body>
</html>