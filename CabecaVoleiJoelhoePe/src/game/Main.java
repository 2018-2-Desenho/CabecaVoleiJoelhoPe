package game;

import java.util.HashMap;
import util.AbstractGameBuilder;
import util.GameBuilderDirector;
import util.GameManager;

public class Main {

    public static void main(String[] args) {
        AbstractGameBuilder builder = new GameBuilder();
        GameBuilderDirector director = new GameBuilderDirector(builder);
        
        GameManager myGame = director.construct(new HashMap<>());
        myGame.run();
    }
    
}
