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

    static final int STATE_STANDING = 0;
    static final int STATE_WALKING = 1;
    static final int STATE_JUMPING = 2;
    static final int STATE_FALLING = 3;
    int state;
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
                break;
        }
    }

    @Override
    public void init() {
        try {
            if(this.rightSide){
                this.FILE_NAME += "_direita";
            }
            
            BufferedImage img = ImageManager.getInstance().loadImage(FILE_NAME += "_parado.png");
            this.sprites.add(img);
            img = ImageManager.getInstance().loadImage(FILE_NAME += "_andando.png");
            this.sprites.add(img);
            img = ImageManager.getInstance().loadImage(FILE_NAME += "_pulando.png");
            this.sprites.add(img);
            img = ImageManager.getInstance().loadImage(FILE_NAME += "_caindo.png");
            this.sprites.add(img);

            this.state = STATE_STANDING;
            this.position.width = this.sprites.get(state).getWidth();
            this.position.height = this.sprites.get(state).getHeight();
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
        
        if (this.speed.y < 0) {
            if (this.state != STATE_JUMPING) {
                this.playSound("jump.wav");
            }
            this.state = STATE_JUMPING;
        } else if (speed.y > 0) {
            this.state = STATE_FALLING;
        } else if (speed.x != 0) {
            if (this.state == STATE_FALLING) {
                this.playSound("jumpEnd.wav");
            }
            this.state = STATE_WALKING;
            if (currentTick - this.lastStepTick > this.stepInterval) {
                playSound("step.wav");
                this.lastStepTick = currentTick;
            }
        } else {
            if (this.state == STATE_FALLING) {
                this.playSound("jumpEnd.wav");
            }
            this.state = STATE_STANDING;
        }
    }

    @Override
    public void render(Graphics2D g) {
        int y = (int) (this.position.y + this.position.height) - this.sprites.get(this.state).getHeight();
        int x = (int) (this.position.x + this.position.width / 2) - (this.sprites.get(this.state).getWidth() / 2);
        g.drawImage(this.sprites.get(this.state), x, y, null);
    }

    public void playSound(String fileName) {
            try {
                    AudioManager.getInstance().loadAudio(fileName).play();
            } catch (IOException ioe) {
            }
    }

}
