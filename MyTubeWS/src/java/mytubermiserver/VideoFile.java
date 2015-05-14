package mytubermiserver;

import java.io.Serializable;

/**
 *
 * @author Alexandre
 */
public class VideoFile implements Serializable {
    
    private byte[] data;
    private String fileName;
    private String description;
    private String videoId;
    
    private static final long serialVersionUID = 1L;
    
    public VideoFile() {
        
        super();
        
    }
    
    
    public VideoFile(byte[] data, String fileName) {
        
        this.data = data;
        this.fileName = fileName;
        
    }
    
    
    public VideoFile(byte[] data, String fileName, String videoId) {
        
        this.data = data;
        this.fileName = fileName;
        this.videoId = videoId;
        
    }
    
    
    public void setDescription(String description) {
        
        this.description = description;
        
    }
    
    
    public void setVideoId(String videoId) {
        
        this.videoId = videoId;
        
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
    public byte[] getData() {
        
        return data;
        
    }

    
    public String getFileName() {
        
        return fileName;
        
    }

    
    public String getDescription() {
        
        return description;
        
    }
    
    public String getVideoId() {
        
        return videoId;
        
    }

    
}
