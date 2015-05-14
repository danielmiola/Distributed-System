/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytubeservice;

import mytubermiserver.MyTubeInterface;
import mytubermiserver.VideoFile;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 *
 * @author Alexandre
 */
@WebService(serviceName = "MyTubeService")
public class MyTubeService {

    /**
     * Método de upload de um video
     */
    @WebMethod(operationName = "uploadVideo")
    public String uploadVideo(@WebParam(name = "videoInput") byte[] videoInput,
            @WebParam(name = "descricao") String descricao,
            @WebParam(name = "videoName") String videoName) {
        
        String retorno = null;
        String videoId = null;
        
        try {

            // Pega o registro na máquina servidora (substituir localhost pelo ip da maquina)
            Registry registry = LocateRegistry.getRegistry("192.168.105.165");

            // Pega o stub
            MyTubeInterface stub = (MyTubeInterface) registry.lookup("Video");
            
            // Conexão com a nuvem
            URL url = new URL("http://1-dot-mytubealedan.appspot.com/cloud");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();			
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            
            OutputStreamWriter writer = new OutputStreamWriter(
                    connection.getOutputStream());
            
            // Salva o novo video (descrição + ID) na nuvem
            writer.write("method=save" + "&descricao=" + descricao);
            writer.close();
	
            // Recupera a ID gerada
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
		videoId = connection.getHeaderField("id");
            } else {
                // Server returned HTTP error code.
		System.out.println("Server returned HTTP error code");
            }
            
            VideoFile videoFile = new VideoFile();
            videoFile.setData(videoInput);
            videoFile.setFileName(videoName);
            videoFile.setVideoId(videoId);

            // Faz o upload do video para o banco de dados
            stub.uploadVideo(videoFile);

            retorno = videoId;

	}
	catch (Exception e) {

            System.err.println("Client exception: " + e.toString());
            retorno = e.toString();
            e.printStackTrace();

	}
        
        return retorno;
        
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "downloadVideo")
    public VideoFile downloadVideo(@WebParam(name = "videoId") String videoId) {
        
        VideoFile videoFile = null;
        String descricao = null;
        
        try {
        
            // Pega o registro na máquina servidora (substituir localhost pelo ip da maquina)
            Registry registry = LocateRegistry.getRegistry("192.168.105.165");

            // Pega o stub
            MyTubeInterface stub = (MyTubeInterface) registry.lookup("Video");
            
            URL url = new URL("http://1-dot-mytubealedan.appspot.com/cloud");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
			
            OutputStreamWriter writer = new OutputStreamWriter(
                    connection.getOutputStream());			
            writer.write("method=return" + "&id=" + videoId);
            writer.close();
			
            if (connection.getResponseCode() ==	HttpURLConnection.HTTP_OK) {
                descricao = connection.getHeaderField("descricao");
            }
            else {
                // Server returned HTTP error code.
                System.out.println("Server returned HTTP error code");
            }
            
            // Faz o download do video
            videoFile = stub.downloadVideo(videoId);
            
            videoFile.setDescription(descricao);
            
        }
        catch (Exception e) {
            
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            
        }
        
        return videoFile;
        
    }

}
