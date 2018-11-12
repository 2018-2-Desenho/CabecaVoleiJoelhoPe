package modelo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ImageManager;

public class Ground extends StaticEntity{
    
    private String FILE_NAME;
    private boolean rightSide;

    public Ground(int x, int y, int width, int height, Arenas groundSprite, boolean rightSide) {
        super(x, y, width, height);
        this.rightSide = rightSide;
        
        switch(groundSprite){
            case ARENA_GABI:
                this.FILE_NAME = "chao_gabi.png";
                break;
            case ARENA_GUILHERME:
                this.FILE_NAME = "chao_guilherme.png";
                break;
            case ARENA_LUCAS_L:
                this.FILE_NAME = "chao_lucas_l.png";
                break;
            case ARENA_LUCAS_M:
                this.FILE_NAME = "chao_lucas_m.png";
                break;
            case ARENA_LUCAS_P:
                this.FILE_NAME = "chao_lucas_p.png";
                break;
            case ARENA_LUCAS_S:
                this.FILE_NAME = "chao_lucas_s.png";
                break;
            case ARENA_PAULO:
                this.FILE_NAME = "chao_paulo.png";
                break;
            case ARENA_THIAGO:
                this.FILE_NAME = "chao_thiago.png";
                break;
        }
    }

    @Override
    public void init() {
        try {
            this.sprite = ImageManager.getInstance().loadImage(this.FILE_NAME);
        } catch (IOException ex) {
            Logger.getLogger(Ground.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(int currentTick) {
        // Detectar pontos e atualizar placar!
    }
}
