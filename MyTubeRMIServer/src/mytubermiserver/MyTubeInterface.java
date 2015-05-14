package mytubermiserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyTubeInterface extends Remote {

	void uploadVideo(VideoFile videoFile) throws RemoteException;
	
	VideoFile downloadVideo(String videoId) throws RemoteException;

}