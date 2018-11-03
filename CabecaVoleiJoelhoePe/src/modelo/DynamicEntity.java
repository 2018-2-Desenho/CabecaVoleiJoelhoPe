package modelo;

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
        
        if (this.speed.y < 0) {
            if (this.collidingEntities[COLLIDING_TOP] != null) {
		this.position.y = this.collidingEntities[COLLIDING_TOP].position.y + this.collidingEntities[COLLIDING_TOP].position.height;
		this.speed.y = 0;
		this.acceleration.y = 0;
            }
	} else if (this.speed.y > 0) {
            if (this.collidingEntities[COLLIDING_DOWN] != null) {
		this.position.y = this.collidingEntities[COLLIDING_DOWN].position.y - this.position.height + 1;
		this.speed.y = 0;
		this.acceleration.y = 0;
            }
	}

	if(this.speed.x < 0) {
            if(this.collidingEntities[COLLIDING_LEFT] != null) {
		this.position.x = this.collidingEntities[COLLIDING_LEFT].position.x + this.collidingEntities[COLLIDING_LEFT].position.width - 1;
		this.speed.x = 0;
		this.acceleration.x = 0;
            } else {
		this.speed.x += this.friction;
		if(this.speed.x > 0) {
                    this.speed.x = 0;
		}
            }
	} else if(this.speed.x > 0) {
            if(this.collidingEntities[COLLIDING_RIGHT] != null) {
		this.position.x = this.collidingEntities[COLLIDING_RIGHT].position.x - this.position.width + 1;
		this.speed.x = 0;
		this.acceleration.x = 0;
            } else {
		this.speed.x -= this.friction;
		if (this.speed.x < 0) {
                    this.speed.x = 0;
		}
            }
	}
	
        this.position.x += this.speed.x;
        this.position.y += this.speed.y;
	this.acceleration.x = 0;
	this.acceleration.y = 0;
    }
    
}
