package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Wall extends StaticEntity{

    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void init() {
        this.sprite = new BufferedImage((int) this.position.width, (int) this.position.height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = this.sprite.getGraphics();
        g.setColor(new Color(0,0,0,0f));
        g.fillRect(0, 0, this.sprite.getWidth(), this.sprite.getHeight());
    }

    @Override
    public void update(int currentTick) {
        // Parede não necessita de atualizações durante o jogo.
    }
    
}
