package modelo;

import java.io.IOException;

public class FallingState extends AbstractState{
    
    public FallingState(Player player, String spriteFileName) throws IOException {
        super(player, spriteFileName + "_caindo.png");
    }

    @Override
    public void playSong() {
        this.playSound("jumpEnd.wav");
        
        if(player.speed.y == 0 && player.speed.x != 0){
            player.state = player.walking;
        } else if (player.speed.y == 0 && player.speed.x == 0) {
            player.state = player.standing;
        }
    }
    
}
