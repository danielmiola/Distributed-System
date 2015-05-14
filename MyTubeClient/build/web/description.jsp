<%-- 
    Document   : description
    Created on : 29/11/2014, 21:58:58
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
<html lang="en">
    <%
        VideoFile videoFile = null; // Declarando fora do try para ser acessível fora dele

        try {

            // Conexão com o Web Service
            mytubeservice.MyTubeService_Service service = new mytubeservice.MyTubeService_Service();
            mytubeservice.MyTubeService port = service.getMyTubeServicePort();

            // Pega o ID do video pelo request
            String videoId = request.getParameter("arquivoID");

            // Baixa o vídeo
            videoFile = port.downloadVideo(videoId);

            // Seta o vídeo como atributo de sessão
            session.setAttribute("videoFile", videoFile);

        } catch (Exception ex) {

            out.print(ex.toString());

        }
    %>
    <%-- end web service invocation --%>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <title>Mytube</title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/jumbotron-narrow.css" rel="stylesheet">

        <!-- JQuery core -->
        <script src="js/jquery-2.1.1.min.js"></script>
    </head>

    <body>
        <div class="container">
            <div class="header">
                <h3 class="text-muted"><a href="index.html">MyTube</a></h3>
            </div>

            <div class="container">
                <div class="panel panel-success">
                    <div class="panel-heading"><h3>Vídeo encontrado com sucesso!</h3></div>
                    <div class="panel-body">
                        <form class="form-horizontal" role="form" action="download.jsp" method="get" enctype="multipart/form-data">
                          <div class="form-group">
                            <label for="nome" class="col-sm-2 control-label">Vídeo:</label>
                            <div class="col-sm-10">
                              <p id="nome" style="margin-top: 7px;"><% out.println(videoFile.getDescription());%></p>
                            </div>                            
                          </div>
                          <input class="btn btn-success center-block" type="submit" value="Baixar video"/>
                        </form>
                            <a href="index.html" style="margin-top: -20px;"><button class="btn btn-primary pull-right">Retornar</button></a>
                    </div>
                </div>
            </div>

            <footer class="footer">
            <p>&copy; Alexandre - Daniel - Sistemas Distribuidos - UFSCar</p>
            </footer>

        </div> <!-- /container -->
    </body>
</html>