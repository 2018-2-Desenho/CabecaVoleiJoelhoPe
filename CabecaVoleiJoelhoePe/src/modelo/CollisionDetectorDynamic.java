package modelo;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class CollisionDetectorDynamic {
    ArrayList<DynamicEntity> entities;
    
   public CollisionDetectorDynamic(ArrayList<DynamicEntity> entities){
       this.entities = entities;
   }
   
   public void update(int currentTick){
       for (DynamicEntity entity : this.entities) {
           for (int i = 0; i < 4; i++) {
               entity.collidingDynamic[i] = null;
           }
       }
       
       for (int x = 0; x < this.entities.size() - 1; x++) {
            DynamicEntity focusEntity = this.entities.get(x);
            
            for (int y = x + 1; y < this.entities.size(); y++) {
                DynamicEntity verifiedEntity = this.entities.get(y);
                
                if (focusEntity.position.intersects(verifiedEntity.position)) {
                    Rectangle2D rect = focusEntity.position.createIntersection(verifiedEntity.position);
                    
                    if (rect.getWidth() > rect.getHeight()) {
                        if (focusEntity.position.getCenterY() < verifiedEntity.position.getCenterY()) {
                            focusEntity.collidingDynamic[DynamicEntity.COLLIDING_DOWN] = verifiedEntity;
                            verifiedEntity.collidingDynamic[DynamicEntity.COLLIDING_TOP] = focusEntity;
                        } else {
                            focusEntity.collidingDynamic[DynamicEntity.COLLIDING_TOP] = verifiedEntity;
                            verifiedEntity.collidingDynamic[DynamicEntity.COLLIDING_DOWN] = focusEntity;
                        }
                    } else {
                        if (focusEntity.position.getCenterX() < verifiedEntity.position.getCenterX()) {
                            focusEntity.collidingDynamic[DynamicEntity.COLLIDING_RIGHT] = verifiedEntity;
                            verifiedEntity.collidingDynamic[DynamicEntity.COLLIDING_LEFT] = focusEntity;
                        } else {
                            focusEntity.collidingDynamic[DynamicEntity.COLLIDING_LEFT] = verifiedEntity;
                            verifiedEntity.collidingDynamic[DynamicEntity.COLLIDING_RIGHT] = focusEntity;
                        }
                    }
                }
            }
        }
   }
}
