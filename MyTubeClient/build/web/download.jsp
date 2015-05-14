<%-- 
    Document   : newjsp
    Created on : 13/11/2014, 19:29:26
    Author     : Daniel
--%>

<%@page import="java.io.File"%>
<%@page import="mytubeservice.VideoFile" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator" %>
<%@page import="org.apache.commons.fileupload.*" %>
<%@page import="org.apache.commons.fileupload.disk.*" %>
<%@page import="org.apache.commons.fileupload.servlet.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>    <%-- start web service invocation --%><hr/>
    <%
    try {
        
        // Pega o arquivo de vídeo pela sessão
        VideoFile videoFile = (VideoFile) session.getAttribute("videoFile");
        
        // Seta os atributos da resposta e manda o arquivo        
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment;filename=\"" + videoFile.getFileName() + "\"");
        
        ServletOutputStream servOut = response.getOutputStream();
        servOut.write(videoFile.getData());
        servOut.flush();
        servOut.close();
        

    } catch (Exception ex) {
        
	out.print(ex.toString());
        
    }
    %>
    <%-- end web service invocation --%><hr/>

</html>
