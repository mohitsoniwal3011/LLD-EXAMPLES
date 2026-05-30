package proxy;

interface Video{
    void play();
}

class RealVideo implements Video{

    private final String fileName;

    RealVideo(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    public void loadFromDisk(){
        System.out.println("Loading huge video file...");
    }

    @Override
    public void play() {
        System.out.println("Playing video...");
    }
}

class VideoProxy implements  Video{

    private final String fileName;
    private RealVideo realVideo;

    VideoProxy(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public void play() {
        if(realVideo == null){
            realVideo = new RealVideo(fileName);
        }
        realVideo.play();
    }
}
public class VideoVirtualProxyPattern {

    public static void main(String[] args) {




        Video proxy = new VideoProxy("some.mp4");
        proxy.play();  // loads from disk ( lazy / virtual loading )
        proxy.play();  // does not load


        Video v1 = new RealVideo("some.mp4"); // this gets loaded immeadiately

    }
}
