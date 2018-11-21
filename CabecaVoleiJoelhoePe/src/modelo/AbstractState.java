package modelo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.AudioManager;
import util.ImageManager;

public abstract class AbstractState {
    
    protected Player player;
    public final BufferedImage sprite;
    
    protected AbstractState(Player player, String spriteFileName) throws IOException{
        this.player = player;
        this.sprite = ImageManager.getInstance().loadImage(spriteFileName);
       
    }
    
    public abstract void playSong();
    
    public void renderSprite(Graphics2D g){
        int y = (int) (this.player.position.y + this.player.position.height) - this.sprite.getHeight();
        int x = (int) (this.player.position.x + this.player.position.width / 2) - (this.sprite.getWidth() / 2);
        g.drawImage(this.sprite, x, y, null);
    }
    
    protected void playSound(String fileName) {
        try {
            AudioManager.getInstance().loadAudio(fileName).play();
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
