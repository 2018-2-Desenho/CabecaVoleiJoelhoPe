package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Ball;
import modelo.DynamicEntity;
import modelo.Net;
import modelo.Player;
import modelo.Wall;
import util.AbstractGameBuilder;
import util.FontManager;
import util.GameManager;
import util.ImageManager;

public class GameBuilder implements AbstractGameBuilder{
    private final Game game;
    
    public GameBuilder(){
        this.game = new Game();
    }
    
    @Override
    public AbstractGameBuilder createSceneElements(final HashMap<String, String> imageFileNames) {
        this.game.entities.add(new Wall(0, 590, 400, 10));        
        this.game.entities.add(new Wall(400, 590, 400, 10));
        this.game.entities.add(new Wall(790, 0, 10, 600));
        this.game.entities.add(new Wall(0, 0, 10, 600));
        this.game.entities.add(new Wall(0, 0, 800, 10));
        this.game.entities.add(new Net(360, 400, 80, 200));
        this.game.pj1 = 0;
        this.game.pj2 = 0;
        
        try {
            this.game.imgCenario = ImageManager.getInstance().loadImage(imageFileNames.get("Arena"));
            this.game.imgPlacar = ImageManager.getInstance().loadImage("placa.png");
            this.game.fontPlacar = FontManager.getInstance().loadFont("Calculator.ttf", 80, FontManager.BOLD);
        } catch (IOException ex) {
            Logger.getLogger(GameBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this;
    }

    @Override
    public AbstractGameBuilder createGameElements(final HashMap<String, String> imageFileNames) {
        DynamicEntity j2 = new Player(500, 300, true, imageFileNames.get("Personagem_Direita"));
        DynamicEntity j1 = new Player(150, 300, false, imageFileNames.get("Personagem_Esquerda"));
        DynamicEntity b = new Ball(150, 60, 20);
        
        this.game.dynamicEntities = new ArrayList<>();
        this.game.dynamicEntities.add(j2);
        this.game.dynamicEntities.add(j1);
        this.game.dynamicEntities.add(b);
        
        this.game.entities = new ArrayList<>();
        this.game.entities.add(j2);
        this.game.entities.add(j1);
        this.game.entities.add(b);
        
        return this;
    }

    @Override
    public AbstractGameBuilder createControlElements() {
        this.game.collisionDetector = new CollisionDetector(this.game.entities);
        this.game.collisionDetectorDynamic = new CollisionDetectorDynamic(this.game.dynamicEntities);
        this.game.gameOver = false;
        
        return this;
    }

    @Override
    public GameManager getResult() {
        return game;
    }
   
}
