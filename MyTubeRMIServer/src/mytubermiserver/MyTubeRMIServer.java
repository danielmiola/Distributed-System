package mytubermiserver;

import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.gridfs.*;
import com.mongodb.BasicDBObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.io.File;
import java.io.FileInputStream;

import java.net.UnknownHostException;

public class MyTubeRMIServer implements MyTubeInterface {

	public MyTubeRMIServer() {

		super();

	}


	public void uploadVideo(VideoFile videoFile) {

		try {

			// Fazemos a conexão com o banco de dados NoSQL
			MongoClient client = new MongoClient("localhost", 27017);
			DB db = client.getDB("mytube");

			// Criamos um GridFS, da API do MongoDB, para a manipulação
			// de arquivos grandes na coleção 'videos'
			GridFS collec = new GridFS(db, "videos");

			// Objeto pra inserção na coleção
			GridFSInputFile videoInput = collec.createFile(videoFile.getData());
			
			videoInput.setFilename(videoFile.getFileName());
			videoInput.setId(videoFile.getVideoId());

			// Salva o BD
			videoInput.save();

		}
		catch (UnknownHostException ue) {

			System.err.println("Erro na conexão com o banco de dados");
			ue.printStackTrace();

		}
		catch (MongoException me) {

			System.err.println("Erro na manipulação do banco de dados");
			System.err.println("Codigo do erro: " + me.getCode());
			me.printStackTrace();

		}

	}


	public VideoFile downloadVideo(String videoId) {
            
            VideoFile videoFile = null;

		try {

			// Fazemos a conexão com o banco de dados NoSQL
			MongoClient client = new MongoClient("localhost", 27017);
			DB db = client.getDB("mytube");

			// Criamos um GridFS, da API do MongoDB, para a manipulação
			// de arquivos grandes na coleção 'videos'
			GridFS collec = new GridFS(db, "videos");

			// Busca o objeto na coleção
			BasicDBObject video = new BasicDBObject("_id", videoId);
			GridFSDBFile videoOutput = collec.findOne(video);
                        
                        
                        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                        videoOutput.writeTo(byteArray);
                        
                        videoFile = new VideoFile(byteArray.toByteArray(), 
                                                            videoOutput.getFilename());
                        
		}
		catch (UnknownHostException ue) {

			System.err.println("Erro na conexão com o banco de dados");
			ue.printStackTrace();

		}
		catch (IOException io) {

			System.err.println("Erro ao escrever o arquivo de copia");
			io.printStackTrace();

		}
		catch (MongoException me) {

			System.err.println("Erro na manipulação do banco de dados");
			System.err.println("Codigo do erro: " + me.getCode());
			me.printStackTrace();

		}
                
                return videoFile;

	}


	public static void main(String args[]) {

		try {

			MyTubeRMIServer serverObj = new MyTubeRMIServer();
			MyTubeInterface stub = (MyTubeInterface) UnicastRemoteObject.exportObject(serverObj, 0);

                        LocateRegistry.createRegistry(1099);
			Registry registry = LocateRegistry.getRegistry();

			registry.rebind("Video", stub);

			System.err.println("Server ready");

		}
		catch (Exception e) {

			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();

		}

	}

}