package modelo;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.AudioManager;
import util.ImageManager;
import util.InputManager;

public class Player extends DynamicEntity{

    AbstractState standing;
    AbstractState walking;
    AbstractState falling;
    AbstractState jumping;       
    AbstractState state;
    int stepInterval;
    int lastStepTick;
    ArrayList<BufferedImage> sprites;
    private String FILE_NAME;

    public Player(int x, int y, boolean rightSide, Characters playerSprite) {
        super(x, y, 10);
        this.stepInterval = 20;
        this.sprites = new ArrayList<>();
        this.rightSide = rightSide;
        
        switch(playerSprite){
            case PERSONAGEM_GABI:
                this.FILE_NAME = "personagem_gabi";
                break;
            case PERSONAGEM_GUILHERME:
                this.FILE_NAME = "personagem_guilherme";
                break;
            case PERSONAGEM_LUCAS_L:
                this.FILE_NAME = "personagem_lucas_l";
                break;
            case PERSONAGEM_LUCAS_M:
                this.FILE_NAME = "personagem_lucas_m";
                break;
            case PERSONAGEM_LUCAS_P:
                this.FILE_NAME = "personagem_lucas_p";
                break;
            case PERSONAGEM_LUCAS_S:
                this.FILE_NAME = "personagem_lucas_s";
                break;
            case PERSONAGEM_PAULO:
                this.FILE_NAME = "personagem_paulo";
                break;
            case PERSONAGEM_THIAGO:
                this.FILE_NAME = "personagem_thiago";
        }
    }

    @Override
    public void init() {
        try {
            if(this.rightSide){
                this.FILE_NAME += "_direita";
            }
            
            this.standing = new StandingState(this, FILE_NAME);
            this.walking = new WalkingState(this, FILE_NAME);
            this.jumping = new JumpingState(this, FILE_NAME);
            this.falling = new FallingState(this, FILE_NAME);

            this.state = this.standing;
            this.position.width = this.state.sprite.getWidth();
            this.position.height = this.state.sprite.getHeight();
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(int currentTick) {
        
        if(this.rightSide){
            if (InputManager.getInstance().isPressed(KeyEvent.VK_RIGHT)) {
                this.acceleration.x = 0.4;
            } else if (InputManager.getInstance().isPressed(KeyEvent.VK_LEFT)) {
                this.acceleration.x = -0.4;
            }
            if (InputManager.getInstance().isJustPressed(KeyEvent.VK_UP) && this.collidingEntities[COLLIDING_DOWN] != null) {
                this.acceleration.y = -10;
            }
        } else {
            if (InputManager.getInstance().isPressed(KeyEvent.VK_D)) {
                this.acceleration.x = 0.4;
            } else if (InputManager.getInstance().isPressed(KeyEvent.VK_A)) {
                this.acceleration.x = -0.4;
            }
            if (InputManager.getInstance().isJustPressed(KeyEvent.VK_W) && this.collidingEntities[COLLIDING_DOWN] != null) {
                this.acceleration.y = -10;
            }
        }
        
        super.update(currentTick);
        
        this.state.playSong();
    }

    @Override
    public void render(Graphics2D g) {
        this.state.renderSprite(g);
    }

    public void playSound(String fileName) {
        try {
            AudioManager.getInstance().loadAudio(fileName).play();
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
