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
       
       for (int x = 0; x < this.entities.size() - 1; x++) {
            AbstractEntity focusEntity = this.entities.get(x);
            
            for (int y = x + 1; y < this.entities.size(); y++) {
                AbstractEntity verifiedEntity = this.entities.get(y);
                
                if (focusEntity.position.intersects(verifiedEntity.position)) {
                    Rectangle2D rect = focusEntity.position.createIntersection(verifiedEntity.position);
                    
                    if (rect.getWidth() > rect.getHeight()) {
                        if (focusEntity.position.getCenterY() < verifiedEntity.position.getCenterY()) {
                            focusEntity.collidingEntities[AbstractEntity.COLLIDING_DOWN] = verifiedEntity;
                            verifiedEntity.collidingEntities[AbstractEntity.COLLIDING_TOP] = focusEntity;
                        } else {
                            focusEntity.collidingEntities[AbstractEntity.COLLIDING_TOP] = verifiedEntity;
                            verifiedEntity.collidingEntities[AbstractEntity.COLLIDING_DOWN] = focusEntity;
                        }
                    } else {
                        if (focusEntity.position.getCenterX() < verifiedEntity.position.getCenterX()) {
                            focusEntity.collidingEntities[AbstractEntity.COLLIDING_RIGHT] = verifiedEntity;
                            verifiedEntity.collidingEntities[AbstractEntity.COLLIDING_LEFT] = focusEntity;
                        } else {
                            focusEntity.collidingEntities[AbstractEntity.COLLIDING_LEFT] = verifiedEntity;
                            verifiedEntity.collidingEntities[AbstractEntity.COLLIDING_RIGHT] = focusEntity;
                        }
                    }
                }
            }
        }
   }
}
