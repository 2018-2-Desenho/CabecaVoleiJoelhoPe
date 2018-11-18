package util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class InputManager implements KeyListener {
    static protected int KEY_RELEASED = 0;
    static protected int KEY_JUST_PRESSED = 1;
    static protected int KEY_PRESSED = 2;
    static private InputManager instance;
    private final HashMap<Integer, Integer> keyCache;
    private final ArrayList<Integer> pressedKeys;
    private final ArrayList<Integer> releasedKeys;

    private InputManager(){
        keyCache = new HashMap<>();
        pressedKeys = new ArrayList<>();
        releasedKeys = new ArrayList<>();
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

    @Override
    public void keyTyped(KeyEvent e){
        
    }

    @Override
    public void keyPressed(KeyEvent e){
         pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e){
        releasedKeys.add(e.getKeyCode());
    }
}
