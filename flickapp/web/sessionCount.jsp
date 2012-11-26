<%-- 
    Document   : sessionCount
    Created on : Nov 27, 2012, 12:50:42 AM
    Author     : sphatzik
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Session Info</TITLE>
<LINK REL=STYLESHEET
     HREF="events-styles.css"
     TYPE="text/css">
</HEAD>

<BODY>
<TABLE BORDER=5 ALIGN="CENTER">
  <TR><TH CLASS="TITLE">Session Info</TABLE>
<P>

<jsp:useBean class="sessionCounter"
          id="counter" scope="application" />
<UL>
<LI>Total number of sessions in the life of this
   Web application:
   <jsp:getProperty name="counter"
               property="totalSessionCount" />.
<LI>Number of sessions currently in memory:
  <jsp:getProperty name="counter"
              property="currentSessionCount" />.
<LI>Maximum number of sessions that have ever been in
    memory at any one time:
   <jsp:getProperty name="counter"
               property="maxSessionCount" />.
</UL>

</BODY>
</HTML>
