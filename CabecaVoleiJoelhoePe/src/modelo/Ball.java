package modelo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ImageManager;

public class Ball extends DynamicEntity {
    
    private static final String FILE_NAME = "bola.png";
    BufferedImage sprite;

    public Ball(int x, int y, double speedValue) {
        super(x, y, speedValue);
    }

    @Override
    public void init() {
        try {
            this.friction = 0.10;
            this.gravity = 0.40;
            this.sprite = ImageManager.getInstance().loadImage(FILE_NAME);
            this.position.width = this.sprite.getWidth();
            this.position.height = this.sprite.getHeight();
        } catch (IOException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void update(int currentTick) {
        if (this.collidingDynamic[COLLIDING_TOP] != null) {
            this.acceleration.y -= 50;    
        } 
            
        if (this.collidingDynamic[COLLIDING_DOWN] != null) {
            this.acceleration.y += Math.random() * 5;
            if(this.collidingDynamic[COLLIDING_DOWN].rightSide){
                Player aux = (Player) this.collidingDynamic[COLLIDING_DOWN];
                if(aux.state == 2){    
                    this.acceleration.y += 1 + Math.random() * 10;

                    this.acceleration.x = 0;
                    this.acceleration.x = -80;
                }
            } else {
                Player aux = (Player) this.collidingDynamic[COLLIDING_DOWN];
                if(aux.state == 2){
                    this.acceleration.y += 1 + Math.random() * 10;

                    this.acceleration.x = 0;
                    this.acceleration.x = 80;
                }
            }
        }
            
        if (this.collidingDynamic[COLLIDING_LEFT] != null) {
            this.acceleration.x += 50;
        } 

        if (this.collidingDynamic[COLLIDING_RIGHT] != null) {
            this.acceleration.x -= 50;
        }

        this.speed.x += this.acceleration.x;
        if (this.speed.x < -this.maxSpeed.x) {
                this.speed.x = -this.maxSpeed.x;
        } else if (this.speed.x > this.maxSpeed.x) {
                this.speed.x = this.maxSpeed.x;
        }

        this.acceleration.y += gravity;
        this.speed.y += this.acceleration.y;
        if (this.speed.y < -this.maxSpeed.y) {
                this.speed.y = -this.maxSpeed.y;
        } else if (this.speed.y > this.maxSpeed.y) {
                this.speed.y = this.maxSpeed.y;
        }

        if (this.speed.y < 0) {
                if (this.collidingEntities[COLLIDING_TOP] != null) {
                        this.position.y = this.collidingEntities[COLLIDING_TOP].position.y
                                        + this.collidingEntities[COLLIDING_TOP].position.height;
                        this.speed.y = this.speed.y * -0.9;
                        this.acceleration.y = this.acceleration.y * -0.9;
                }
        } else if (this.speed.y > 0) {
                if (this.collidingEntities[COLLIDING_DOWN] != null) {
                        this.position.y = this.collidingEntities[COLLIDING_DOWN].position.y
                                        - this.position.height
                                        + 1;
                        this.speed.y = this.speed.y * -0.9;
                        this.acceleration.y = this.acceleration.y * -0.9;
                }
        }

        if (this.speed.x < 0) {
                if (this.collidingEntities[COLLIDING_LEFT] != null) {
                        this.position.x = this.collidingEntities[COLLIDING_LEFT].position.x
                                        + this.collidingEntities[COLLIDING_LEFT].position.width - 1;
                        this.speed.x = this.speed.x * -0.9;
                        this.acceleration.x = this.acceleration.x * -0.9;
                } else {
                        this.speed.x += this.friction;
                        if (this.speed.x > 0) {
                                this.speed.x = 0;
                        }
                }
        } else if (this.speed.x > 0) {
                if (this.collidingEntities[COLLIDING_RIGHT] != null) {
                        this.position.x = this.collidingEntities[COLLIDING_RIGHT].position.x
                                        - this.position.width
                                        + 1;
                        this.speed.x = this.speed.x * -0.9;
                        this.acceleration.x = this.acceleration.x * -0.9;
                } else {
                        this.speed.x -= this.friction;
                        if (this.speed.x < 0) {
                                this.speed.x = 0;
                        }
                }
        }

        this.position.x += this.speed.x;
        this.position.y += this.speed.y;

        this.acceleration.x = this.acceleration.x * 0.99;
        this.acceleration.y = 0;
    }

    @Override
    public void render(Graphics2D g) {
            // Desenha o sprite pego da positionição correspondente ao estado atual.
            int y = (int) (this.position.y + this.position.height) - this.sprite.getHeight();
            int x = (int) (this.position.x + this.position.width / 2) - (this.sprite.getWidth() / 2);
            g.drawImage(this.sprite, x, y, null);
    }
    
}
