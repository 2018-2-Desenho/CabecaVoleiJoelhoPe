package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.AbstractEntity;
import static modelo.AbstractEntity.COLLIDING_DOWN;
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
    BufferedImage imgPlacar;
    Font fontPlacar;
    int pj1;
    int pj2;

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

    private void calcScore(){
        if(entities.get(2).collidingEntities[COLLIDING_DOWN] != null && dynamicEntities.get(2).collidingDynamic[COLLIDING_DOWN] == null){
            if(entities.get(2).collidingEntities[COLLIDING_DOWN] == entities.get(3)){
                pj2++;
            } else if (entities.get(2).collidingEntities[COLLIDING_DOWN] == entities.get(4)) {
                pj1++;
            }
        }
        
        if(pj1 == 7 || pj2 == 7){
            gameOver = true;
        }
    }
    
    @Override
    public void onUpdate(int currentTick) {
        if (!gameOver) {
            for (AbstractEntity e : entities) {
                    e.update(currentTick);
            }
            
            calcScore();
            
            this.collisionDetector.update(currentTick);
            this.collisionDetectorDynamic.update(currentTick);
        }
        if (InputManager.getInstance().isPressed(KeyEvent.VK_ESCAPE)) {
            terminate();
        }
    }
    
    protected void renderHUD(Graphics2D g){
        g.setColor(Color.black);
        g.setFont(fontPlacar);
        g.drawString("" + pj1, 335, 130);
        g.drawString("" + pj2, 428, 130);
        
        if(gameOver){
            g.setColor(Color.red);
            
            if(pj1 == 7)
                g.drawString("Jogador 1 Venceu!", 100, 280);
            else
                g.drawString("Jogador 2 Venceu!", 100, 280);
            
            g.drawString("ESC retorna ao menu:D", 30, 360);
        }
    }

    @Override
    public void onRender(Graphics2D g) {
        g.drawImage(this.imgCenario, 0, 0, null);
        g.drawImage(this.imgPlacar, 300, 20, null);
        renderHUD(g);
        g.setColor(Color.white);
        
        for (AbstractEntity e : entities) {
            e.render(g);
        }
    }
}
