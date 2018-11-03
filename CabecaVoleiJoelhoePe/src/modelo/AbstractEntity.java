package modelo;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class AbstractEntity {
    
    static final int COLLIDING_TOP = 0;
    static final int COLLIDING_RIGHT = 1;
    static final int COLLIDING_DOWN = 2;
    static final int COLLIDING_LEFT = 3;
    Rectangle2D.Double position;
    int energy;
    AbstractEntity[] collidingEntities;
    
    public AbstractEntity(int x, int y){
        this.position = new Rectangle2D.Double(x, y, 1, 1);
        this.energy = 1;
        this.collidingEntities = new AbstractEntity[4];
    }
    
    public abstract void init();
    public abstract void update(int currentTick);
    public abstract void render(Graphics2D g);
    
}
