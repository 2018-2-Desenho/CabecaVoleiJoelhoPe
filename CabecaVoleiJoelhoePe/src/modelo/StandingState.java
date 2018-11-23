package modelo;

import java.io.IOException;

public class StandingState extends AbstractState{
    
    public StandingState(Player player, String spriteFileName) throws IOException {
        super(player, spriteFileName + "_parado.png");
    }

    @Override
    public void playSong() {
        if (player.speed.y < 0) {
            this.playSound("jump.wav");
            player.state = player.jumping;
        } else if (player.speed.x != 0){
            player.state = player.walking;
        } 
    }
    
}
