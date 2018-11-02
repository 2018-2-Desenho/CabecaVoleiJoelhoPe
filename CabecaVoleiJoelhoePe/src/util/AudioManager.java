package util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class AudioManager {
    static private AudioManager instance;
    private HashMap<String, AudioClip> clips;

    private AudioManager(){
        clips = new HashMap<String, AudioClip>();
    }

    static public AudioManager getInstance(){
        if (instance == null){
            instance = new AudioManager();
        }
        return instance;
    }

    public AudioClip loadAudio(String fileName) throws IOException{
        URL url = getClass().getResource("/" + fileName);
        if (url == null){
            throw new RuntimeException("O áudio /" + fileName
                            + " não foi encontrado.");
        } else{
            if (clips.containsKey(fileName)){
                return clips.get(fileName);
            } else{
                AudioClip clip = Applet.newAudioClip(getClass().getResource("/" + fileName));
                clips.put(fileName, clip);
                return clip;
            }
        }
    }
    
}
