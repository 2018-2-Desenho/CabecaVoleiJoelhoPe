package modelo;

import java.io.IOException;

public class JumpingState extends AbstractState{
    
    public JumpingState(Player player, String spriteFileName) throws IOException {
        super(player, spriteFileName + "_pulando.png");
    }

    @Override
    public void playSong() {
        if(player.speed.y > 0){
            player.state = player.falling;
        }
    }
    
}
