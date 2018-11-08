package modelo;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class CollisionDetector {
    
    ArrayList<AbstractEntity> entities;
    
   public CollisionDetector(ArrayList<AbstractEntity> entities){
       this.entities = entities;
   }
   
   public void update(int currentTick){
       for (AbstractEntity entity : this.entities) {
           for (int i = 0; i < 4; i++) {
               entity.collidingEntities[i] = null;
           }
       }
       
       for (int i1 = 0; i1 < this.entities.size() - 1; i1++) {
            AbstractEntity o1 = this.entities.get(i1);
            
            for (int i2 = i1 + 1; i2 < this.entities.size(); i2++) {
                AbstractEntity o2 = this.entities.get(i2);
                
                if (o1.position.intersects(o2.position)) {
                    Rectangle2D rect = o1.position.createIntersection(o2.position);
                    
                    if (rect.getWidth() > rect.getHeight()) {
                        if (o1.position.getCenterY() < o2.position.getCenterY()) {
                                o1.collidingEntities[AbstractEntity.COLLIDING_DOWN] = o2;
                                o2.collidingEntities[AbstractEntity.COLLIDING_TOP] = o1;
                        } else {
                                o1.collidingEntities[AbstractEntity.COLLIDING_TOP] = o2;
                                o2.collidingEntities[AbstractEntity.COLLIDING_DOWN] = o1;
                        }
                    } else {
                        if (o1.position.getCenterX() < o2.position.getCenterX()) {
                                o1.collidingEntities[AbstractEntity.COLLIDING_RIGHT] = o2;
                                o2.collidingEntities[AbstractEntity.COLLIDING_LEFT] = o1;
                        } else {
                                o1.collidingEntities[AbstractEntity.COLLIDING_LEFT] = o2;
                                o2.collidingEntities[AbstractEntity.COLLIDING_RIGHT] = o1;
                        }
                    }
                }
            }
        }
   }
}
