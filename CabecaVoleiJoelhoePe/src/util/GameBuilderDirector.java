package util;

import java.util.HashMap;

public class GameBuilderDirector {
     AbstractGameBuilder builder;

    public GameBuilderDirector(AbstractGameBuilder builder) {
        this.builder = builder;
    }
    
    public GameManager construct(HashMap<String, String> imageFileNames){
        return this.builder.createGameElements(imageFileNames)
                           .createSceneElements(imageFileNames)
                           .createControlElements()
                           .getResult();
    }
}
