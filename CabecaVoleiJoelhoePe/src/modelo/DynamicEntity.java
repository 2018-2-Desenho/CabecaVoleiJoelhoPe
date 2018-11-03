package modelo;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public abstract class DynamicEntity extends AbstractEntity{
    
    Point2D.Double speed;
    Point2D.Double acceleration;
    Point2D.Double maxSpeed;
    double friction;
    double gravity;

    public DynamicEntity(int x, int y, double speedValue) {
        super(x, y);
        this.speed = new Point2D.Double(speedValue, speedValue);
        this.acceleration = new Point2D.Double();
        this.maxSpeed = new Point2D.Double(5, 15);
        this.friction = 0.3;
        this.gravity = 0.4;
    }
    
    @Override
    public void update(int currentTick){
        this.speed.x += this.acceleration.x;
        
        if(this.speed.x < -this.maxSpeed.x) {
            this.speed.x = -this.maxSpeed.x;
        } else if(this.speed.x > this.maxSpeed.x) {
            this.speed.x = this.maxSpeed.x;
        }
        
        this.acceleration.y += this.gravity;
        this.speed.y = this.acceleration.y;
        
        if(this.speed.y < -this.maxSpeed.y) {
            this.speed.y = -this.maxSpeed.y;
        } else if(this.speed.y > this.maxSpeed.y) {
            this.speed.y = this.maxSpeed.y;
	}
    }
    
}
