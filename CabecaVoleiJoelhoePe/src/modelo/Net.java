package modelo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ImageManager;

public class Net extends StaticEntity{

    private static final String NOME_ARQUIVO = "rede.png"; 
    
    public Net(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void init() {
        try {
            this.sprite = ImageManager.getInstance().loadImage(this.NOME_ARQUIVO);
        } catch (IOException ex) {
            Logger.getLogger(Net.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(int currentTick) {
        // Rede não necessita de atualizações durante o jogo.
    }
    
}
