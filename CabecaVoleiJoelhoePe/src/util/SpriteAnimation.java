package util;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class SpriteAnimation {
    private int currentImageIndex;
    private int indexInc;
    private int speed;
    private boolean loop;
    private ArrayList<BufferedImage> images;

    public SpriteAnimation(){
        currentImageIndex = 0;
        loop = false;
        indexInc = 1;
        images = new ArrayList<BufferedImage>();
    }

    public void setLoop(boolean value){
        loop = value;
    }

    public boolean isLoop(){
        return loop;
    }

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed){
       this.speed = speed;
    }

    public void addImage(BufferedImage img){
        images.add(img);
    }

    public void update(int currentTick){
        if (currentTick % speed == 0){
            currentImageIndex += indexInc;
            if (currentImageIndex == images.size() || currentImageIndex == -1){
                if (loop){
                    indexInc *= -1;
                    currentImageIndex += indexInc;
                } else{
                    currentImageIndex = 0;
                }
            }
        }
    }

    public BufferedImage getImage()
    {
        return images.get(currentImageIndex);
    }

    public boolean finished()
    {
        return currentImageIndex == images.size()-1;
    }
}
