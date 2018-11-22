package util;

import java.util.HashMap;

public interface AbstractGameBuilder {
    
    public AbstractGameBuilder createSceneElements(HashMap<String, String> imageFileNames);
    public AbstractGameBuilder createGameElements(HashMap<String, String> imageFileNames);
    public AbstractGameBuilder createControlElements();
    public GameManager getResult();
}
