package modelo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.AudioManager;
import util.ImageManager;
import util.GameManager;
import util.InputManager;


public class Game extends GameManager {
	
    CollisionDetector collisionDetector;
    CollisionDetectorDynamic collisionDetectorDynamic;
    ArrayList<AbstractEntity> entities;
    ArrayList<DynamicEntity> dynamicEntities;
    boolean gameOver;
    BufferedImage imgCenario;

    public Game() {		
            entities = new ArrayList<>();
            dynamicEntities = new ArrayList<>();
            collisionDetector = new CollisionDetector(entities);
            collisionDetectorDynamic = new CollisionDetectorDynamic(dynamicEntities);
            gameOver = false;
    }

    @Override
    public void onLoad() {        
        try {
            imgCenario = ImageManager.getInstance().loadImage("estadio.png");
            AudioManager.getInstance().loadAudio("menina.wav").loop();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DynamicEntity j2 = new Player(500, 300, true, Characters.PERSONAGEM_GABI);
        DynamicEntity j1 = new Player(150, 300, false, Characters.PERSONAGEM_GABI);
        DynamicEntity b = new Ball(60, 60, 20);

        dynamicEntities.add(j2);
        dynamicEntities.add(j1);
        dynamicEntities.add(b);

        entities.add(j2);
        entities.add(j1);
        entities.add(b);

        entities.add(new Wall(0, 590, 800, 10));
        entities.add(new Wall(790, 0, 10, 600));
        entities.add(new Wall(0, 0, 10, 600));
        entities.add(new Wall(0, 0, 800, 10));
        entities.add(new Net(360, 400, 80, 200));

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
            collisionDetector.update(currentTick);
            collisionDetectorDynamic.update(currentTick);
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
    
    public static void main(String[] args) {
        GameManager myGame = new Game();
        myGame.run();
    }
}
