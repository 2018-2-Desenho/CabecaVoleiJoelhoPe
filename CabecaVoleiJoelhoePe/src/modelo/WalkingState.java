package modelo;

import java.io.IOException;

public class WalkingState extends AbstractState{
    
    public WalkingState(Player player, String spriteFileName) throws IOException {
        super(player, spriteFileName + "_andando.png");
    }

    @Override
    public void playSong() {
        playSound("step.wav");
        
        if (player.speed.y < 0) {
            this.playSound("jump.wav");
            player.state = player.jumping;
        } else if (player.speed.x == 0){
            player.state = player.standing;
        }
    }
    
}
