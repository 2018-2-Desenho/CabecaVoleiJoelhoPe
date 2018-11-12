package modelo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ImageManager;

public class Ground extends StaticEntity{
    
    private String NOME_ARQUIVO;
    private boolean campoDireito;

    public Ground(int x, int y, int width, int height, Arenas groundSprite, boolean campoDireito) {
        super(x, y, width, height);
        this.campoDireito = campoDireito;
        
        switch(groundSprite){
            case ARENA_GABI:
                this.NOME_ARQUIVO = "arena_gabi.png";
                break;
            case ARENA_GUILHERME:
                this.NOME_ARQUIVO = "arena_guilherme.png";
                break;
            case ARENA_LUCAS_L:
                this.NOME_ARQUIVO = "arena_lucas_l.png";
                break;
            case ARENA_LUCAS_M:
                this.NOME_ARQUIVO = "arena_lucas_m.png";
                break;
            case ARENA_LUCAS_P:
                this.NOME_ARQUIVO = "arena_lucas_p.png";
                break;
            case ARENA_LUCAS_S:
                this.NOME_ARQUIVO = "arena_lucas_s.png";
                break;
            case ARENA_PAULO:
                this.NOME_ARQUIVO = "arena_paulo.png";
                break;
            case ARENA_THIAGO:
                this.NOME_ARQUIVO = "arena_thiago.png";
                break;
        }
    }

    @Override
    public void init() {
        try {
            this.sprite = ImageManager.getInstance().loadImage(this.NOME_ARQUIVO);
        } catch (IOException ex) {
            Logger.getLogger(Ground.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(int currentTick) {
        // Detectar pontos e atualizar placar!
    }
}
