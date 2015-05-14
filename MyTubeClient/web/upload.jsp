<%-- 
    Document   : newjsp
    Created on : 13/11/2014, 19:29:26
    Author     : Daniel
--%>

<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator" %>
<%@page import="org.apache.commons.fileupload.*" %>
<%@page import="org.apache.commons.fileupload.disk.*" %>
<%@page import="org.apache.commons.fileupload.servlet.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
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
                    <div class="panel-heading"><h3>Arquivo enviado com sucesso!</h3></div>
                    <div class="panel-body">
                        <%
                            try {

                                // Conexão com o Web Service
                                mytubeservice.MyTubeService_Service service = new mytubeservice.MyTubeService_Service();
                                mytubeservice.MyTubeService port = service.getMyTubeServicePort();

                            // Verificação de que o request é para o upload de um arquivo
                                // (Tecnicamente, nunca deve acontecer um erro aqui)
                            /*boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                                 if(!isMultipart) {
                                 out.println("Erro");
                                 }*/
                                // Cria um DiskFileItemFactory para manipulação dos arquivos
                                DiskFileItemFactory factory = new DiskFileItemFactory();

                            // Configura o repositório em que os arquivos serão temporariamente salvos
                                // para uso e conexão com o Web Service
                                ServletContext servletContext = this.getServletConfig().getServletContext();
                                File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
                                factory.setRepository(repository);

                                // Cria o novo manipulador de arquivos
                                ServletFileUpload upload = new ServletFileUpload(factory);

                                // Faz o parse da requisição, recuperando a lista de arquivos enviados
                                List<FileItem> items = upload.parseRequest(request);

                            // Processa os arquivos enviados
                                // Como temos apenas um arquivo sendo enviado, pegamos somente ele
                                // (sem necessidade de um while(it.hasNext())
                                Iterator<FileItem> it = items.iterator();
                                FileItem item = it.next();

                                // Pega o nome do video, bem como ele em si (em array de bytes)
                                java.lang.String fileName = item.getName();
                                byte[] videoInput = item.get();

                                // Pega a descrição do video
                                FileItem item2 = it.next();
                                String descricao = item2.getString();

                            // A chamada ao método de upload do Web Service devolve a ID do novo video
                                // adicionado
                                java.lang.String IdResult = port.uploadVideo(videoInput, descricao, fileName);

                                out.println("<form class='form-horizontal' role='form'>");
                                out.println("<div class='form-group'>");
                                out.println("<label for='nome' class='col-sm-2 control-label'>Vídeo:</label>");
                                out.println("<div class='col-sm-10'>");
                                out.println("<p id='nome' style='margin-top: 7px;''>" + fileName + "</p>");
                                out.println("</div></div>");
                                out.println("<div class='form-group'>");
                                out.println("<label for='descricao' class='col-sm-2 control-label'>Descrição:</label>");
                                out.println("<div class='col-sm-10'>");
                                out.println("<p id='descricao' style='margin-top: 7px;''>" + descricao + "</p>");
                                out.println("</div></div>");
                                out.println("<div class='form-group'>");
                                out.println("<label for='id' class='col-sm-2 control-label'>ID gerado:</label>");
                                out.println("<div class='col-sm-10'>");
                                out.println("<p id='id' style='margin-top: 7px;''>" + IdResult + "</p>");
                                out.println("</div></div>");
                                out.println("</form>");
                            } catch (Exception ex) {

                                out.print(ex.toString());

                            }
                        %>                      
                        <a href="index.html"><button class="btn btn-primary pull-right">Retornar</button></a>
                    </div>
                </div>
            </div>

            <footer class="footer">
                <p>&copy; Alexandre - Daniel - Sistemas Distribuidos - UFSCar</p>
            </footer>

        </div> <!-- /container -->
    </body>
</html>