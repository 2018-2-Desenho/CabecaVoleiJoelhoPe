package modelo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static modelo.AbstractEntity.COLLIDING_DOWN;
import util.AudioManager;
import util.FontManager;
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
    BufferedImage imgPlacar;
    Font fontPlacar;
    int pj1;
    int pj2;

    public Game() {		
            entities = new ArrayList<>();
            dynamicEntities = new ArrayList<>();
            collisionDetector = new CollisionDetector(entities);
            collisionDetectorDynamic = new CollisionDetectorDynamic(dynamicEntities);
            gameOver = false;
            pj1 = 0;
            pj2 = 0;
    }

    @Override
    public void onLoad() {        
        try {
            imgCenario = ImageManager.getInstance().loadImage("estadio.png");
            imgPlacar = ImageManager.getInstance().loadImage("placa.png");
            AudioManager.getInstance().loadAudio("menina.wav").loop();
            
            fontPlacar = FontManager.getInstance().loadFont("Calculator.ttf", 80, FontManager.BOLD);
            
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DynamicEntity j2 = new Player(500, 300, true, Characters.PERSONAGEM_GABI);
        DynamicEntity j1 = new Player(150, 300, false, Characters.PERSONAGEM_GABI);
        DynamicEntity b = new Ball(60, 60, 20);

        dynamicEntities.add(j2);
        dynamicEntities.add(j1);
        dynamicEntities.add(b);
        
        AbstractEntity p1 = new Wall(0, 590, 400, 10);
        AbstractEntity p2 = new Wall(400, 590, 400, 10);
        
        entities.add(j2);
        entities.add(j1);
        entities.add(b);
        entities.add(p1);
        entities.add(p2);
        
        // entities.add(new Wall(0, 590, 800, 10));
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
    
    private void calcScore(){
        if(entities.get(2).collidingEntities[COLLIDING_DOWN] != null && dynamicEntities.get(2).collidingDynamic[COLLIDING_DOWN] == null){
            if(entities.get(2).collidingEntities[COLLIDING_DOWN] == entities.get(3)){
                pj2++;
            } else if (entities.get(2).collidingEntities[COLLIDING_DOWN] == entities.get(4)) {
                pj1++;
            }
        }
    }

    @Override
    public void onUpdate(int currentTick) {
        if (!gameOver) {
            for (AbstractEntity e : entities) {
                    e.update(currentTick);
            }
            
            calcScore();
            
            collisionDetector.update(currentTick);
            collisionDetectorDynamic.update(currentTick);
        }
        if (InputManager.getInstance().isPressed(KeyEvent.VK_ESCAPE)) {
            terminate();
        }
    }
    
    protected void renderHUD(Graphics2D g){
        // Muda a cor para amarelo.
        g.setColor(Color.black);
        // Muda a fonte para uma derivada de tamanho 44.
        g.setFont(fontPlacar);
        // Escreve as informações na tela (pontos e tentativas).
        g.drawString("" + pj1, 335, 110);
        g.drawString("" + pj2, 428, 110);
    }

    @Override
    public void onRender(Graphics2D g) {
        g.drawImage(this.imgCenario, 0, 0, null);
        g.drawImage(this.imgPlacar, 300, 0, null);
        renderHUD(g);
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
