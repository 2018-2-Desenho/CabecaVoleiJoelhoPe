package util;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class InputManager {
    static protected int KEY_RELEASED = 0;
    static protected int KEY_JUST_PRESSED = 1;
    static protected int KEY_PRESSED = 2;
    static private InputManager instance;
    private HashMap<Integer, Integer> keyCache;
    private ArrayList<Integer> pressedKeys;
    private ArrayList<Integer> releasedKeys;

    private InputManager(){
        keyCache = new HashMap<Integer, Integer>();
        pressedKeys = new ArrayList<Integer>();
        releasedKeys = new ArrayList<Integer>();
    }

    static public InputManager getInstance(){
        if (instance == null){
            instance = new InputManager();
        }
        return instance;
    }

    public boolean isPressed(int keyId){
        return keyCache.containsKey(keyId)
                        && !keyCache.get(keyId).equals(KEY_RELEASED);
    }

    public boolean isJustPressed(int keyId){
        return keyCache.containsKey(keyId)
                            && keyCache.get(keyId).equals(KEY_JUST_PRESSED);
    }

    public boolean isReleased(int keyId)
    {
        return !keyCache.containsKey(keyId)
                        || keyCache.get(keyId).equals(KEY_RELEASED);
    }

    public void update(){
        for (Integer keyCode : keyCache.keySet()){
            if (isJustPressed(keyCode)){
                keyCache.put(keyCode, KEY_PRESSED);
            }
        }
        for (Integer keyCode : releasedKeys){
            keyCache.put(keyCode, KEY_RELEASED);
        }
        for (Integer keyCode : pressedKeys){
            if (isReleased(keyCode)){
                keyCache.put(keyCode, KEY_JUST_PRESSED);
            } else {
                keyCache.put(keyCode, KEY_PRESSED);
            }
        }
        pressedKeys.clear();
        releasedKeys.clear();
    }

    public void keyTyped(KeyEvent e){
        
    }

    public void keyPressed(KeyEvent e){
         pressedKeys.add(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e){
        releasedKeys.add(e.getKeyCode());
    }
}
