package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.AbstractEntity;
import modelo.DynamicEntity;
import util.AudioManager;
import util.GameManager;
import util.InputManager;


public class Game extends GameManager {
	
    CollisionDetector collisionDetector;
    CollisionDetectorDynamic collisionDetectorDynamic;
    ArrayList<AbstractEntity> entities;
    ArrayList<DynamicEntity> dynamicEntities;
    boolean gameOver;
    BufferedImage imgCenario;

    public Game() {}

    @Override
    public void onLoad() {        
        try {
            AudioManager.getInstance().loadAudio("menina.wav").loop();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (AbstractEntity e : entities) {
                e.init();
        }
    }

    @Override
    public void onUnload() {
        try {
            AudioManager.getInstance().loadAudio("menina.wav").stop();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onUpdate(int currentTick) {
        if (!gameOver) {
            for (AbstractEntity e : entities) {
                    e.update(currentTick);
            }
            
            this.collisionDetector.update(currentTick);
            this.collisionDetectorDynamic.update(currentTick);
        }
        if (InputManager.getInstance().isPressed(KeyEvent.VK_ESCAPE)) {
            terminate();
        }
    }

    @Override
    public void onRender(Graphics2D g) {
        g.drawImage(this.imgCenario, 0, 0, null);
        g.setColor(Color.white);
        
        for (AbstractEntity e : entities) {
            e.render(g);
        }
    }
    
//    public static void main(String[] args) {
//        GameManager myGame = new Game();
//        myGame.run();
//    }
}
