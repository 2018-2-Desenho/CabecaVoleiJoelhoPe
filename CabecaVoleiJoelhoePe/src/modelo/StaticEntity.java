package modelo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class StaticEntity extends AbstractEntity{
    
    BufferedImage sprite;

    public StaticEntity(int x, int y, int width, int height) {
        super(x, y);
        this.position.setRect(x, y, width, height);
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(this.sprite, (int) this.position.x, (int) this.position.y, null);
    }
}
