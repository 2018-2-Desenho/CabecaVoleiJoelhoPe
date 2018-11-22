package modelo;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class AbstractEntity {
    
    public static final int COLLIDING_TOP = 0;
    public static final int COLLIDING_RIGHT = 1;
    public static final int COLLIDING_DOWN = 2;
    public static final int COLLIDING_LEFT = 3;
    public Rectangle2D.Double position;
    int energy;
    public AbstractEntity[] collidingEntities;
    
    public AbstractEntity(int x, int y){
        this.position = new Rectangle2D.Double(x, y, 1, 1);
        this.energy = 1;
        this.collidingEntities = new AbstractEntity[4];
    }
    
    public abstract void init();
    public abstract void render(Graphics2D g);
    public abstract void update(int currentTick);
}
