
package mytubeservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mytubeservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UploadVideo_QNAME = new QName("http://mytubeservice/", "uploadVideo");
    private final static QName _DownloadVideoResponse_QNAME = new QName("http://mytubeservice/", "downloadVideoResponse");
    private final static QName _UploadVideoResponse_QNAME = new QName("http://mytubeservice/", "uploadVideoResponse");
    private final static QName _DownloadVideo_QNAME = new QName("http://mytubeservice/", "downloadVideo");
    private final static QName _UploadVideoVideoInput_QNAME = new QName("", "videoInput");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mytubeservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DownloadVideo }
     * 
     */
    public DownloadVideo createDownloadVideo() {
        return new DownloadVideo();
    }

    /**
     * Create an instance of {@link UploadVideoResponse }
     * 
     */
    public UploadVideoResponse createUploadVideoResponse() {
        return new UploadVideoResponse();
    }

    /**
     * Create an instance of {@link UploadVideo }
     * 
     */
    public UploadVideo createUploadVideo() {
        return new UploadVideo();
    }

    /**
     * Create an instance of {@link DownloadVideoResponse }
     * 
     */
    public DownloadVideoResponse createDownloadVideoResponse() {
        return new DownloadVideoResponse();
    }

    /**
     * Create an instance of {@link VideoFile }
     * 
     */
    public VideoFile createVideoFile() {
        return new VideoFile();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadVideo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mytubeservice/", name = "uploadVideo")
    public JAXBElement<UploadVideo> createUploadVideo(UploadVideo value) {
        return new JAXBElement<UploadVideo>(_UploadVideo_QNAME, UploadVideo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadVideoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mytubeservice/", name = "downloadVideoResponse")
    public JAXBElement<DownloadVideoResponse> createDownloadVideoResponse(DownloadVideoResponse value) {
        return new JAXBElement<DownloadVideoResponse>(_DownloadVideoResponse_QNAME, DownloadVideoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadVideoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mytubeservice/", name = "uploadVideoResponse")
    public JAXBElement<UploadVideoResponse> createUploadVideoResponse(UploadVideoResponse value) {
        return new JAXBElement<UploadVideoResponse>(_UploadVideoResponse_QNAME, UploadVideoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadVideo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mytubeservice/", name = "downloadVideo")
    public JAXBElement<DownloadVideo> createDownloadVideo(DownloadVideo value) {
        return new JAXBElement<DownloadVideo>(_DownloadVideo_QNAME, DownloadVideo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "videoInput", scope = UploadVideo.class)
    public JAXBElement<byte[]> createUploadVideoVideoInput(byte[] value) {
        return new JAXBElement<byte[]>(_UploadVideoVideoInput_QNAME, byte[].class, UploadVideo.class, ((byte[]) value));
    }

}
